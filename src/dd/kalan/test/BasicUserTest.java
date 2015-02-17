/**
 * 
 */
package dd.kalan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test.None;

import dd.kalan.db.DatabaseImpl;
import dd.kalan.db.Emf;
import dd.kalan.db.InterfaceDatabase;
import dd.kalan.exceptions.KalanException;
import dd.kalan.models.User;
import dd.kalan.security.Security;

/**
 * @author sissoko
 * @date 16 févr. 2015 17:15:40
 */
public class BasicUserTest {
	InterfaceDatabase database;

	@Before
	public void setup() {
		System.out.println("Test started...");
		Emf.PERSISTENCE_UNIT = "Kalan";
		database = DatabaseImpl.createDatabaseImpl();
		// User connected = database.authenticate("mcicheick@yahoo.fr",
		// "secret");
		// assertNotNull(connected);
		// database.deleteAllUser();
	}

	@Test(expected = None.class)
	public void insertAndFindUserTest() throws KalanException {
		User connected = database.authenticate("mcicheick@yahoo.fr", "secret");
		assertNotNull(connected);
		// User cheick = new User("cheickm.sissoko@gmail.com",
		// Security.hashPassword("secret"));
		// database.createUser(cheick);
		User bdCheick = database
				.findUserByUsername("cheickm.sissoko@gmail.com");
		assertNotNull(bdCheick);
		assertEquals("cheickm.sissoko@gmail.com", bdCheick.getUsername());

		// User elay = new User("elaysama@gmail.com",
		// Security.hashPassword("secret"));
		// database.createUser(elay);
		User bdElay = database.findUserByUsername("elaysama@gmail.com");
		assertNotNull(bdElay);
		assertEquals("elaysama@gmail.com", bdElay.getUsername());

		List<User> elayCheick = database.findAllUser();
		assertEquals(elayCheick.size(), 3);
	}

	@Test(expected = None.class)
	public void findAndUpdateTest() throws KalanException {
		User connected = database.authenticate("mcicheick@yahoo.fr", "secret");
		assertNotNull(connected);
		// User cheick = new User("cheickm.sissoko@gmail.com",
		// Security.hashPassword("secret"));
		// database.createUser(cheick);
		User bdCheick = database
				.findUserByUsername("cheickm.sissoko@gmail.com");
		assertNotNull(bdCheick);
		assertEquals("cheickm.sissoko@gmail.com", bdCheick.getUsername());

		// User elay = new User("elaysama@gmail.com",
		// Security.hashPassword("secret"));
		// database.createUser(elay);
		User bdElay = database.findUserByUsername("elaysama@gmail.com");
		assertNotNull(bdElay);
		assertEquals("elaysama@gmail.com", bdElay.getUsername());

		// bdCheick.setUsername("mcicheick@yahoo.fr");
		// database.updateUser(bdCheick);
		// bdCheick = database.findUserByUsername("mcicheick@yahoo.fr");
		// assertNotNull(bdCheick);
		// assertEquals("mcicheick@yahoo.fr", bdCheick.getUsername());

		List<User> elayCheick = database.findAllUser();
		assertEquals(elayCheick.size(), 3);
	}

	@Test
	public void findByPageTest() throws KalanException {
		List<User> users = database.findAllUser();
		assertEquals(users.size(), 3);
		users = database.findUserByPage(1);
		System.out.println(users.size());
		assertEquals(users.size(), 3);
	}

	// @Test()
	public void accessTest() throws KalanException {
		database.logout();
		// User connected = database.authenticate("mcicheick@yahoo.fr",
		// "secret");
		// assertNotNull(connected);
		User cheick = new User("cheickm.sissoko@gmail.com",
				Security.hashPassword("secret"));
		assertNotNull(database.createUser(cheick));
		List<User> users = database.findAllUser();
		assertEquals(users.size(), 3);
	}
}
