package com.talkactive.taconversationsstorage.model;

public class ConversationDTO {

    private String conversationOwner;
    private String conversationMember;

    public ConversationDTO(String conversationOwner, String conversationMember) {
        this.conversationOwner = conversationOwner;
        this.conversationMember = conversationMember;
    }

    public String getConversationOwner() {
        return conversationOwner;
    }

    public void setConversationOwner(String conversationOwner) {
        this.conversationOwner = conversationOwner;
    }

    public String getConversationMember() {
        return conversationMember;
    }

    public void setConversationMember(String conversationMember) {
        this.conversationMember = conversationMember;
    }
}
