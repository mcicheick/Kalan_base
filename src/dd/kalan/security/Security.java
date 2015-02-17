/**
 * 
 */
package dd.kalan.security;

import dd.kalan.models.User;

/**
 * @author sissoko
 * @date 16 févr. 2015 23:30:28
 */
public class Security {

	private static BCrypt bcrypt = new BCrypt();

	/**
	 * @param user
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(User user, String password) {
		boolean check = bcrypt.checkpw(password, user.getPassword());
		return check;
	}

	/**
	 * 
	 * @param plainText
	 * @return
	 */
	public static String hashPassword(String plainText) {
		return bcrypt.hashpw(plainText, BCrypt.gensalt());
	}

}
