package com.talkactive.taconversationsstorage.service;

import com.talkactive.taconversationsstorage.model.*;
import com.talkactive.taconversationsstorage.repository.ConversationRepository;
import com.talkactive.taconversationsstorage.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ConversationServiceImpl implements ConversationService {

    private ConversationRepository conversationRepository;
    private MessageRepository messageRepository;

    public ConversationServiceImpl(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

//    @Override
//    public MessageDTO save(MessageDTO messageDTO) {
//
//        Message senderMessage = new Message(messageDTO);
//        Message receiverMessage = new Message(messageDTO);
//
//        Conversation senderConversation = conversationRepository.findByConversationOwnerAndConversationMember(messageDTO.getSenderName(), messageDTO.getReceiverName())
//                .orElse(new Conversation(messageDTO.getSenderName(), messageDTO.getReceiverName(), new ArrayList<>()));
//        senderConversation.getMessages().add(senderMessage);
//
//        Conversation receiverConversation = conversationRepository.findByConversationOwnerAndConversationMember(messageDTO.getReceiverName(), messageDTO.getSenderName())
//                .orElse(new Conversation(messageDTO.getReceiverName(), messageDTO.getSenderName(), new ArrayList<>()));
//        receiverConversation.getMessages().add(receiverMessage);
//
//        senderMessage.setConversation(senderConversation);
//        receiverMessage.setConversation(receiverConversation);
//
//        conversationRepository.save(senderConversation);
//        messageRepository.save(senderMessage);
//
//        conversationRepository.save(receiverConversation);
//        messageRepository.save(receiverMessage);
//
//        return messageDTO;
//    }


    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        Message senderMessage = new Message(messageDTO);
        Message receiverMessage = new Message(messageDTO);

        Conversation senderConversation = conversationRepository.findByConversationOwner(messageDTO.getSenderName())
                .orElse(new Conversation(messageDTO.getSenderName(), new ArrayList<>()));

        Conversation receiverConversation = conversationRepository.findByConversationOwner(messageDTO.getReceiverName())
                .orElse(new Conversation(messageDTO.getReceiverName(), new ArrayList<>()));

        senderMessage.setConversation(senderConversation);
        receiverMessage.setConversation(receiverConversation);

        conversationRepository.save(senderConversation);
        messageRepository.save(senderMessage);

        conversationRepository.save(receiverConversation);
        messageRepository.save(receiverMessage);

        return messageDTO;
    }

//    @Override
//    public List<ConversationDTO> getConversations(Principal principal) {
//        List<ConversationDTO> conversations = new ArrayList<>();
//        conversationRepository.findAllByConversationOwner(principal.getName())
//                .forEach(conversation -> {
//                    conversations.add(new ConversationDTO(conversation.getConversationOwner()/*, conversation.getConversationMember()*/));
//                });
//        if (!conversations.isEmpty()) return conversations;
//        else return null;
//    }


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
                        messages.add(new MessageDTO(message));
                    });
            return messages;
        }

//        if (conversation.isPresent()) {
//            List<MessageDTO> messages;
////            conversation.get().getMessages().forEach(message -> {
////                messages.add(new MessageDTO(message));
////            });
//            messages = conversation.get().getMessages()
//                    .stream()
//                    .filter(message -> message.getReceiverName().equals(conversationMember))
//                    .map(MessageDTO::new)
//                    .collect(Collectors.toList());
//            messages.forEach(messageDTO -> log.info(messageDTO.toString()));
//            return messages;
//        }

        return new ArrayList<>();
    }

//    @Override
//    public List<MessageDTO> getMessages(Principal principal, String conversationMember) {
//        Optional<Conversation> conversation = conversationRepository.findByConversationOwnerAndConversationMember(principal.getName(), conversationMember);
//        List<MessageDTO> messageDTOS = new ArrayList<>();
//        if (conversation.isPresent()) {
//            log.info("conversation is present");
//            List<Message> messages = conversation.get().getMessages();
//            messages.forEach(message -> {
//                messageDTOS.add(new MessageDTO(message));
//            });
//            messageDTOS.forEach(message -> {
//                log.info(message.toString());
//            });
//            return messageDTOS;
//        } else return new ArrayList<>();
//    }
}
