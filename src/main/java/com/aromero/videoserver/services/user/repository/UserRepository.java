package com.aromero.videoserver.services.user.repository;

import com.aromero.videoserver.services.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
