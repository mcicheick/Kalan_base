/**
 * 
 */
package dd.kalan.container;

import java.util.logging.Logger;

/**
 * 
 * @author sissoko
 * @date 16 févr. 2015 01:30:24
 */
public interface Interceptor {

	public static final String LOGGING_SERVICE = "Logging_Service";
	public static final String SECURITY_SERVICE = "Security_Service";
	public static final String MONITORING_SERVICE = "Monitoring_Service";
	public static final String EXCEPTION_NAME = "throwable";

	public static final Logger logger = Logger
			.getLogger(LoggingInterceptor.class.getName());

	/**
	 * 
	 * @param ctx
	 */
	public void log(Context ctx);

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public boolean methodCalled_callback(Context ctx);

	/**
	 * 
	 * @param ctx
	 * @return
	 */
	public boolean methodReturned_callback(Context ctx);
}
