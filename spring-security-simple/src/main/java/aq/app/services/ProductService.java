package aq.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import aq.app.models.Product;

@Service
public class ProductService {

	@PreFilter("filterObject.owner == authentication.name")
	public List<Product> sellProducts(List<Product> products) {
		return products;
	}
	
	@PostFilter("filterObject.owner == authentication.name")
	public List<Product> findProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("nikolai", "juice"));
		products.add(new Product("nikolai", "meat"));
		products.add(new Product("julien", "fruit"));
		return products;
	}
}
