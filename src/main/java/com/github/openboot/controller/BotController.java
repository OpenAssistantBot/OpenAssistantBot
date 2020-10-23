package com.github.openboot.controller;

import com.github.openboot.dto.BotResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class BotController {

    @GetMapping
    public BotResponseDto ask(String question) {
        //...
        return new BotResponseDto();
    }
}
