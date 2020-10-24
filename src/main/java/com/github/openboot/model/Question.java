package com.github.openboot.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Question {

    @Id
    private String id;

    private Long number;

    private String question;

    private String clarification;

    private String type;

    private String query;

    private List<Step> steps;

    private List<String> links;
}
