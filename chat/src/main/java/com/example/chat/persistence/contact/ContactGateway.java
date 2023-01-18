package com.example.chat.persistence.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/* Data Mapper Pattern */
@Repository
public class ContactGateway implements ContactDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addContact(Contact contact) {
        var sql = """ 
                INSERT INTO CONTACTS (owner_id, user_id)
                VALUES (?,?)
                """;
        return jdbcTemplate.update(sql,contact.owner_id(),contact.user_id());
    }

    public List<Contact> selectContactsByOwnerId(int owner_id) {
        var sql = """ 
                SELECT *
                FROM CONTACTS
                WHERE owner_id = ?
                """;
        return jdbcTemplate.query(sql,new ContactMapper(),owner_id);
    }

    public int deleteContact(int id) {
        var sql = """
                DELETE FROM CONTACTS
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql,id);
    }
}
