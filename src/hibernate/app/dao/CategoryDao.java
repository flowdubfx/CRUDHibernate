package hibernate.app.dao;

import java.util.List;

import hibernate.app.model.Category;

public interface CategoryDao {

	void create(Category category);

	Category getOne(int id);

	List<Category> getCategories();

	void update(Category category);

	void delete(int id);
}
