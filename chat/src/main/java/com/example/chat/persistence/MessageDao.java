package com.example.chat.persistence;

import java.util.List;

public interface MessageDao {
    int addMessage(Message message);
    List<Message> getLazyMessagesByDate(int conversation_id, int upper, int lower);
    int deleteMessage(int id);

}
