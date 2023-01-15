package com.example.chat.persistence;

public record Contact(Integer id,
                      Integer owner_id,
                      Integer user_id) {
}
