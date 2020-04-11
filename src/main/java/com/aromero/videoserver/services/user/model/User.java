package com.aromero.videoserver.services.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@Document(collection = "User")
public class User {
    @Id
    Long id;
    String username;
    String encryptedPassword;
    List<Role> roles;

    public User() {
        super();
        this.id = (long) 0;
        this.username = "";
        this.encryptedPassword = "";
        this.roles = new ArrayList<>();
    }

    public User(long id, String username, String encryptedPassword, LinkedList<Role> roles) {
        this.id = id;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.roles = roles;
    }
}
