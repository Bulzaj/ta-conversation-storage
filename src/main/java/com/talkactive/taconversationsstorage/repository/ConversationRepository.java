package com.talkactive.taconversationsstorage.repository;

import com.talkactive.taconversationsstorage.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, UUID> {

    Optional<Conversation> findByConversationOwnerAndConversationMember(String conversationOwner, String conversationMember);
    List<Conversation> findAllByConversationOwner(String conversationOwner);
}
