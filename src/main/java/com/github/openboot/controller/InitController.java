package com.github.openboot.controller;

import com.github.openboot.service.CsvParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/init")
@RequiredArgsConstructor
public class InitController {

    private final CsvParserService parserService;

    @GetMapping
    public List<String> init() {
        return parserService.initDB();
    }
}
