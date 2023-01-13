package com.example.chat.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

/* Table Data Gateway Pattern */
public class UserGateway {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> selectUsers() {
        var sql = """
                SELECT *
                FROM USERS""";
        return jdbcTemplate.query(sql,new UserMapper());
    }

    public int addUser(User user) {
        var sql = """ 
                INSERT INTO USERS (first_name, second_name, email, password)
                VALUES (?,?,?,crypt(?,gen_salt('bf')))
                """;

        return jdbcTemplate.update(sql,user.first_name(),user.second_name(),user.email(),user.password());
    }

    public Optional<User> getUserById(int id) {
        // TODO
        var sql = """
                SELECT *
                FROM USERS
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql,new UserMapper(),id).stream().findFirst();
    }
}
