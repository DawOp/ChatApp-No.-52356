package com.example.chat.persistence;

import java.util.List;

/* Data Mapper Pattern */
public interface ContactDao {
    int addContact(Contact contact);
    List<Contact> selectContactsByOwnerId(int owner_id);
    int deleteContact(int id);
}
