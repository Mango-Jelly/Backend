package com.mangojelly.backend.domain.room;

import com.mangojelly.backend.domain.guest.Guest;
import com.mangojelly.backend.domain.guest.GuestRepository;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.movie.MovieMapper;
import com.mangojelly.backend.domain.movie.MovieRepository;
import com.mangojelly.backend.domain.sceneMovie.SceneMovie;
import com.mangojelly.backend.global.common.PythonRunComponent;
import com.mangojelly.backend.applicatoin.dto.request.RoomBeginRequest;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.script.Script;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final GuestRepository guestRepository;
    private final PythonRunComponent pythonRunComponent;

    public Room findByMember(Member member) {
        return roomRepository.findByMember(member).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_NOT_EXISTED));
    }

    @Transactional
    public Room save(String title, String dpt, Member member, boolean visible) {
        if (roomRepository.findByMember(member).isPresent())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_EXISTED);
        return roomRepository.save(roomMapper.toEntity(title, dpt, member, UUID.randomUUID(), visible));
    }

    @Transactional
    public Room save(String title, String dpt, Member member, boolean visible, UUID address) {
        return roomRepository.save(roomMapper.toEntity(title, dpt, member, address, visible));
    }

    @Transactional
    public void deleteRoomByMember(UUID address, Member member) {
        Room room = roomRepository.findByAddress(address).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_DELETED));
        if (room.getMember().getId() != member.getId())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_IS_NOT_YOURS);
        roomRepository.deleteByAddress(address);
    }

    public Room findByAddress(UUID address) {
        return roomRepository.findByAddress(address).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_INPUT_INVALID_VALUE));
    }

    @Transactional
    public void saveMovie(Member member, UUID roomUUID) {
        Room room = roomRepository.findByAddress(roomUUID).orElseThrow(() -> BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_ALREADY_DELETED));
        if (room.getMember().getId() != member.getId())
            throw BusinessException.of(ErrorCode.ERROR_CLIENT_BY_ROOM_IS_NOT_YOURS);
//        for(SceneMovie sceneMovie :room.getSceneMovieList()){
//            sceneMovie.getAddress()
//        }
        // 연극 제목
        String movieTitle = room.getTitle() + roomUUID.toString().substring(0, 9);
        movieTitle = movieTitle.replace(" ", "");
        List<Guest> guests = guestRepository.findAllByRoomId(room.getId());
        StringBuilder sbGuest = new StringBuilder();
        for (Guest guest : guests) {
            if (guest.getRole() != null) {
                sbGuest.append(guest.getName()).append(",");
                sbGuest.append(guest.getRole().getName()).append(",");
            }
        }
        sbGuest.deleteCharAt(sbGuest.length() - 1);
        String party = sbGuest.toString();

        List<String> commandTest = new ArrayList<>();
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("window");
        String python = isWindows ? "python" : "python3";
        commandTest.add(python);
        commandTest.add("VideoEditor.py");
        // S3의 기본 주소
        String basePath = "C:\\Users\\SSAFY\\Desktop\\util\\";
        // 이거 S3에 저장할 주소로 바꾸면됨
        StringBuilder sbOut = new StringBuilder();
        sbOut.append(basePath).append("movie\\").append(movieTitle).append(".mp4");
        System.out.println(sbOut);
        commandTest.add(sbOut.toString());

        // 이거 S3에 씬 영상 저장된 주소로 바꾸면 됨
        File videoDir = new File(basePath + "\\videos");
        System.out.println(videoDir);
        String[] filenames = videoDir.list();
        assert filenames != null;
        for (String filename : filenames) {
            commandTest.add(videoDir + "\\" + filename);
        }
        commandTest.add("--delete");
        System.out.println("아웃!!" + sbOut);
        System.out.println(commandTest);
        if(!pythonRunComponent.runPy(commandTest))
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);

        movieRepository.save(movieMapper.toEntity(member, room.getScript(), roomUUID.toString(), movieTitle, party, room.getDpt(), room.isVisible()));
    }
    /**
     * 연극 시작 가능 여부 확인 함수
     * @param member
     * @param address
     * @return
     */
    public Room checkRoomBegin(Member member, UUID address){
        findByMember(member);
        return roomRepository.findByMemberAndAddress(member, address).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_NO_AUTHORIZATION));
    }

    @Transactional
    public Room updateScript(Room room, Script script){
        room.setScript(script);
        return room;
    }

}
