package hibernate.app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.hibernateutil.HibernateUtil;

import hibernate.app.model.Category;

public class CategoryDaoImp implements CategoryDao {

	private Session session;

	private void beginSession() {
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	}

	private void closeSession() {
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void create(Category category) {
		beginSession();
		session.save(category);
		closeSession();
	}

	@Override
	public Category getOne(int id) {
		beginSession();
		Category category = (Category) session.get(Category.class, id);
		closeSession();
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getCategories() {
		beginSession();
		Criteria criteria = session.createCriteria(Category.class);
		List<Category> categories = criteria.list();
		closeSession();
		return categories;
	}

	@Override
	public void update(Category category) {
		beginSession();
		session.saveOrUpdate(category);
		closeSession();
	}

	@Override
	public void delete(int id) {
		beginSession();
		Category category = getOne(id);
		session.delete(category);
		closeSession();
	}

}
