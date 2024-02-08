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
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
        List<String> sceneMovieList = room.getSceneMovieList().stream().map(SceneMovie::getAddress).toList();

//        try{
//            concatSceneMovie(movieTitle,);
//        }catch (Exception e){
//            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
//        }
        List<Guest> guests = guestService.findAllByRoomId(room.getId());
        movieService.save(member, room.getScript(), room.isVisible() ,roomUUID.toString(), movieTitle, guests, room.getDpt());
    }
    private void loadSceneMovie(List<String> sceneMovieList){


    }


//    private void concatSceneMovie(String movieTitle,List<String> sceneMovieList) throws Exception {
//        List<String> commandTest = new ArrayList<>();
//        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("window");
//        String python = isWindows ? "python" : "python3";
//        commandTest.add(python);
//        commandTest.add("VideoEditor.py");
//
//        // S3의 기본 주소
////        String basePath = "C:\\Users\\SSAFY\\Desktop\\util\\";
//        // 이거 S3에 저장할 주소로 바꾸면됨
//        StringBuilder sbOut = new StringBuilder();
//        sbOut.append("./movie").append(movieTitle).append(".mp4");
////        System.out.println(sbOut);
//        commandTest.add(sbOut.toString());
//
//        // 이거 S3에 씬 영상 저장된 주소로 바꾸면 됨
//        File videoDir = new File("./videos");
//        ClassPathResource resource = new ClassPathResource("/videos");
//        System.out.println(videoDir);
//        for (String filename : sceneMovieList) {
//            commandTest.add(videoDir + "/" + filename);
//        }
//        commandTest.add("--delete");
//        System.out.println("아웃!!" + sbOut);
//        System.out.println(commandTest);
//        if(!pythonRunComponent.runPy(commandTest))
//            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
//    }

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