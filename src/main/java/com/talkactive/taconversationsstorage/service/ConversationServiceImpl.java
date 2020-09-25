package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.*;
import com.talkactive.taconversationsstorage.repository.ConversationRepository;
import com.talkactive.taconversationsstorage.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final StandardPBEStringEncryptor encryptor;

    public ConversationServiceImpl(ConversationRepository conversationRepository, MessageRepository messageRepository, StandardPBEStringEncryptor encryptor) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
        this.encryptor = encryptor;
    }

    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        Message senderMessage = new Message(messageDTO);
        Message receiverMessage = new Message(messageDTO);

        Conversation senderConversation = conversationRepository.findByConversationOwner(messageDTO.getSenderName())
                .orElse(new Conversation(messageDTO.getSenderName(), new ArrayList<>()));

        Conversation receiverConversation = conversationRepository.findByConversationOwner(messageDTO.getReceiverName())
                .orElse(new Conversation(messageDTO.getReceiverName(), new ArrayList<>()));

        senderMessage.setConversation(senderConversation);
        senderMessage.setMessageBody(encryptor.encrypt(messageDTO.getMessageBody()));

        receiverMessage.setConversation(receiverConversation);
        receiverMessage.setMessageBody(encryptor.encrypt(messageDTO.getMessageBody()));

        conversationRepository.save(senderConversation);
        messageRepository.save(senderMessage);

        conversationRepository.save(receiverConversation);
        messageRepository.save(receiverMessage);

        return messageDTO;
    }

    @Override
    public ConversationsListDTO getConversations(Principal principal) {
        ConversationsListDTO result = new ConversationsListDTO();
        result.setConversationOwner(principal.getName());
        Optional<Conversation> conversation = conversationRepository.findByConversationOwner(principal.getName());
        if (conversation.isPresent()) {
            List<String> participants = new ArrayList<>();
            log.info("conversation is present");

            conversation.get().getMessages().forEach(message -> {
                participants.add(message.getReceiverName());
                participants.add(message.getSenderName());
            });

            Set<String> participantsSet = new HashSet<>(participants);
            participantsSet.remove(principal.getName());
            participants.clear();
            participants.addAll(participantsSet);
            participants.forEach(participant -> log.info(participant));
            result.setConversationsParticipants(participants);
        }
        return result;
    }

    @Override
    public List<MessageDTO> getMessages(Principal principal, String conversationMember) {
        Optional<Conversation> conversation = conversationRepository.findByConversationOwner(principal.getName());
        if (conversation.isPresent()) {
            List<MessageDTO> messages = new ArrayList<>();
            List<Message> filteredList = conversation.get().getMessages()
                            .stream()
                            .filter(message -> message.getReceiverName().equals(conversationMember) || message.getSenderName().equals(conversationMember))
                            .collect(Collectors.toList());
                    filteredList.forEach(message -> {
                        String messageBody = message.getMessageBody();
                        message.setMessageBody(encryptor.decrypt(messageBody));
                        messages.add(new MessageDTO(message));
                    });
            return messages;
        }
        return new ArrayList<>();
    }
}
