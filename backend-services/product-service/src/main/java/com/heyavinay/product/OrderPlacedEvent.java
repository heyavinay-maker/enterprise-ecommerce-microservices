package com.heyavinay.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPlacedEvent {
	
	private Long productId;
	private Integer quantity;// This provides getQuantity()
	
	
	// Default constructor required for Kafka JSON mapping
    public OrderPlacedEvent() {}
    
    
	public OrderPlacedEvent(Long productId, Integer quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderPlacedEvent [productId=" + productId + ", quantity=" + quantity + "]";
	}
	
	

}
