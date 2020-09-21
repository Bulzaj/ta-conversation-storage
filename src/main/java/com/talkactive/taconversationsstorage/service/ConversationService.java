package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.Message;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import org.springframework.stereotype.Service;

@Service
public interface ConversationService {

    Message save(MessageDTO messageDTO);
}
