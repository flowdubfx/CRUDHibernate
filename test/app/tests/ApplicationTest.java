package app.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hibernate.app.model.Category;
import hibernate.app.model.Product;
import hibernate.app.service.CategoryService;
import hibernate.app.service.ProductService;

public class ApplicationTest {
	
	private ProductService productService = new ProductService();
	private CategoryService categoryService = new CategoryService();
	
	@Before
	public void productCreation(){
		Category category = categoryService.getOne(2);
		Product product = new Product("Acer", category);
		productService.create(product);
	}
	
	@Test
	public void productsInDatabase() {							
		List<Product> products = productService.getProducts();
		assertEquals(6, products.size());
	}
	
	@Test
	public void getProduct(){		
		Product product = productService.getOne(1);
		assertNotNull(product);
	}
	
	@Test
	public void getProductsInCategory(){
		
		Category category = categoryService.getOne(2);		
		List<Product> products = productService.getProductsPerCategory(category);		
		assertEquals(2, products.size());
	}
	

}
