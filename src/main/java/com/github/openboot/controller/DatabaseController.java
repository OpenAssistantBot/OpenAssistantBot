package com.github.openboot.controller;

import com.github.openboot.service.CsvParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
public class DatabaseController {

    private final CsvParserService parserService;

    @GetMapping("/init")
    public List<String> init() {
        return parserService.initDatabase();
    }

    @GetMapping("/drop")
    public void drop() {
        parserService.deleteAll();
    }
}
