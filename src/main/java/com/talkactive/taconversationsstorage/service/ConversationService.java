package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.ConversationDTO;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface ConversationService {

    MessageDTO save(MessageDTO messageDTO);

    List<ConversationDTO> getConversations(Principal principal);

    List<MessageDTO> getMessages(Principal principal, String conversationMember);
}
