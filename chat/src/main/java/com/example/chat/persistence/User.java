package com.example.chat.persistence;

public record User(Integer id,
                    String first_name,
                    String second_name,
                    String email,
                    String password) {
}
