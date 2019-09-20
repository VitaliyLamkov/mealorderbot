package com.mealorderbot.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.mealorderbot.entites.Product;

import java.sql.Date;
import java.util.List;

public class ProductDao {
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
		    .configure()
		    .build();
	private static SessionFactory sessionFactory;
	
	public ProductDao() throws Exception {
		 try {
			    sessionFactory = new MetadataSources(registry)
			      .buildMetadata()
			      .buildSessionFactory();
			  } catch (Exception e) {
			    StandardServiceRegistryBuilder.destroy(registry);
			    throw e;
			}
	}
	
	public void add()  {
		  	Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        transaction = session.beginTransaction();
	        Product productEntite = new Product("10.5.2019", "кофе", 45.00, "чаш.");
	        session.save(productEntite);
	        transaction.commit();
	        session.close();
		 }
	
	 public String list() {
		 	StringBuilder str = new StringBuilder("");
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<Product> products = session.createQuery("FROM Product").list();
	        for (Product product : products) {
	            str.append(product.toString());
	            str.append("\n================\n");
	        }
	        session.close();
	        return str.toString();
	    }
}
