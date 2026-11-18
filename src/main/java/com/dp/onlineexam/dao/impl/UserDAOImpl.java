package com.dp.onlineexam.dao.impl;

import com.dp.onlineexam.dao.IUserDAO;
import com.dp.onlineexam.models.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements IUserDAO {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private PasswordEncoder passwordEncoder;
    final String registerUserSQL = """
                INSERT INTO users (email, password, phone_number, admin, address)
                 VALUES (:email, :password, :phoneNumber, :admin,:address)
                """;

    public UserDAOImpl(NamedParameterJdbcTemplate namedJdbcTemplate,PasswordEncoder passwordEncoder) {
        this.namedParameterJdbcTemplate = namedJdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String saveUser(User user) {
        try {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            namedParameterJdbcTemplate.update(
                    registerUserSQL,
                    new BeanPropertySqlParameterSource(user)
            );
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return "User registered successfully!";
    }

 @Override
    public boolean isAuthenticatedUser(User user) {
     String sql = """
            SELECT password
            FROM users
            WHERE email = :email;
            """;

     MapSqlParameterSource params = new MapSqlParameterSource();
     params.addValue("email", user.getEmail());

   List<String> passwords = namedParameterJdbcTemplate.query(
             sql,
             params,
             (rs, rowNum) -> rs.getString("password")
     );

     if (passwords.isEmpty()) {
         return false;
     }

     String encodedPassword = passwords.get(0);
     boolean row = passwordEncoder.matches(
             user.getPassword(),
             encodedPassword
     );

     return passwordEncoder.matches(
             user.getPassword(),
             encodedPassword
     );

    }

    @Override
    public boolean isUserRegistered(String email) {
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
