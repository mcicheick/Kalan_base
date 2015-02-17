/**
 * 
 */
package dd.kalan.exceptions;

import java.util.logging.Level;

import dd.kalan.container.Interceptor;

/**
 * @author sissoko
 * @date 16 févr. 2015 17:48:44
 */
public class KalanException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public KalanException() {
		super();
		Interceptor.logger.log(Level.SEVERE, getMessage(), this);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public KalanException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		Interceptor.logger.log(Level.SEVERE, getMessage(), this);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public KalanException(String message, Throwable cause) {
		super(message, cause);
		Interceptor.logger.log(Level.SEVERE, getMessage(), this);
	}

	/**
	 * @param message
	 */
	public KalanException(String message) {
		super(message);
		Interceptor.logger.log(Level.SEVERE, getMessage(), this);
	}

	/**
	 * @param cause
	 */
	public KalanException(Throwable cause) {
		super(cause);
		Interceptor.logger.log(Level.SEVERE, getMessage(), this);
	}

}
