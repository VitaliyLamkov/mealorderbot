package com.mealorderbot.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.mealorderbot.entites.Order;
import com.mealorderbot.entites.OrderItems;
import com.mealorderbot.entites.Product;

import java.sql.Date;
import java.util.List;

public class OrderItemsDao {
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		    .configure()
		    .build();
	private static SessionFactory sessionFactory;
	
	public OrderItemsDao() throws Exception {
		 try {
			    sessionFactory = new MetadataSources(registry)
			      .buildMetadata()
			      .buildSessionFactory();
			  } catch (Exception e) {
			    StandardServiceRegistryBuilder.destroy(registry);
			    throw e;
			}
	}
	
	public Integer add(int orderId, int productId)  {
		 Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        Integer orderItemsId = null;

	        transaction = session.beginTransaction();
	        OrderItems orderItems = new OrderItems( orderId, productId);
	        orderItemsId = (Integer) session.save(orderItems);
	        transaction.commit();
	        session.close();
	        return orderItemsId;
		 }
	
	 public List<OrderItems> listOrder(int orderId) {
		 	
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<OrderItems> orderItems = session.createQuery("FROM OrderItems where ORDER_ID=:orderId")
	        		.setParameter("orderId", orderId)
	        		.list();
	        session.close();
	        transaction.commit();
	        return orderItems;
	    }

	 public OrderItems getById(int orderItemsId) {
		 	
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        transaction = session.beginTransaction();
	        OrderItems orderItems = session.byId(OrderItems.class).getReference(orderItemsId);
	        transaction.commit();
	        session.close();
	        return orderItems;
		 
	 }
	 
	 public void remove(int orderItemsId) {
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        OrderItems orderItems = (OrderItems) session.get(OrderItems.class, orderItemsId);
	        session.delete(orderItems);
	        transaction.commit();
	        session.close();
	    }
	 
}
