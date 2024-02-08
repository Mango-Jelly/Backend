package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.request.RoomBeginRequest;
import com.mangojelly.backend.applicatoin.dto.request.RoomCreateRequest;
import com.mangojelly.backend.applicatoin.dto.response.RoomCreateResponse;
import com.mangojelly.backend.domain.guest.Guest;
import com.mangojelly.backend.domain.guest.GuestService;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.movie.MovieService;
import com.mangojelly.backend.domain.role.Role;
import com.mangojelly.backend.domain.role.RoleService;
import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.room.RoomService;
import com.mangojelly.backend.domain.sceneMovie.SceneMovie;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.domain.script.ScriptService;
import com.mangojelly.backend.global.common.PythonRunComponent;
import com.mangojelly.backend.global.common.S3FileUploader;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RoomFacade {
    private final RoomService roomService;
    private final MemberService memberService;
    private final ScriptService scriptService;
    private final GuestService guestService;
    private final RoleService roleService;
    private final MovieService movieService;
    private final PythonRunComponent pythonRunComponent;
    private final S3FileUploader s3FileUploader;

    /**
     * 방 생성 여부 체크
     *
     * @param memberId
     * @return 해당 회원의 방 정보
     */
    public RoomCreateResponse existRoomByMember(int memberId) {
        Member member = memberService.findById(memberId);
        return RoomCreateResponse.of(roomService.findByMember(member).getAddress());
    }

    /**
     * 방 생성 메서드
     *
     * @param memberId, request
     * @return 생성한 방 객체
     */
    @Transactional
    public RoomCreateResponse saveRoom(int memberId, RoomCreateRequest request) {
        Member member = memberService.findById(memberId);
        return RoomCreateResponse.of(roomService.save(request.title(), request.department(), member, request.visible()).getAddress());
    }

    /**
     * 방 삭제 메서드
     *
     * @param address
     */
    @Transactional
    public void deleteRoom(int memberId, UUID address) {
        Member member = memberService.findById(memberId);
        roomService.deleteRoomByMember(address, member);
    }

    @Transactional
    public void saveMovie(int memberId, UUID roomUUID) {
        Member member = memberService.findById(memberId);
        Room room = roomService.findByAddress(roomUUID);
        if (room.getMember().getId() != member.getId())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_IS_NOT_YOURS);

        // 연극 제목
        String movieTitle = room.getTitle().replace(" ", "") + roomUUID.toString().substring(0, 8);
        List<String> sceneMovieUrlList = room.getSceneMovieList().stream().map(SceneMovie::getAddress).toList();

        for (String sceneMovieUrl : sceneMovieUrlList)
            loadSceneMovie(sceneMovieUrl);

        concatSceneMovie(movieTitle, sceneMovieUrlList);
        String address = s3FileUploader.uploadFile("scene/"+movieTitle+".mp4");
        List<Guest> guests = guestService.findAllByRoomId(room.getId());

        movieService.save(member, room.getScript(), room.isVisible(), address, movieTitle, guests, room.getDpt());
    }

    /**
     * sceneMovie download(url : s3)
     *
     * @param url
     */
    public void loadSceneMovie(String url) {
        String[] fileName = url.split("/");
        try {
            InputStream in = URI.create(url).toURL().openStream();
            Files.copy(in, Paths.get("src/main/resources/util/scene/" + fileName[fileName.length - 1]));
        } catch (IOException e) {
            System.out.println(e.toString());
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
        }
    }


    /**
     * sceneMovie 합치기
     *
     * @param movieTitle
     * @param sceneMovieList
     * @throws Exception
     */
    public void concatSceneMovie(String movieTitle, List<String> sceneMovieList) {
        List<String> commandTest = new ArrayList<>();
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("window");
        String python = isWindows ? "python" : "python3";
        commandTest.add(python);
        commandTest.add("VideoEditor.py");
        commandTest.add("./scene/" + movieTitle + ".mp4");

        for (String filename : sceneMovieList) {
            commandTest.add("./videos/" + filename);
        }
        commandTest.add("--delete");
        try{
            if (!pythonRunComponent.runPy(commandTest)){
                throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
            }
        }catch (Exception e){
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
        }
    }

    /**
     * 연극 시작하기 메서드
     *
     * @param memberId
     * @param guests
     * @param scriptId
     */
    @Transactional
    public void beginMovie(int memberId, List<RoomBeginRequest.Players> guests, int scriptId, UUID address) {
        Member member = memberService.findById(memberId);
        Room room = roomService.checkRoomBegin(member, address);
        Script script = scriptService.findById(scriptId);

        roomService.updateScript(room, script);
        for (RoomBeginRequest.Players guest : guests) {
            Guest guest_ = guestService.findById(guest.guestId());
            if (guest.roleId() != null) {
                Role role = roleService.findById(guest.roleId(), script);
                guestService.updateRole(guest_, role);
            }
        }
    }
}