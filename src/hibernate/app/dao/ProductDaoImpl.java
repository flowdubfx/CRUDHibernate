package hibernate.app.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import com.hibernateutil.HibernateUtil;

import hibernate.app.model.Category;
import hibernate.app.model.Product;

public class ProductDaoImpl implements ProductDao {

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
	public void create(Product product) {
		beginSession();
		session.save(product);
		closeSession();
	}

	@Override
	public Product getOne(int id) {
		beginSession();
		Product product = (Product) session.get(Product.class, id);
		closeSession();
		return product;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		beginSession();
		List<Product> products = null;
		try {
			Criteria criteria = session.createCriteria(Product.class);
			products = criteria.list();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			closeSession();
		}
		closeSession();
		return products;
	}

	@Override
	public void update(Product product) {
		beginSession();
		session.saveOrUpdate(product);
		closeSession();
	}

	@Override
	public void delete(int id) {
		beginSession();
		Product product = getOne(id);
		session.delete(product);
		closeSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductsPerCategory(Category category) {
		beginSession();
		List<Product> products = null;
		try {
			Criteria criteria = session.createCriteria(Product.class, "product");
			criteria.createAlias("product.category", "category");
			criteria.add(Restrictions.eq("category.id", category.getId()));
			criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			products = criteria.list();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			closeSession();
		}
		closeSession();
		return products;
	}

	@Override
	public Long numberOfProducts() {
		beginSession();
		Long result = null;
		try {
			String hql = "SELECT count(id) FROM Product p WHERE p.category.id = 1";
			Query query = session.createQuery(hql);
			result = (Long) query.uniqueResult();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			closeSession();
		}
		closeSession();
		return result;
	}

}
