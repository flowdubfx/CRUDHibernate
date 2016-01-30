package hibernate.app.dao;

import java.util.List;

import org.hibernate.Criteria;
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
		Criteria criteria = session.createCriteria(Product.class);
		List<Product> products = criteria.list();
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
		Criteria criteria = session.createCriteria(Product.class,"product");
		criteria.createAlias("product.category", "category");
		criteria.add(Restrictions.eq("category.id", category.getId()));	
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<Product> products = criteria.list();
		closeSession();
		return products;		
	}

}
