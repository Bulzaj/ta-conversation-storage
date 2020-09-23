package com.talkactive.taconversationsstorage.model;

public class MessageDTO {

    private String senderName;
    private String receiverName;
    private Long createdAt;
    private String messageBody;
    private String messageType;

    public MessageDTO() {
    }

    public MessageDTO(String senderName, String receiverName, Long createdAt, String messageBody, String messageType) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.createdAt = createdAt;
        this.messageBody = messageBody;
        this.messageType = messageType;
    }

    public MessageDTO(Message message) {
        this.senderName = message.getSenderName();
        this.receiverName = message.getReceiverName();
        this.createdAt = message.getCreatedAt();
        this.messageBody = message.getMessageBody();
        this.messageType = message.getMessageType();
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "senderName='" + senderName + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", createdAt=" + createdAt +
                ", messageBody='" + messageBody + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
