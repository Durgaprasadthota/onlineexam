package com.dp.onlineexam.dao;

import com.dp.onlineexam.models.User;

public interface IUserDAO {
    String saveUser(User user);
    boolean isAuthenticatedUser(User user);
    public boolean isUserRegistered(String email);
}
