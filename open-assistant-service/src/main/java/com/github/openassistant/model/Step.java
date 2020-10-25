package com.github.openassistant.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document
public class Step {

    private UUID id;

    private String value;
}
