package com.example.chat.persistence.contact;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Contact(
                resultSet.getInt("id"),
                resultSet.getInt("owner_id"),
                resultSet.getInt("user_id")
        );
    }
}