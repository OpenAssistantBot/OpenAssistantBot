package com.github.openassistant.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.openassistant.model.QuestionObject;
import com.github.openassistant.util.NplUtil;
import com.github.openassistant.exception.QuestionNotFoundException;
import com.github.openassistant.model.Question;
import com.github.openassistant.repository.QuestionRepository;
import com.github.openassistant.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Question search(String question) {
        Question goodQuestion =  questionRepository.findFirstByQuestion(question);
        if (goodQuestion != null) {
            return goodQuestion;
        }

        // Convert a bad question to list of the similar questions
        String result = restTemplate.getForObject(NplUtil.URI + question, String.class);

        QuestionObject questionObject = null;
        try {
            questionObject = objectMapper.readValue(result, QuestionObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (questionObject == null) {
            throw new QuestionNotFoundException(question);
        }

        if (Double.parseDouble(questionObject.getDistance()) < 0.5) {
            throw new QuestionNotFoundException(question);
        }

        return questionRepository.findFirstByQuestion(questionObject.getValue());
    }
}
