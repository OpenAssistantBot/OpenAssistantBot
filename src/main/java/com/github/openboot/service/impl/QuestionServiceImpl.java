package com.github.openboot.service.impl;

import com.github.openboot.exception.QuestionNotFoundException;
import com.github.openboot.model.Question;
import com.github.openboot.model.QuestionObject;
import com.github.openboot.repository.QuestionRepository;
import com.github.openboot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final RestTemplate restTemplate;
    private final String aiServiceUrl = "http://localhost:8081/";

    @Override
    public Question search(String question) {
        Question goodQuestion =  questionRepository.findByQuestion(question);
        if (goodQuestion != null) {
            return goodQuestion;
        }

        // Convert a bad question to list of the similar questions
        QuestionObject[] questionObjects = restTemplate.getForEntity(aiServiceUrl, QuestionObject[].class).getBody();

        if (questionObjects == null) {
            throw new QuestionNotFoundException(question);
        }

        QuestionObject questionObject = Arrays.stream(questionObjects)
                .filter(q -> q.getDistance() > 0.90)
                .findFirst()
                .orElseThrow(() -> {
                    throw new QuestionNotFoundException(question);
                });

        return questionRepository.findByQuestion(questionObject.getValue());
    }
}
