package com.app.employsoft.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.employsoft.api.entities.Message;
import com.app.employsoft.auth.entities.UserEntity;

public interface MessageDAO extends JpaRepository<Message, Long> {

    List<Message> findBySender(UserEntity user);

}
