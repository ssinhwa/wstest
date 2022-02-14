package com.websocket.wstest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/ws")
public class ChatController {

    @RequestMapping("/chat")
    public String home(){
        System.out.println("ChatController.home");
        return "chat";
    }
}
