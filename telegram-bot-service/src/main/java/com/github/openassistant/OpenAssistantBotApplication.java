package com.github.openassistant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.openassistant.model.QuestionDto;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@AllArgsConstructor
public class OpenAssistantBotApplication implements ApplicationRunner {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        SpringApplication.run(OpenAssistantBotApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        TelegramBot bot = new TelegramBot("1254092133:AAElmZVuARLTGzmpucdSmZkMPlklqXMqqjg");

        bot.setUpdatesListener(updates -> {
            updates.forEach(System.out::println);

            updates.forEach(update -> {
                if (update.message().text().equals("/start")) {
                    bot.execute(new SendMessage(update.message().chat().id(),
                            "Добро пожаловать, " + update.message().from().firstName() + "!"));
                } else {

                    String result = restTemplate.getForObject(
                            "http://127.0.0.1:9090/api/chat/v1/bot?question=" + update.message().text(), String.class);

                    QuestionDto questionObject = null;
                    try {
                        questionObject = objectMapper.readValue(result, QuestionDto.class);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    if (questionObject != null) {
                        bot.execute(new SendMessage(update.message().chat().id(),
                                questionObject.getAnswer()));
                    } else {
                        bot.execute(new SendMessage(update.message().chat().id(),
                                null));
                    }
                }
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
