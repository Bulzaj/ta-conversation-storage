package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface ConversationService {

    MessageDTO save(MessageDTO messageDTO);
}
