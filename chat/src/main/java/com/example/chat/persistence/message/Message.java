package com.example.chat.persistence.message;

import java.sql.Timestamp;
import java.time.LocalDate;

public record Message(Integer id,
                      Integer sender_id,
                      Integer conversation_id,
                      String text,
                      Timestamp created_at) {
}
