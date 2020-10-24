package com.github.openboot.converter;

import com.github.openboot.dto.QuestionDto;
import com.github.openboot.model.Question;
import com.github.openboot.model.Step;

import java.util.stream.Collectors;

public class QuestionConverter {

    public static QuestionDto convertFromModelToDto(Question question) {
        int count = 1;
        StringBuilder answer = new StringBuilder();
        answer.append(question.getNumber()).append(". ").append(question.getQuestion()).append("<br>");
        answer.append("Запрос: ").append(question.getQuery()).append("<br>");
        answer.append("Тип ДБО: ").append(question.getType()).append("<br>").append("<br>").append("<br>");
        answer.append("Шаги: ").append(question.getType()).append("<br>");
        for (Step step : question.getSteps()) {
            answer.append(count++).append(". ").append(step.getValue()).append("<br>");
        }
        return new QuestionDto() {{ setAnswer(answer.toString()); }};
    }
}
