package com.mealorderbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class App 
{
    public static void main( String[] args )
    {
    	 ApiContextInitializer.init();

         TelegramBotsApi botsApi = new TelegramBotsApi();

         try {
             botsApi.registerBot(new Bot());
//             System.out.println("4go!!!!!!!!!!!!");
         } catch (TelegramApiException e) {
             e.printStackTrace();
         }
       
     }
   
}

