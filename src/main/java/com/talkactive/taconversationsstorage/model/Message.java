package com.talkactive.taconversationsstorage.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String messageBody;
    private String senderName;
    private Long createdAt;

    @ManyToOne
    private Conversation conversation;

    public Message() {
    }

    public Message(String messageBody, Long createdAt, String messageType, String senderName, Conversation conversation) {
        this.messageBody = messageBody;
        this.createdAt = createdAt;
        this.senderName = senderName;
        this.conversation = conversation;
    }

    public Message(MessageDTO messageDTO) {
        this.messageBody = messageDTO.getMessageBody();
        this.senderName = messageDTO.getSenderName();
        this.createdAt = messageDTO.getCreatedAt();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
