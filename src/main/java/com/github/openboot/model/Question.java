package com.github.openboot.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class Question {

    private UUID id;

    private String question;

    private String clarification;

    private String type;

    private String query;

    private List<String> steps;
}
