package com.example.chat.persistence;

import java.time.LocalDate;

public record Message(Integer id,
                      Integer sender_id,
                      Integer conversation_id,
                      String text,
                      LocalDate created_at) {
}
