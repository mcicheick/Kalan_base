/**
 * 
 */
package dd.kalan.db;

import java.util.List;

import dd.kalan.exceptions.KalanException;
import dd.kalan.models.User;

/**
 * @author sissoko
 * @date 16 févr. 2015 16:36:40
 */
public interface InterfaceDatabase {
	/**
	 * Insert new your in database
	 * 
	 * @param user
	 * @return
	 * @throws KalanException
	 */
	User createUser(User user) throws KalanException;

	/**
	 * Find a user by its id and return it, if it is not exist return null.
	 * 
	 * @param id
	 * @return
	 * @throws KalanException
	 */
	User findUserById(Long id) throws KalanException;

	/**
	 * Update user, assume that the user exists.
	 * 
	 * @param user
	 * @return
	 * @throws KalanException
	 */
	User updateUser(User user) throws KalanException;

	/**
	 * Delete a user by its id
	 * 
	 * @param id
	 * @return
	 * @throws KalanException
	 */
	User deleteUser(Long id) throws KalanException;

	/**
	 * Find a list of user by page.
	 * 
	 * @param page
	 * @return
	 */
	List<User> findUserByPage(Integer page);

	/**
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

	/**
	 * @return
	 */
	List<User> findAllUser();

	/**
	 * 
	 */
	Boolean deleteAllUser();

	/**
	 * 
	 * @param username
	 * @return
	 */
	User authenticate(String username, String password);

	/**
	 * 
	 * @return
	 */
	Boolean logout();
}
