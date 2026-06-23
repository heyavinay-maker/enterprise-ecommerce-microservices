package com.heyavinay.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private final OrderRepository orderRepository;
	
	public OrderController(OrderRepository orderRepository) {
		
		this.orderRepository=orderRepository;
		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public Order placeOrder(@RequestBody OrderRequest request) {
		
		var order=new Order();
		order.setProductId(request.productId());
		order.setPurchaseQuantity(request.purchaseQuantity());
		order.setTotalBillAmount(request.totalBillAmount());
		order.setOrderStatus("PENDING");// Initial status before payment/verification
		
		return orderRepository.save(order);
			
		
	}
	@GetMapping
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}

}
