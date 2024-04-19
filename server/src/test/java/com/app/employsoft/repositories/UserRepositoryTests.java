package com.app.employsoft.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    void findAllTest() {
        List<UserEntity> users = userDAO.findAll();
        assertEquals(4, users.size());
    }

    @Test
    @Order(2)
    void findByIdTest() {
        UserEntity user = userDAO.findById(1L).get();
        assertEquals("Santiago", user.getName());
    }

    @Test
    @Order(3)
    void findByUsernameTest() {
        Optional<UserEntity> user = userDAO.findByUsername("santiago");
        assertEquals("santiago", user.get().getUsername());
    }

}
