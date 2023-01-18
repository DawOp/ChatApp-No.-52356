package com.example.chat.persistence.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Data Mapper Pattern */
@Repository
public class MessageGateway implements MessageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addMessage(Message message) {
        var sql = """ 
                INSERT INTO MESSAGES (sender_id, conversation_id, text, created_at)
                VALUES (?,?,?,?)
                """;
        return jdbcTemplate.update(sql,message.sender_id(),message.conversation_id(),message.text(),message.created_at());
    }
    /* Lazy Loading Pattern*/
    @Override
    public List<Message> getLazyMessagesByDate(int conversation_id, int lower, int upper) {
        var sql = """ 
                SELECT *
                FROM MESSAGES
                WHERE conversation_id = ?
                ORDER BY created_at
                LIMIT ? OFFSET ?
                """;
        return jdbcTemplate.query(sql,new MessageMapper(),conversation_id,upper,lower);
    }

    @Override
    public int deleteMessage(int id) {
        var sql = """ 
                DELETE FROM MESSAGES
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql,id);
    }
}
