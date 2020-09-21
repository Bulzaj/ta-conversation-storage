package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.Conversation;
import com.talkactive.taconversationsstorage.model.Message;
import com.talkactive.taconversationsstorage.model.MessageDTO;
import com.talkactive.taconversationsstorage.repository.ConversationRepository;
import com.talkactive.taconversationsstorage.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConversationServiceImpl implements ConversationService {

    private ConversationRepository conversationRepository;
    private MessageRepository messageRepository;

    public ConversationServiceImpl(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Message save(MessageDTO messageDTO) {

        Message senderMessage = new Message(messageDTO);
        Message receiverMessage = new Message(messageDTO);

        Conversation senderConversation = conversationRepository.findByConversationOwnerAndConversationMember(messageDTO.getSenderName(), messageDTO.getReceiverName())
                .orElse(new Conversation(messageDTO.getSenderName(), messageDTO.getReceiverName(), new ArrayList<>()));
        senderConversation.getMessages().add(senderMessage);

        Conversation receiverConversation = conversationRepository.findByConversationOwnerAndConversationMember(messageDTO.getReceiverName(), messageDTO.getSenderName())
                .orElse(new Conversation(messageDTO.getReceiverName(), messageDTO.getSenderName(), new ArrayList<>()));
        receiverConversation.getMessages().add(receiverMessage);

        senderMessage.setConversation(senderConversation);
        receiverMessage.setConversation(receiverConversation);

        conversationRepository.save(senderConversation);
        messageRepository.save(senderMessage);

        conversationRepository.save(receiverConversation);
        messageRepository.save(receiverMessage);

        return new Message(messageDTO);
    }
}
