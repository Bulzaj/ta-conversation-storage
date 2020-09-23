package com.talkactive.taconversationsstorage.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String conversationOwner;

//    private String conversationMember;

    @OneToMany(
            mappedBy = "conversation",
            cascade = CascadeType.ALL
    )
    private List<Message> messages;

    public Conversation() {
    }

    public Conversation(String conversationOwner, /*String conversationMember,*/ List<Message> messages) {
        this.conversationOwner = conversationOwner;
//        this.conversationMember = conversationMember;
        this.messages = messages;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getConversationOwner() {
        return conversationOwner;
    }

    public void setConversationOwner(String conversationOwner) {
        this.conversationOwner = conversationOwner;
    }

//    public String getConversationMember() {
//        return conversationMember;
//    }

//    public void setConversationMember(String conversationMember) {
//        this.conversationMember = conversationMember;
//    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}