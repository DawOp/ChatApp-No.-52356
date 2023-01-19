package com.example.chat.service;

import com.example.chat.dto.RemoteContact;
import com.example.chat.persistence.contact.Contact;
import com.example.chat.persistence.user.User;

import com.example.chat.persistence.contact.ContactGateway;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
//    private
    private final ContactGateway contactGateway;

    public ContactService(ContactGateway contactGateway) {
        this.contactGateway = contactGateway;
    }

    public List<RemoteContact> getContacts(Integer id, UserService userService) {
        List<Integer> contacts = contactGateway.selectContactsByOwnerId(id).stream().
                map(Contact::user_id).toList();

        List<User> users = contacts.stream().
                map(c -> userService.getUsers(c).get()).toList();

        return users.stream().
                map(c -> new RemoteContact(c.id(),c.first_name()
                        + " " + c.second_name() + " " +c.email())).toList();
    }

    public int addContact(Integer owner_id, String email, UserService userService) {
        // TODO
        var user = userService.getUserByEmail(email);

        if (user.isPresent()) {
            Integer user_id = user.get().id();
            Contact contact = new Contact(0, owner_id, user_id);
            contactGateway.addContact(contact);
        }

        return 0;
    }
}
