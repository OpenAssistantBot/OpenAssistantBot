package com.github.openassistant.service;

import java.util.List;

public interface CsvParserService {

    List<String> initDatabase();

    void deleteAll();
}
