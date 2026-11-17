package com.dp.onlineexam.dao.impl;

import com.dp.onlineexam.dao.IUserDAO;
import com.dp.onlineexam.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements IUserDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    final String sql = """
                INSERT INTO users (email, password, phone_number, address)
                 VALUES (:email, :password, :phoneNumber, :address)
                """;

    public UserDAOImpl(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
    }

    @Override
    public String saveUser(User user) {
        try {
            namedParameterJdbcTemplate.update(
                    sql,
                    new BeanPropertySqlParameterSource(user)
            );
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return "User registered successfully!";
    }

 @Override
    public boolean IsAuthenciatedUser(User user) {
     String sql = """
            SELECT COUNT(*)
            FROM users
            WHERE email = :email
            AND password = :password
            """;

     MapSqlParameterSource params = new MapSqlParameterSource();
     params.addValue("email", user.getEmail());
     params.addValue("password", user.getPassword());

     Integer count = namedParameterJdbcTemplate.queryForObject(
             sql,
             params,
             Integer.class
     );

     return count != null && count > 0;

    }

    @Override
    public boolean IsUserRegistered(String email) {
        String sql = """
            SELECT COUNT(*)
            FROM users
            WHERE email = :email
            """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        Integer count = namedParameterJdbcTemplate.queryForObject(
                sql,
                params,
                Integer.class
        );

        return count != null && count > 0;

    }
}
