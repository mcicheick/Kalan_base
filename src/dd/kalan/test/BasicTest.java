/**
 * 
 */
package dd.kalan.test;

import java.lang.reflect.InvocationTargetException;

import dd.kalan.container.Container;
import dd.kalan.db.DatabaseImpl;
import dd.kalan.db.Emf;
import dd.kalan.db.InterfaceDatabase;
import dd.kalan.exceptions.KalanException;
import dd.kalan.models.User;

/**
 * @author sissoko
 * @date 16 févr. 2015 03:01:20
 */
public class BasicTest {

	public Integer doSum(Integer a, Integer b) {
		return a + b;
	}

	/**
	 * @param args
	 * @throws KalanException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static void main(String[] args) {
		Emf.PERSISTENCE_UNIT = "Kalan";
		InterfaceDatabase database = DatabaseImpl.createDatabaseImpl();
		Container container;
		try {
			container = new Container(database);
			User user = new User();
			user.setUsername("mcicheick@yahoo.fr");
			user.setPassword("apassword");
			container.createUser(user);
		} catch (KalanException e) {
			System.out.println(e);
		}
	}
}
