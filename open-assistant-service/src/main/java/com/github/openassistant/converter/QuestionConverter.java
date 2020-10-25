package com.github.openassistant.converter;

import com.github.openassistant.model.Step;
import com.github.openassistant.dto.QuestionDto;
import com.github.openassistant.model.Question;

public class QuestionConverter {

    public static QuestionDto convertFromModelToDto(Question question) {
        if (question == null) throw new RuntimeException();

        int count = 1;
        StringBuilder answer = new StringBuilder();

        answer.append(question.getNumber()).append(". ").append(question.getQuestion()).append("?<br>");
        answer.append("Запрос: ").append(question.getQuery()).append("<br>");
        answer.append("Тип ДБО: ").append(question.getType()).append("<br>").append("<br>").append("<br>");
        answer.append("Шаги: ").append(question.getType()).append("<br>");

        for (Step step : question.getSteps()) {
            answer.append(count++).append(". ").append(step.getValue()).append("<br>");
        }

        return new QuestionDto() {{ setAnswer(answer.toString()); }};
    }
}
