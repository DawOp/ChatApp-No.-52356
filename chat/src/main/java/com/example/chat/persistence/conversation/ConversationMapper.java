package com.example.chat.persistence.conversation;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConversationMapper implements RowMapper<Conversation> {
    @Override
    public Conversation mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Conversation(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("contact_id")
                );
    }
}
