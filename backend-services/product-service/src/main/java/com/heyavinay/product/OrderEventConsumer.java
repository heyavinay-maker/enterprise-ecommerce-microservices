package com.heyavinay.product;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

	private final ProductRepository productRepository;

	// Eclipse automatically wires this via constructor injection
	public OrderEventConsumer(ProductRepository productRepository) {
		this.productRepository = productRepository;

	}

	public void handleStockRedcution(OrderPlacedEvent event) {
		System.out.println(
				"-> [Kafka Consumer] Event intercepted! Reducing stock for Product ID: " + event.getProductId());

		// 1. Fetch the product entity from your MySQL Docker container
		Optional<Product> productOptional = productRepository.findById(event.getProductId());

		if (productOptional.isPresent()) {
			Product product = productOptional.get();

			// 2. Perform the stock calculation
			int updatedStock = product.getStockQuantity() - event.getQuantity();

			if (updatedStock >= 0) {

				// 3. Save the new stock level directly back into MySQL
				product.setStockQuantity(updatedStock);
				productRepository.save(product);

				System.out.println("-> [MySQL] Inventory updated! New stock balance: " + updatedStock);

			} else {
				System.err.println("-> [Transaction Rejected] Insufficient warehouse stock available.");
			}
		} else {
			System.err.println("-> [Error] Product ID " + event.getProductId() + " does not exist in MySQL.");
		}

	}

}
