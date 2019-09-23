package com.mealorderbot.entites;


import java.util.Date;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "ORDER")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private int orderId;
	@Column(name = "NUMBER")
	private int number;
	@Column(name = "DATE_ORDDER")
	private Date dateOrder;
	@Column(name = "TELEGRAM_ID")
	private String telegramId;
	
	@Column
	@ElementCollection(targetClass=OrderItems.class)
	private Set<OrderItems> orderItems;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order( int number, Date dateOrder, String telegramId) {
		
//		setOrderId(orderId);
		setNumber(number);
		setDateOrder(dateOrder);
		setTelegramId(telegramId);
	}

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

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", number=" + number + ", dateOrder=" + dateOrder + ", telegramId="
				+ telegramId + "]";
	}
	
//	@OneToMany(mappedBy = "ORDERITEMS", cascade = CascadeType.ALL)
	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}
}
