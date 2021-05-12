package com.capg.msc.myshoppingcart.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.capg.msc.myshoppingcart.beans.Product;
import com.capg.msc.myshoppingcart.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private ProductService service;
	
	public ProductRestController() {
		System.out.println("------>> Product Rest Controller Constructor");
	}
	@GetMapping("/home")
	public String homeRequest() {
		return "Welcome : My Shopping App" + LocalDateTime.now();
	}

	@PostMapping("/product/insert")
	public Product insertProduct(@RequestBody Product p) {
		service.saveProduct(p);
		return p;
	}
	
	@GetMapping("/product/all")
	public List<Product> getAllproducts(){
		return service.getAllProducts();
	}
	
	@GetMapping("/product/search/{id}")
	public Product searchProduct(@PathVariable int id){
		return service.searchProduct(id);
	}
	
	@PutMapping("/product/update/{id}")
	public Product updateProduct(@RequestBody Product p,@PathVariable int id)
	{
		return service.updateProduct(p, id);
	}

	@PostMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable int id) {
		if (service.deleteProduct(id))return "Deleted!";
		else return "No such ID found";
	}
	
	@GetMapping("/products/{category}")
	public List<Product> getAllProductsByCategory(@PathVariable String category)
	{
		return service.getAllProductsByCategory(category);
	}
	
	@GetMapping("/products/{category}/{range1}/{range2}")
	public List<Product> getAllProductsByCategoryAndPrice(@PathVariable String category,
			@PathVariable int range1,@PathVariable int range2)
	{
		return service.getAllProductsByCategoryAndPrice(category, range1, range2);
	}
}
