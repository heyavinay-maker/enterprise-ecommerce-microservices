package com.heyavinay.order;

public record OrderRequest(
		
		Long productId,
        Integer purchaseQuantity,
        Double totalBillAmount
) {}
