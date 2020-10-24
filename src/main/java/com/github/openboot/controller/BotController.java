package com.github.openboot.controller;

import com.github.openboot.dto.QuestionDto;
import com.github.openboot.model.Question;
import com.github.openboot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bot")
public class BotController {

    private final QuestionService questionService;

    @GetMapping
    public QuestionDto ask(String question) {
        //...
        Question storedQuestion = questionService.search(question);

        QuestionDto response = new QuestionDto();

        if (storedQuestion != null) {
            response.setAnswer(storedQuestion.getQuestion() + "\n" + storedQuestion.getSteps());
        }
        return response;
    }
}
