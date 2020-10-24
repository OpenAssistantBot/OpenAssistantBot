package com.github.openboot.service;

import java.util.List;

public interface CsvParserService {

    List<String> initDatabase();

    List<String> updateDatabase(String path);

    void deleteAll();
}
