package com.mealorderbot.entites;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderProduct {
	
	private int orderId;
	private int number;
	private Date dateOrder;
	private String telegramId;
	private String dateProduct;
	private String name;
	private Double price;
	private String nomenclature;
	private boolean actual;
	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Date getDateOrder() {
		return dateOrder;
	}
	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	public String getTelegramId() {
		return telegramId;
	}
	public void setTelegramId(String telegramId) {
		this.telegramId = telegramId;
	}
	public String getDateProduct() {
		return dateProduct;
	}
	public void setDateProduct(String dateProduct) {
		this.dateProduct = dateProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getNomenclature() {
		return nomenclature;
	}
	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}
	public boolean isActual() {
		return actual;
	}
	public void setActual(boolean actual) {
		this.actual = actual;
	}
	
	
}
