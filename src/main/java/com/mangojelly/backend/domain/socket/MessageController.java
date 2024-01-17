package com.mangojelly.backend.domain.socket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/hello/{channelId}")
    public void message(Message message, @DestinationVariable("channelId") String channelId){
        log.debug("channelId:{}", channelId);
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + channelId, message);
    }

}
