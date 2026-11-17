package com.dp.onlineexam.init;

import com.dp.onlineexam.dao.IUserDAO;
import com.dp.onlineexam.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class OnlineExamAdminDataInitializer implements CommandLineRunner {

    private final IUserDAO userDao;
    private final PasswordEncoder passwordEncoder;

    public OnlineExamAdminDataInitializer(
            IUserDAO userDao,
            PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        String email = "admin@onlineexam.com";

        if (userDao.IsUserRegistered(email)) {
            throw new Exception("User already exist in the db");
        }
        String encodedPassword = passwordEncoder.encode("Admin@123");
        User admin = new User();
        admin.setEmail(email);
        admin.setPassword(encodedPassword);
        admin.setPhoneNumber("+919876543210");
        admin.setAddress("HYD");
        admin.setRole("ADMIN");

        userDao.saveUser(admin);

    }
}