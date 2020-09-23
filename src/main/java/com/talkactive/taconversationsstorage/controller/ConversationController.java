package com.talkactive.taconversationsstorage.controller;

import com.talkactive.taconversationsstorage.model.ConversationDTO;
import com.talkactive.taconversationsstorage.model.Message;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import com.talkactive.taconversationsstorage.service.ConversationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conversation")
@Slf4j
public class ConversationController {

    private ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/save-message")
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody MessageDTO messageDTO) {
        return new ResponseEntity<>(conversationService.save(messageDTO), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ConversationDTO>> conversations(Principal principal) {
        return new ResponseEntity<>(conversationService.getConversations(principal), HttpStatus.OK);
    }
}
