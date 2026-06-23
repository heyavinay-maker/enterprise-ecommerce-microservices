package com.heyavinay.order;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq_gen")
	@SequenceGenerator(name="order_seq_gen" , sequenceName = "customer_order_seq" ,allocationSize = 1)
	
	private Long id;
	
	private Long productId;
	private Integer purchaseQuantity;
	private Double  totalBillAmount;
	private String  orderStatus;
	public Order() {
		super();
		this.id = id;
		this.productId = productId;
		this.purchaseQuantity = purchaseQuantity;
		this.totalBillAmount = totalBillAmount;
		this.orderStatus = orderStatus;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(Integer purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public Double getTotalBillAmount() {
		return totalBillAmount;
	}
	public void setTotalBillAmount(Double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", productId=" + productId + ", purchaseQuantity=" + purchaseQuantity
				+ ", totalBillAmount=" + totalBillAmount + ", orderStatus=" + orderStatus + "]";
	}
	
	
	
	
	
	
	

}
