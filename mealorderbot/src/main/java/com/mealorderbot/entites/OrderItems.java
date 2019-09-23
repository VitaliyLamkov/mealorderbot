package com.mealorderbot.entites;
import javax.persistence.*;
@Entity
@Table(name = "ORDERITEMS")
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDERITEMS_ID")
	private int orderItemsId;
	@Column(name = "ORDER_ID")
	private int orderId;
	@Column(name = "PRODUCT_ID")
	private  int productId;
//	@Column
//	@ElementCollection(targetClass=Order.class)
//	private Order order;
//	@Column
//	@ElementCollection(targetClass=Product.class)
//	private Product product;
	
	 public OrderItems() {
		// TODO Auto-generated constructor stub
	}
	public OrderItems( int orderId, int productId) {
		
		setOrderItemsId(orderItemsId);
		setOrderId(orderId);
		setProductId(productId);
		
//		setOrder(order);
//		setProduct(product);
		
	}
	public int getOrderItemsId() {
		return orderItemsId;
	}
	public void setOrderItemsId(int orderItemsId) {
		this.orderItemsId = orderItemsId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	

//	public Order getOrder() {
//		return order;
//	}
//	
////	@ManyToOne
////	@JoinColumn(name = "PRODUCT_ID")
//	public Product getProduct() {
//		return product;
//	}
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//	public void setProduct(Product product) {
//		this.product = product;
//	}
	
	@Override
	public String toString() {
		return "OrderItems [orderItemsId=" + orderItemsId + ", order="+"]";
	}
}
