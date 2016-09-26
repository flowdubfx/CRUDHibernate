package hibernate.app.service;

import java.util.List;

import hibernate.app.dao.ProductDao;
import hibernate.app.dao.ProductDaoImpl;
import hibernate.app.model.Category;
import hibernate.app.model.Product;

public class ProductService {

	private ProductDao dao;

	public ProductService() {
		dao = new ProductDaoImpl();
	}

	public void create(Product product) {
		dao.create(product);
	}

	public Product getOne(int id) {
		return dao.getOne(id);
	}

	public List<Product> getProducts() {
		return dao.getProducts();
	}

	public List<Product> getProductsPerCategory(Category category) {
		return dao.getProductsPerCategory(category);
	}

	public void update(Product product) {
		dao.update(product);
	}

	public void delete(int id) {
		dao.delete(id);
	}
}
