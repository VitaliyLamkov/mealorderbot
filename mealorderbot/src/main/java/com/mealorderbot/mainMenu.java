package com.mealorderbot;

public enum mainMenu {
	START("/start"), NEWORDER("Оформить новый заказ"), LASTORDERS("Просмотреть последние 5 заказов"), IDPHONE("Просмотреть последние 5 заказов");
	private String itemMenu;
	mainMenu(String itemMnu){
		this.itemMenu = itemMnu;
	}
	
	public String getItemMenu() {
		return itemMenu;
	}
}
