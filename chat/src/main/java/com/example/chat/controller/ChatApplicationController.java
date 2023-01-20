package com.example.chat.controller;

import com.example.chat.dto.*;
import com.example.chat.persistence.conversation.Conversation;
import com.example.chat.persistence.message.Message;
import com.example.chat.service.ContactService;
import com.example.chat.service.ConversationService;
import com.example.chat.service.MessageService;
import com.example.chat.service.UserService;
import com.example.chat.util.SignUp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ChatApplicationController {
    private final UserService userService;

    private final ContactService contactService;

    private final ConversationService conversationService;

    private final MessageService messageService;

    public ChatApplicationController(UserService userService,
                                     ContactService contactService,
                                     ConversationService conversationService,
                                     MessageService messageService) {
        this.userService = userService;
        this.contactService = contactService;
        this.conversationService = conversationService;
        this.messageService = messageService;
    }

    @PostMapping("/login")
    public UserDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO);
    }

    @PostMapping("/signup")
    public int signup(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return userService.createUser(registerRequestDTO) == SignUp.SUCCESS ? 200 : 401;
    }

    @CrossOrigin
    @GetMapping("{id}/contacts")
    public List<RemoteContact> getContacts(@PathVariable Integer id) {
        return contactService.getContacts(id, userService);
    }

    @CrossOrigin
    @PostMapping("{id}/contact")
    public int addContact(@PathVariable Integer id, @RequestBody String email) {
        return contactService.addContact(id,email,userService);
    }

    @CrossOrigin
    @PostMapping("{id}/conversation")
    public int createConversation(@PathVariable Integer id, @RequestBody Conversation conversation) {
        return conversationService.addConversation(conversation);
    }
    @CrossOrigin
    @GetMapping("{id}/conversation/{contact_id}")
    public Conversation getConversation(@PathVariable Integer id, @PathVariable Integer contact_id) {
        return conversationService.getConversation(contact_id);
    }

    @CrossOrigin
    @GetMapping("{id}/message/{contact_id}")
    public List<Message> getMessage(@PathVariable Integer id, @PathVariable Integer contact_id) {
        return messageService.getMessages(contact_id);
    }

    @CrossOrigin
    @PostMapping("{id}/message")
    public int sendMessage(@PathVariable Integer id, @RequestBody MessageRequestDTO conversation) {
        return messageService.sendMessage(id,conversation);
    }
}
