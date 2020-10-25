package com.github.openassistant;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenAssistantBotApplication implements ApplicationRunner {

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
                    bot.execute(new SendMessage(update.message().chat().id(),
                            null));
                }
            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
