package dd.kalan.db;

import javax.persistence.EntityManager;

/**
 * 
 * @author Sissoko
 * @date 8 févr. 2014 05:01:37
 */
public interface Dao {

	public EntityManager getEntityManager();

}