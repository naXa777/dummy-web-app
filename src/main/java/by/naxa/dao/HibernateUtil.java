package by.naxa.dao;

import by.naxa.WelcomeController;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handy utility class.
 * <p />
 * Created by phomal on 02.12.2014.
 */
public class HibernateUtil {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	private static final SessionFactory sessionFactory;
	static {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(registry.build());

			/* For Hibernate 4.0-4.3
			ServiceRegistry registry = new ServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
					.buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(registry);*/
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			logger.error("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static synchronized SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}

	/**
	 * Hidden constructor.
	 */
	/*private HibernateUtil() throws IllegalAccessException {
		throw new IllegalAccessException("It shouldn't be instantiated!");
	}*/
}
