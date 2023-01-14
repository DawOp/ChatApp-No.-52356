package com.example.chat.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

/* Row Data Gateway */
public class ConversationGateway {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addConversation(Conversation conversation) {
        var sql = """ 
                INSERT INTO CONVERSATIONS (name, contact_id)
                VALUES (?,?)
                """;
        return jdbcTemplate.update(sql,conversation.name(),conversation.contact_id());
    }

    public int deleteConversation(int id) {
        var sql = """ 
                DELETE FROM CONVERSATIONS
                WHERE id=?
                """;
        return jdbcTemplate.update(sql,id);
    }

    public Optional<Conversation> selectConversationByContactId(int contact_id) {
        var sql = """ 
                SELECT *
                FROM CONVERSATIONS
                WHERE contact_id = ?
                """;
        return jdbcTemplate.query(sql,new ConversationMapper(),contact_id).stream().findFirst();
    }

    public int updateNameConversation(Conversation conversation) {
        var sql = """ 
                UPDATE CONVERSATIONS
                SET name = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql,conversation.name(),conversation.id());
    }
}
