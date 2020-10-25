package com.github.openassistant.service.impl;

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

    @Override
    public Question search(String question) {
        Question goodQuestion =  questionRepository.findFirstByQuestion(question);
        if (goodQuestion != null) {
            return goodQuestion;
        }

        // Convert a bad question to list of the similar questions
        QuestionObject questionObject = restTemplate.getForObject(NplUtil.URI + question,
                QuestionObject.class);

        if (questionObject == null) {
            throw new QuestionNotFoundException(question);
        }

        if (Double.parseDouble(questionObject.getDistance()) < 0.5) {
            throw new QuestionNotFoundException(question);
        }

        return questionRepository.findFirstByQuestion(questionObject.getValue());
    }
}
