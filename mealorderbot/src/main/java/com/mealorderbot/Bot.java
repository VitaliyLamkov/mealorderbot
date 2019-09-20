package com.mealorderbot;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mealorderbot.dao.ProductDao;
import com.mealorderbot.entites.Product;




public class Bot  extends TelegramLongPollingBot{
//	 ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
	 
	public void onUpdateReceived(Update update) {
		System.out.println("1go!!!!!!!!!!!!");
		// TODO Auto-generated method stub
		if (update.hasMessage() && update.getMessage().hasText()) {
//	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
//	        		.setChatId(update.getMessage().getChatId())
//	                .setText(update.getMessage().getText());
	        String txt = update.getMessage().getText();
	        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
	        setButtons(message);
	        message.enableMarkdown(true);
	        
//	        message.setReplyMarkup(replyKeyboardMarkup);
	        
	      
			if (txt.equals("/start")) {
				
		        
				message.setChatId(update.getMessage().getChatId());
				String str = "!!!";
				try {
				ProductDao productDao = new ProductDao();
					productDao.add();
					productDao.add();
					productDao.add();
					str = productDao.list();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					str = e.getMessage();
				}
//				
				
				message.setText("Hello, world! This is simple bot! Круто бот работает" + str);

			}
	        try {
//	        	setButtons(message);
	            execute(message); // Call method to send the message
	            
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	}
		

	}
	public synchronized void setButtons(SendMessage sendMessage) {
        
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Оформить новый заказ"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Просмотреть последние 5 заказов "));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
	public String getBotUsername() {
		System.out.println("2go!!!!!!!!!!!!");
		return "mealorderbot";
	}


	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		System.out.println("3go!!!!!!!!!!!!");
		return "845662715:AAHGg6EeEkc1QOitAhsNfPEH5NDp8-ZFZ-U";
	}
}
