package com.example;

import com.example.model.Data;
import com.example.service.DataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.example.parser.config.StructureCardBuilder.BuildDescription;

/**
 * 
 * This example bot is an echo bot that just repeats the messages sent to him
 *
 */
@Component
class ExampleBot extends TelegramLongPollingBot {

	private static final Logger logger = LoggerFactory.getLogger(ExampleBot.class);
	
	private final String token;
	private final String username;

	ExampleBot(@Value("${bot.token}") String token, @Value("${bot.username}") String username) {
		this.token = token;
		this.username = username;
	}
	
	@Override
	public String getBotToken() {
		return token;
	}
	
	@Override
	public String getBotUsername() {
		return username;
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			Message message = update.getMessage();
			SendMessage response = new SendMessage();

			Long chatId = message.getChatId();
			response.setChatId(String.valueOf(chatId));
			String text = null;
			try {
				text = BuildDescription(message.getText());
			} catch (IOException e) {
				text="Введите валидную ссылку";
			}
			DataServiceImpl dataService = new DataServiceImpl();
			Data data = new Data(text,message.getText());
			try {
				dataService.saveData(data);
			} catch (Exception e) {
				logger.info("Not unique value");
			}
			response.setText(text);
			try {
				execute(response);
				logger.info("Sent message \"{}\" ", chatId);
			} catch (TelegramApiException e) {
				logger.error("Failed to send message \"{}\" due to error: {}", chatId, e.getMessage());
			}
		}
		if (update.getMessage().hasDocument())
		{
			Document document = update.getMessage().getDocument();
			Message message = update.getMessage();
			String file_id = document.getFileId();
			SendMessage response = new SendMessage();
			response.setText(file_id);
			long chatId = message.getChatId();
			response.setChatId(String.valueOf(chatId));
			try {
				execute(response);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
			logger.info("Sent message \"{}\" to {}",chatId, file_id);
		}

	}

	@PostConstruct
	public void start() {
		logger.info("username: {}, token: {}", username, token);
	}

}
