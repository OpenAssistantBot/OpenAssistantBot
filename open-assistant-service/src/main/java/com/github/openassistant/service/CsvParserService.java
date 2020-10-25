package com.github.openassistant.service;

import java.io.IOException;
import java.util.List;

public interface CsvParserService {

    List<String> initDatabase();

    List<String> updateDatabase(String path);

    void deleteAll();
}
