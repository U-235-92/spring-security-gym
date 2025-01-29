package aq.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import aq.app.models.Product;
import aq.app.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping("/add")
	public String add(@RequestParam String name) {
		log.info("Adding product " + name);
		return "main.html";
	}
	
	@ResponseBody
	@GetMapping("/sell") 
	public List<Product> sellProduct() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("nikolai", "juice"));
		products.add(new Product("nikolai", "meat"));
		products.add(new Product("julien", "fruit"));
		return productService.sellProducts(products);
	}
	
	@ResponseBody
	@GetMapping("/find") 
	public List<Product> findProduct() {
		return productService.findProducts();
	}
}
