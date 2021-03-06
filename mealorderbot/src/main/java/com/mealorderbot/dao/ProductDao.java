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
	
	public void add(String dateProduct, String name, Double price, String nomenclature, Boolean actual)  {
		  	Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        transaction = session.beginTransaction();
	        Product productEntite = new Product(dateProduct, name, price, nomenclature, actual);
	        session.save(productEntite);
	        transaction.commit();
	        session.close();
		 }
	
	 public List<Product> listActual() {
		 	
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<Product> products = session.createQuery("FROM Product where actual=true").list();
	        session.close();
	        return products;
	    }
	 public List<Product> listNotActual() {
		 	StringBuilder str = new StringBuilder("");
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;

	        transaction = session.beginTransaction();
	        List<Product> products = session.createQuery("FROM Product where atual=false").list();
	        session.close();
	        return products;
	    }
	 public Product getProductById(int productId) {
		 	
	        Session session = sessionFactory.openSession();
	        Transaction transaction = null;
	        transaction = session.beginTransaction();
	        Product product = session.byId(Product.class).getReference(productId);
	        transaction.commit();
	        session.close();
	        return product;
		 
	 }
}
