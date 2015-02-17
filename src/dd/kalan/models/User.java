/**
 * 
 */
package dd.kalan.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author sissoko
 * @date 16 févr. 2015 03:59:07
 */
@Entity
@Table(name = "T_USERS")
@NamedQueries({
		@NamedQuery(name = "User.findAllUser", query = "SELECT u FROM User u order by u.username asc"),
		@NamedQuery(name = "User.findUserByUsername", query = "SELECT u FROM User u WHERE u.username = :username order by u.creationDate asc"),
		@NamedQuery(name = "User.deleteAllUser", query = "DELETE FROM User"),
		@NamedQuery(name = "User.findUserByPage", query = "SELECT u FROM User u order by u.username asc") })
public class User extends Model {

	/**
	 * 
	 */
	@Basic(optional = false)
	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String username;

	/**
	 * 
	 */
	@Basic(optional = false)
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User[" + id + "](" + username + ")";
	}
}
