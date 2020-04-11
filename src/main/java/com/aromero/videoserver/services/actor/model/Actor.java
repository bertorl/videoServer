package com.aromero.videoserver.services.actor.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Actor")
public class Actor {

    @Id
    private Long id;
    private String name;
}
