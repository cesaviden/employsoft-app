package com.app.employsoft.auth.services;

import com.app.employsoft.auth.entities.UserEntity;
import com.app.employsoft.auth.repositories.UserDAO;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Iterable<UserEntity> getAllUsers() {
        return userDAO.findAll();
    }

    public UserEntity getUserById(Long id) {
        Optional<UserEntity> userOptional = userDAO.findById(id);
        return userOptional.orElse(null);
    }

    public UserEntity updateUser(Long id, UserEntity user) {
        Optional<UserEntity> userOptional = userDAO.findById(id);
        if (userOptional.isPresent()) {
            UserEntity existingUser = userOptional.get();
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEnabled(user.isEnabled());
            existingUser.setAccountNoExpired(user.isAccountNoExpired());
            existingUser.setAccountNoLocked(user.isAccountNoLocked());
            existingUser.setCredentialNoExpired(user.isCredentialNoExpired());
            existingUser.setRoles(user.getRoles()); // Actualizar roles según sea necesario
            return userDAO.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }
}
