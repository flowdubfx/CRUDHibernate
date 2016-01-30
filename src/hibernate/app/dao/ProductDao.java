package hibernate.app.dao;

import java.util.List;

import hibernate.app.model.Category;
import hibernate.app.model.Product;

public interface ProductDao {

	void create(Product product);

	Product getOne(int id);

	List<Product> getProducts();

	void update(Product product);

	void delete(int id);
	
	List<Product> getProductsPerCategory(Category category);

}
