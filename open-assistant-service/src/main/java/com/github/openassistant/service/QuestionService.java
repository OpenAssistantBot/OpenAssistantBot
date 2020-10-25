package com.github.openassistant.service;

import com.github.openassistant.model.Question;

public interface QuestionService {

    Question search(String question);
}
