package hibernate.app.service;

import java.util.List;

import hibernate.app.dao.CategoryDao;
import hibernate.app.dao.CategoryDaoImp;
import hibernate.app.model.Category;

public class CategoryService {

	private CategoryDao dao;

	public CategoryService() {

		dao = new CategoryDaoImp();
	}

	public void create(Category category) {
		dao.create(category);
	}

	public Category getOne(int id) {
		return dao.getOne(id);
	}

	public List<Category> getCategories() {
		return dao.getCategories();
	}

	public void update(Category category) {
		dao.update(category);
	}

	public void delete(int id) {
		dao.delete(id);
	}

}
