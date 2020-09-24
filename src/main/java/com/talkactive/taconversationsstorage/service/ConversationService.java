package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.ConversationsListDTO;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public interface ConversationService {

    MessageDTO save(MessageDTO messageDTO);

    ConversationsListDTO getConversations(Principal principal);

    List<MessageDTO> getMessages(Principal principal, String conversationMember);
}
