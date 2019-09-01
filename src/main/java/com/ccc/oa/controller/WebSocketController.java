package com.ccc.oa.controller;

import com.ccc.oa.model.ChatMessage;
import com.ccc.oa.model.ChatUser;
import com.ccc.oa.service.ChatMessageService;
import com.ccc.oa.service.ChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping(value = "/app")
public class WebSocketController {

    private final SimpMessagingTemplate template;
    private final ChatUserService chatUserService;
    private final ChatMessageService chatMessageService;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template, ChatUserService chatUserService, ChatMessageService chatMessageService) {
        this.template = template;
        this.chatUserService = chatUserService;
        this.chatMessageService = chatMessageService;
    }

    @GetMapping(value = "/getMessage")
    @ResponseBody
    public List<ChatMessage> getMessages(String from, String to) {
        List<ChatMessage> res = chatMessageService.getFromUserToUser(from, to);
        List<ChatMessage> merge = chatMessageService.getFromUserToUser(to, from);
        res.addAll(merge);
        res.sort(Comparator.comparingLong(ChatMessage::getCreated));
        return res;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<ChatUser> connectUser() {
        return chatUserService.getAll();
    }


    @MessageMapping("/transmit")
    public void handleMessage(ChatMessage chatMessage) {
        System.out.println("后台一对一推送！");
        chatMessage.setMessage(HtmlUtils.htmlEscape(chatMessage.getMessage()));
        chatMessageService.add(chatMessage.getFrom(), chatMessage);
        template.convertAndSendToUser(chatMessage.getTo(), "/queue/message", chatMessage);
    }
}