package hibernate.app;

import org.hibernate.Session;

import com.hibernateutil.HibernateUtil;

public class App {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.close();
	}

}
