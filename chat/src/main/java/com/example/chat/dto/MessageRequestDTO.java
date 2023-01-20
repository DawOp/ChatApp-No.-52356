package com.example.chat.dto;
import java.sql.Timestamp;

public record MessageRequestDTO(String text,
                                Integer conversation_id) {

}
