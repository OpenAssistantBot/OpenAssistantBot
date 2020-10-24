package com.github.openboot.controller;

import com.github.openboot.converter.QuestionConverter;
import com.github.openboot.dto.QuestionDto;
import com.github.openboot.model.Question;
import com.github.openboot.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Bot"})
@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotController {

    private final QuestionService questionService;

    @ApiOperation(value = "Ask a question.")
    @GetMapping
    public QuestionDto ask(String question) {
        Question storedQuestion = questionService.search(question);
        QuestionDto response = new QuestionDto();
        if (storedQuestion != null) {
            response = QuestionConverter.convertFromModelToDto(storedQuestion);
        }
        return response;
    }
}
