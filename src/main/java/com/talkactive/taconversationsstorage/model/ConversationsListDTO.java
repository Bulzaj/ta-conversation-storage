package com.talkactive.taconversationsstorage.model;

import java.util.ArrayList;
import java.util.List;

public class ConversationsListDTO {

    private String conversationOwner;
    private List<String> conversationsParticipants = new ArrayList<>();

    public String getConversationOwner() {
        return conversationOwner;
    }

    public ConversationsListDTO() {
    }

    public ConversationsListDTO(String conversationOwner, List<String> conversationsParticipants) {
        this.conversationOwner = conversationOwner;
        this.conversationsParticipants = conversationsParticipants;
    }

    public void setConversationOwner(String conversationOwner) {
        this.conversationOwner = conversationOwner;
    }

    public List<String> getConversationsParticipants() {
        return conversationsParticipants;
    }

    public void setConversationsParticipants(List<String> conversationsParticipants) {
        this.conversationsParticipants = conversationsParticipants;
    }
}
