package com.aromero.videoserver.services.user.service;

import com.aromero.videoserver.services.user.model.User;
import com.aromero.videoserver.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service(UserServiceImpl.BEAN_NAME)
public class UserServiceImpl implements UserService {
    public static final String BEAN_NAME = "UserService";

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String username) {
        Optional<User> result = this.userRepository.findByUsername(username);
        if (result.isPresent()) {
            return result.get();
        } else {
            return new User(0, "", "", new LinkedList<>());
        }
    }

    @Override
    public User saveUser(User user) {
       return this.userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
