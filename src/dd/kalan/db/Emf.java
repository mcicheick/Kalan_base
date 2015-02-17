package dd.kalan.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author Sissoko
 * @date 8 févr. 2014 05:01:23
 */
public class Emf {

	public static String PERSISTENCE_UNIT = "";

	static {
		if ("".equalsIgnoreCase(PERSISTENCE_UNIT)) {
			System.err.println("Don't forget to initiate the persistence unit");
			System.err
					.println("Emf.PERSISTENCE_UNIT = \"my-persistence-unit\"");
		}
	}

	private static EntityManagerFactory instance;

	/**
     *
     */
	private Emf() {
	}

	public static EntityManagerFactory getInstance() {
		if (null == instance || !instance.isOpen()) {
			instance = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		}
		return instance;
	}
}
