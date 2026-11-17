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
    private final String adminEmail = "admin@onlineexam.com";

    public OnlineExamAdminDataInitializer(
            IUserDAO userDao,
            PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userDao.IsUserRegistered(adminEmail)) {
            String encodedPassword = passwordEncoder.encode("Admin@123");
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setPassword(encodedPassword);
            admin.setPhoneNumber("+919876543210");
            admin.setAddress("HYD");
            admin.setRole("ADMIN");
            userDao.saveUser(admin);
        }
    }
}