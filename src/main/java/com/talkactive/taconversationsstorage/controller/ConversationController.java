package com.talkactive.taconversationsstorage.controller;

import com.talkactive.taconversationsstorage.model.Message;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import com.talkactive.taconversationsstorage.service.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversation")
public class ConversationController {

    private ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping(value = "/save-message")
    public ResponseEntity<Message> saveMessage(MessageDTO messageDTO) {
        return new ResponseEntity<>(conversationService.save(messageDTO), HttpStatus.CREATED);
    }
}
