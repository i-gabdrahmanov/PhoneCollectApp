package com.dev.alex.phonecollect.service;

import com.dev.alex.phonecollect.config.BotConfig;
import com.dev.alex.phonecollect.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    final private BotConfig config;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    ExportService exportService;


    @Autowired
    ParserService parserService;

    public TelegramBot(BotConfig config) {
        this.config = config;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId(); // айди для идентификации чата
            String messageText = update.getMessage().getText();

            switch (messageText) {
                case "/start":
                    startCommandRecieved(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/beeline":
                    recieveContent(chatId, OperatorEnum.BEELINE);
                    break;
                case "/megafon":
                    recieveContent(chatId, OperatorEnum.MEGAFON);
                    break;
                case "/mts":
                    recieveContent(chatId, OperatorEnum.MTS);
                    break;
                case "/test":
                    startCommandRecieved(chatId, "Test complete");
                    break;
                default:
                    sendMessage(chatId, "Unsupported command", null);
            }
        }
    }

    private void recieveContent(Long chatId, OperatorEnum operator) {
        String messageToSend = operator.getName();
       // String jsonString = phoneService.collectNumbers(operator);
      // List<Phone> phones = parserService.parse(jsonString, operator);
        List<String> jsonStrings = phoneService.collectNumbers(operator, true);
        List<Phone> phones = new ArrayList<>();
        for (String json:jsonStrings) {
            if (!json.isEmpty()) {
                phones.addAll(parserService.parse(json, operator));
            }
        }
        File file = exportService.exportToXls(phones, operator, LocalDateTime.now().minusDays(2));
        sendMessage(chatId, messageToSend, file);
    }

    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    private void startCommandRecieved(long chatId, String name) {
        String answer = "Hello, " + name;
        sendMessage(chatId, answer, null);
    }

    private void sendMessage(long chatId, String messageToSend, File attachment) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageToSend);
        if (attachment != null) {
            SendDocument sendDocument = new SendDocument();
            sendDocument.setChatId(chatId);
            InputFile inputFile = new InputFile(attachment);
            sendDocument.setDocument(inputFile);
            try {
                execute(sendDocument);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            execute(message);

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
