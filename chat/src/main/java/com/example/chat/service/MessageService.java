package com.example.chat.service;

import com.example.chat.dto.MessageRequestDTO;
import com.example.chat.persistence.message.Message;
import com.example.chat.persistence.message.MessageGateway;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class MessageService {
    private final MessageGateway messageGateway;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MessageService(MessageGateway messageGateway) {
        this.messageGateway = messageGateway;
    }

    public List<Message> getMessages(Integer conversation_id) {
        return messageGateway.getLazyMessagesByDate(conversation_id,0,100);
    }

    public int sendMessage(Integer id, MessageRequestDTO messageDto) {
        System.out.println(messageDto.text());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Message message = new Message(0,
                id,
                messageDto.conversation_id(),
                messageDto.text(),
                timestamp
        );

        messageGateway.addMessage(message);
        return 0;
    }
}
