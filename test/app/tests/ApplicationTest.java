package app.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import hibernate.app.model.Category;
import hibernate.app.model.Product;
import hibernate.app.service.CategoryService;
import hibernate.app.service.ProductService;

import java.util.List;

import org.junit.Test;

public class ApplicationTest {

	private ProductService productService = new ProductService();
	private CategoryService categoryService = new CategoryService();

	@Test
	public void categoryCreate() {
		Category category = new Category();
		category.setName("Ultrabooks");
		categoryService.create(category);
	}

	public void productCreation() {
		Category category = categoryService.getOne(1);
		Product product = new Product("Fujitsu", category);
		productService.create(product);
	}

	@Test
	public void countProductIds() {
		long numberOfProducts = productService.numberOfProducts();
		assertEquals(3, numberOfProducts);
	}

	@Test
	public void productsInDatabase() {
		List<Product> products = productService.getProducts();
		assertEquals(4, products.size());
	}

	@Test
	public void getProduct() {
		Product product = productService.getOne(3);
		assertNotNull(product);
	}

	@Test
	public void getProductsInCategory() {
		Category category = categoryService.getOne(2);
		List<Product> products = productService.getProductsPerCategory(category);
		assertEquals(1, products.size());
	}

}
