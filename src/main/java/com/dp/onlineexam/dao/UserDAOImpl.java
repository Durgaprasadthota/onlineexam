package com.dp.onlineexam.dao;

import com.dp.onlineexam.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDAOImpl implements  IUserDAO{
    private final JdbcTemplate jdbcTemplate;
    final String sql = """
                INSERT INTO users (email, password, phone_number, address)
                VALUES (?, ?, ?, ?)
                """;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String saveUser(User user) {
        try {
            jdbcTemplate.update(
                    sql,
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhoneNumber(),
                    user.getAddress()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return "User registered successfully!";
    }
}
