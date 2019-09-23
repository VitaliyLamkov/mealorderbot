package com.mealorderbot;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.core.util.ArrayUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.mealorderbot.dao.OrderDao;
import com.mealorderbot.dao.OrderItemsDao;
import com.mealorderbot.dao.ProductDao;
import com.mealorderbot.entites.Order;
import com.mealorderbot.entites.OrderItems;
import com.mealorderbot.entites.Product;




public class Bot  extends TelegramLongPollingBot{
	
	private static final String botUserName = "mealorderbot";
    private static final String token = "845662715:AAHGg6EeEkc1QOitAhsNfPEH5NDp8-ZFZ-U";
    private static long chatId = 0;
    private static int orderId = 0;
    
    private String telegramId;
	private String firstName;
	private String lastName;
	private String phoneNumber;
    
    public Bot() {
		installBase();
	}
    
    
    
	public void onUpdateReceived(Update update) {
		
		if (isCommand(update)) {
			if (getCommand(update).equals(mainMenu.START.getItemMenu())) {
				try {
					start(update);
				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if (getCommand(update).equals(mainMenu.NEWORDER.getItemMenu())) {
				try {
					newOrder(update);
				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					e.printStackTrace();
					System.out.println("neworder error **************************");
				}
				
			}
			if (getCommand(update).equals(mainMenu.LASTORDERS.getItemMenu())) {
				try {
					lastOrder(update);
				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					System.out.println("lastOrder error **************************");
					e.printStackTrace();
				}
			}
		}
		
		if (update.hasCallbackQuery()) {
			if (getCallbackCommand(update).equals("/posm")) {
				//addItemOrder(update);
			}
		}
		
		if (update.hasCallbackQuery()) {
			if (getCallbackCommand(update).equals("/zak")) {
				//addItemOrder(update);
			}
		}
		
		
		if (update.hasCallbackQuery()) {
			
			String[] data = update.getCallbackQuery().getData().split(":");
			if (data[0].equals("/product")) {
				addItemOrder(update);
			}
		}
		
		if (update.getMessage().getContact() != null) {
			try {
				setContact(update);
			} catch (TelegramApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
	
	
	private void setContact(Update update)throws TelegramApiException {
		
		
		SendMessage sendMessage = new SendMessage();
		//setStartMenu(sendMessage);
		sendMessage.setChatId(update.getMessage().getChatId());
		telegramId = update.getMessage().getContact().getUserID().toString();
		
		firstName = update.getMessage().getContact().getFirstName();
		lastName = update.getMessage().getContact().getLastName();
		phoneNumber = update.getMessage().getContact().getPhoneNumber();
		
		sendMessage.enableMarkdown(true);
		
		
		sendMessage.setText("Приветствуем Вас, " + firstName+ " " + lastName);
		
		execute(sendMessage);
		
	}



	private boolean isCommand(Update update) {
		if (update.hasMessage() && update.getMessage().hasText())
       		return true;
		
		return false;
	}
	
	private  boolean isCallbackQuery(Update update) {
			if (!update.hasCallbackQuery())
				return true;
		return false;
	}
	
	private String getCommand(Update update) {
		
		if (update.hasMessage() && update.getMessage().hasText())
			return update.getMessage().getText();
		return "0";
	}
	
	private String getCallbackCommand(Update update) {
		return  update.getCallbackQuery().getData();
	}
	private void start(Update update) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		setStartMenu(sendMessage);
		sendMessage.setChatId(update.getMessage().getChatId());
		
		sendMessage.enableMarkdown(true);
		sendMessage.setText("Вас приветствует бот заказов!");
		execute(sendMessage);
	}
	
	private void newOrder(Update update) throws TelegramApiException {
		SendMessage sendMessage = new SendMessage();
		setStartMenu(sendMessage);
		sendMessage.setChatId(update.getMessage().getChatId());
		sendMessage.enableMarkdown(true);
		
		
		sendMessage.setText("Новый заказ,  ").setReplyMarkup(setOrderMenu());
		execute(sendMessage);
	}
	
	private void addItemOrder(Update update) {
		
		OrderDao orderDao;
		OrderItemsDao  orderItemsDao = null;
		Product product;
		String[] data = update.getCallbackQuery().getData().split(":");
		try {
			orderDao = new OrderDao();
			if (update.getCallbackQuery().getMessage().getChatId()!=chatId) {
				orderId = orderDao.add(2, new Date(), telegramId);
				chatId = update.getCallbackQuery().getMessage().getChatId();
			}
			
			orderItemsDao.add(orderId, Integer.parseInt(data[1]));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	    long message_id = update.getCallbackQuery().getMessage().getMessageId();
	    
	   // long telegramId = update.getCallbackQuery().getFrom().getId();
	    
        long chat_id = update.getCallbackQuery().getMessage().getChatId();
            String answer = "????? добавлено в заказ";
            EditMessageText new_message = new EditMessageText()
                    .setChatId(chat_id)
                    .setMessageId((int)message_id)
                    .setText(answer)
                    .setReplyMarkup(setOrderMenu());
            try {
                execute(new_message); 
            } catch (TelegramApiException e) {
                e.printStackTrace();
        }
		
	}
	


	
	private void lastOrder(Update update) throws TelegramApiException {
		long message_id = update.getCallbackQuery().getMessage().getMessageId();
		long chat_id = update.getCallbackQuery().getMessage().getChatId();
		
		
        String answer = "";
        
        OrderDao orderDao = null;
        
        answer = orderDao.getFiveOrders(Integer.parseInt(telegramId));
        
        EditMessageText new_message = new EditMessageText()
                .setChatId(chat_id)
                .setMessageId((int)message_id)
                .setText(answer)
                .setReplyMarkup(setOrderMenu());
        try {
            execute(new_message); 
        } catch (TelegramApiException e) {
            e.printStackTrace();
    }
	}
	
	
	public synchronized void setStartMenu(SendMessage sendMessage) {
        
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();

       
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Оформить новый заказ"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Просмотреть последние 5 заказов"));
        
        KeyboardRow keyboardThirdRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Разрешить телефон").setRequestContact(true).setRequestLocation(false));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
	
	public static InlineKeyboardMarkup setOrderMenu() {
		List<Product> products = null;
		ProductDao productDao;
		
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
	    List<List<InlineKeyboardButton>> rowList = new ArrayList<List<InlineKeyboardButton>>();
	    
	    
	    
	    rowList.add(new ArrayList<InlineKeyboardButton>() {{
	    	
	    	add(new InlineKeyboardButton().setText("просмотреть").setCallbackData("/posm"));
	    	add(new InlineKeyboardButton().setText("заказать").setCallbackData("/zak"));
	    }});
	    
        inlineKeyboardMarkup.setKeyboard(rowList);
		
		try {
			productDao = new ProductDao();
			products = productDao.listActual();
			if (products!=null)
			for (final Product product : products) {
				 rowList.add(new ArrayList<InlineKeyboardButton>() {{
				    	add(new InlineKeyboardButton().setText(product.toString()).setCallbackData("/product:"+product.getProductId()));
				    }});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
			
			inlineKeyboardMarkup.setKeyboard(rowList);
			 return inlineKeyboardMarkup;
       
    }
	
	private void installBase() {
		
		try {
			ProductDao productDao = new ProductDao();
				productDao.add("01.01.2019", "Кофе",  45.00, "чащ.", false);
				productDao.add("01.01.2019", "Чай",  34.00, "чащ.", true);
				productDao.add("01.01.2019", "Коньяе",  136.00, "рюм.", true);
				productDao.add("01.01.2019", "Виски",  145.00, "рюм.", true);
				productDao.add("01.01.2019", "Кофе",  42.00, "чащ.", false);
				productDao.add("01.01.2019", "Кофе",  48.00, "чащ.", true);
				
			
			} catch (Exception e) {
									
									e.printStackTrace();
										
				
			}
		
	}
	
	
	public String getBotUsername() {
		return botUserName;
	}


	@Override
	public String getBotToken() {
		return token;
	}
}
