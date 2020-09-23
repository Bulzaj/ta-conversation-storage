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
    private String receiverName;
    private Long createdAt;
    private String messageType;

    @ManyToOne
    private Conversation conversation;

    public Message() {
    }

    public Message(String messageBody, Long createdAt, String messageType, String senderName, String receiverName,Conversation conversation) {
        this.messageBody = messageBody;
        this.createdAt = createdAt;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.conversation = conversation;
        this.messageType = messageType;
    }

    public Message(MessageDTO messageDTO) {
        this.messageBody = messageDTO.getMessageBody();
        this.senderName = messageDTO.getSenderName();
        this.receiverName = messageDTO.getReceiverName();
        this.messageType = messageDTO.getMessageType();
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

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
