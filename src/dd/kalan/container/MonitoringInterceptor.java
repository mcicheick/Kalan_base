/**
 * 
 */
package dd.kalan.container;

/**
 * @author sissoko
 * @date 16 févr. 2015 02:03:38
 */
public class MonitoringInterceptor implements Interceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dd.kalan.container.Interceptor#methodCalled_callback(dd.kalan.container
	 * .Context)
	 */
	@Override
	public boolean methodCalled_callback(Context ctx) {
		System.out.println("Monitoring Service pre-invoked... ");
		System.out.println(ctx.getParamValue(Container.METHOD_PARAM_NAME));
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dd.kalan.container.Interceptor#methodReturned_callback(dd.kalan.container
	 * .Context)
	 */
	@Override
	public boolean methodReturned_callback(Context ctx) {
		System.out.println(ctx.getParamValue(Container.METHOD_PARAM_NAME));
		System.out.println("Monitoring Service post-invoked... ");
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.container.Interceptor#log(java.lang.Throwable)
	 */
	@Override
	public void log(Context ctx) {
	}
}
