package com.mealorderbot.entites;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;
@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRODUCT_ID")
	private int	productId;
	@Column(name = "DATE_PRODUCT")
	private String dateProduct;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PRICE")
	private Double price;
	@Column(name = "NOMENCLATURE")
	private String nomenclature;
	@Column(name = "ACTUAL")
	private boolean actual;

	@Column
	@ElementCollection(targetClass=OrderItems.class)
	private Set<OrderItems> orderItems;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	public Product( String dateProduct, String name, Double price, String nomenclature,boolean actual) {
		
//		setProductId(productId);
		setDateProduct(dateProduct);
		setName(name);
		setPrice(price);
		setNomenclature(nomenclature);
		setActual(actual);
	}
	
	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
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
	
	public void setActual(boolean actual) {
		this.actual = actual;
	}
	public boolean isActual() {
		return actual;
	}
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}

	
	@Override
	public String toString() {
//		return "Product [productId=" + productId + ", dateProduct=" + dateProduct + ", name=" + name + ", price="
//				+ price + ", nomenclature=" + nomenclature + "]";
		
		return "Название: " + name + " цена=" + price + "р. " + nomenclature ;
	}
	


}
