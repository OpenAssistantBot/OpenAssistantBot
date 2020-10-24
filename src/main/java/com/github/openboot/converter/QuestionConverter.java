package com.github.openboot.converter;

import com.github.openboot.dto.QuestionDto;
import com.github.openboot.model.Question;
import com.github.openboot.model.Step;

import java.util.stream.Collectors;

public class QuestionConverter {

    public static QuestionDto convertFromModelToDto(Question question) {
        int count = 1;
        StringBuilder answer = new StringBuilder();
        for (Step step : question.getSteps()) {
            answer.append(count++).append(". ").append(step.getValue());
        }
        return new QuestionDto() {{ setAnswer(answer.toString()); }};
    }
}
