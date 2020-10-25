package com.github.openassistant.repository;

import com.github.openassistant.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends MongoRepository<Question, UUID> {

    Question findFirstByQuestion(String question);
}
