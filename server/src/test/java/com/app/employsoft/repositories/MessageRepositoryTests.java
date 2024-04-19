package com.app.employsoft.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.employsoft.api.entities.Message;
import com.app.employsoft.api.repositories.MessageDAO;
import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageRepositoryTests {

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private UserDAO userDAO;


    @Test
    @Order(1)
    void findAllTest() {
        List<Message> messages = messageDAO.findAll();
        assertEquals(3, messages.size());
    }


    @Test
    @Order(2)
    void findByIdTest() {
        Message message = messageDAO.findById(1L).get();
        assertEquals("Message 1 content", message.getContent());
    }


    @Test
    @Order(3)
    void findBySenderTest() {
        UserEntity sender = userDAO.findById(1L).get();
        List<Message> messages = messageDAO.findBySender(sender);
        assertEquals(1, messages.size());
    }
}