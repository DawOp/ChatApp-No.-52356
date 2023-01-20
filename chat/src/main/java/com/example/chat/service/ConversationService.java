package com.example.chat.service;

import com.example.chat.persistence.conversation.Conversation;
import com.example.chat.persistence.conversation.ConversationGateway;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {
    public final ConversationGateway conversationGateway;

    public ConversationService(ConversationGateway conversationGateway) {
        this.conversationGateway = conversationGateway;
    }

    public Conversation getConversation(Integer contact_id) {
        return conversationGateway.selectConversationById(contact_id).orElse(null);
    }

    public int addConversation(Conversation conversation) {
        return conversationGateway.addConversation(conversation);
    }
}
