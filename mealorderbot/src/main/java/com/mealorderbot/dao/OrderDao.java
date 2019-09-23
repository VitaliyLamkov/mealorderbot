package com.mealorderbot.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.mealorderbot.entites.Order;
import com.mealorderbot.entites.Product;

import java.sql.Date;
import java.util.List;

public class OrderDao {
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		    .configure()
		    .build();
	private static SessionFactory sessionFactory;
	
	public OrderDao() throws Exception {
		 try {
			    sessionFactory = new MetadataSources(registry)
			      .buildMetadata()
			      .buildSessionFactory();
			  } catch (Exception e) {
			    StandardServiceRegistryBuilder.destroy(registry);
			    throw e;
			}
	}
	
	public Integer add(int number, java.util.Date date, String telegramId)  {
		 Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        Integer orderId = null;

	        transaction = session.beginTransaction();
	        Order order = new Order(number, date, telegramId);
	        orderId = (Integer) session.save(order);
	        transaction.commit();
	        session.close();
	        return orderId;
		 }
	
	 public List<Order> listConsumer(String telegramId) {
		 	
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<Order> orders = session.createQuery("FROM Order where TELEGRAM_ID=:telegramId")
	        		.setParameter("telegramId", telegramId)
	        		.list();
	        session.close();
	        transaction.commit();
	        return orders;
	    }

	 public Order getById(int orderId) {
		 	
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        transaction = session.beginTransaction();
	        Order order = session.byId(Order.class).getReference(orderId);
	        transaction.commit();
	        session.close();
	        return order;
		 
	 }
	 
	 public void remove(int orderId) {
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        Order order = (Order) session.get(Order.class, orderId);
	        session.delete(order);
	        transaction.commit();
	        session.close();
	    }
	 public int nextNumber() {
		 
		 Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	       List<Order> numbers = session.createQuery("SELECT MAX(Order.number) FROM Order").list();
	        session.close();
	        transaction.commit();
	        return (numbers.get(0).getNumber()+1);
		 		
	 }
	 public String getFiveOrders(int telegramId) {
		 StringBuilder str = new StringBuilder();
		 
		 Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        

	        
	        transaction = session.beginTransaction();
	        List<Order> orders = session.createQuery("FROM Order  "
	        		+ "where TELEGRAM_ID=:telegramId")
	        		.setParameter("telegramId", telegramId)
	        		.list();
	        session.close();
	        for (Order order : orders) {
	        	str.append("Заказ " + order.getNumber() + order.getDateOrder());
	        	
	        }
	        
	        
	        transaction.commit();
	        return str.toString();
	 }
	 
}
