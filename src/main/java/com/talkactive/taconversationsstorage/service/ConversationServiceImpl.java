package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.Conversation;
import com.talkactive.taconversationsstorage.model.Message;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import com.talkactive.taconversationsstorage.repository.ConversationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConversationServiceImpl implements ConversationService {

    private ConversationRepository conversationRepository;

    public ConversationServiceImpl(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    @Override
    public Message save(MessageDTO messageDTO) {

        Message result = new Message(messageDTO);

        Conversation senderConversation = conversationRepository.findByConversationOwnerAndConversationMember(messageDTO.getSenderName(), messageDTO.getReceiverName())
                .orElse(new Conversation(messageDTO.getSenderName(), messageDTO.getReceiverName(), new ArrayList<>()));
        senderConversation.getMessages().add(result);

        Conversation receiverConversation = conversationRepository.findByConversationOwnerAndConversationMember(messageDTO.getReceiverName(), messageDTO.getSenderName())
                .orElse(new Conversation(messageDTO.getReceiverName(), messageDTO.getSenderName(), new ArrayList<>()));
        receiverConversation.getMessages().add(result);

        conversationRepository.save(senderConversation);
        conversationRepository.save(receiverConversation);
        return result;
    }
}
