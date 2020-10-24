package com.github.openboot.controller;

import com.github.openboot.service.CsvParserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"Database"})
@Deprecated
@RestController
@RequestMapping("/database")
@RequiredArgsConstructor
public class DatabaseController {

    private final CsvParserService parserService;

    @Deprecated
    @ApiOperation(value = "Init database with questions.")
    @GetMapping("/init")
    public List<String> init() {
        return parserService.initDatabase();
    }

    @Deprecated
    @ApiOperation(value = "Delete all questions from database.")
    @GetMapping("/drop")
    public List<String> drop() {
        return parserService.initDatabase();
    }
}
