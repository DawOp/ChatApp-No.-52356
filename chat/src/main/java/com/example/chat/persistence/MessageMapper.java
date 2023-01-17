package com.example.chat.persistence;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MessageMapper implements RowMapper<Message> {
    @Override
    public Message mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Message(
                resultSet.getInt("id"),
                resultSet.getInt("sender_id"),
                resultSet.getInt("conversation_id"),
                resultSet.getString("text"),
                LocalDate.parse(resultSet.getString("created_at"))
        );
    }
}