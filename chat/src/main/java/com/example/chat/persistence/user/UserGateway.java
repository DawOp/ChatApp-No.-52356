package com.example.chat.persistence.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/* Table Data Gateway Pattern */
@Repository
public class UserGateway {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<User> selectUserById(int id) {
        var sql = """
                SELECT *
                FROM USERS
                WHERE id = ?
                """;
        return jdbcTemplate.query(sql,new UserMapper(),id).stream().findFirst();
    }

    public int addUser(User user) {
        var sql = """ 
                INSERT INTO USERS (first_name, second_name, email, password)
                VALUES (?,?,?,crypt(?,gen_salt('bf')))
                """;

        return jdbcTemplate.update(sql,user.first_name(),user.second_name(),user.email(),user.password());
    }

    public Optional<User> getUserByEmail(String email) {
        var sql = """
                SELECT *
                FROM USERS
                WHERE email = ?
                """;
        return jdbcTemplate.query(sql,new UserMapper(),email).stream().findFirst();
    }

    public Optional<User> authenticateUser(String email, String password) {
        var sql = """
                SELECT *
                FROM USERS
                WHERE email = ?
                AND password = crypt(?, password);
                """;
        return jdbcTemplate.query(sql,new UserMapper(),email,password).stream().findFirst();
    }
}
