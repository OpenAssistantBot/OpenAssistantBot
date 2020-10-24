package com.github.openboot.service;

import java.util.List;

public interface CsvParserService {

    List<String> initDB();

    List<String> updateDB(String path);
}
