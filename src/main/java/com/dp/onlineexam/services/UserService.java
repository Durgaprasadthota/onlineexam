package com.dp.onlineexam.services;

import com.dp.onlineexam.dao.IUserDAO;
import com.dp.onlineexam.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    IUserDAO userDAO;
    public UserService(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    public String saveUser(User user)
    {
        return this.userDAO.saveUser(user);
    }

    public boolean IsAuthenciatedUser(User user)
    {
        return this.userDAO.IsAuthenciatedUser(user);
    }
}
