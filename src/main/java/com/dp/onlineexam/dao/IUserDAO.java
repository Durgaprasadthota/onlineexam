package com.dp.onlineexam.dao;

import com.dp.onlineexam.models.User;

public interface IUserDAO {
    String saveUser(User user);
    boolean IsAuthenciatedUser(User user);
    public boolean IsUserRegistered(String email);
}
