/**
 * 
 */
package dd.kalan.container;

import dd.kalan.models.User;
import dd.kalan.security.Access;

/**
 * @author sissoko
 * @date 16 févr. 2015 02:02:30
 */
public class SecurityInterceptor implements Interceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * dd.kalan.container.Interceptor#methodCalled_callback(dd.kalan.container
	 * .Context)
	 */
	@Override
	public boolean methodCalled_callback(Context ctx) {
		System.out.println("Security Service pre-invoked...");
		System.out.println(ctx.getParamValue(Container.METHOD_PARAM_NAME));
		Access accessType = (Access) ctx.getParamValue(Container.ACCESS_TYPE);
		if (accessType == null) {
			return true;
		}
		User connected;
		switch (accessType) {
		case ADMIN:
			connected = (User) ctx.getParamValue(Container.CONNECTED);
			if (connected == null) {
				ctx.setParam(Container.ACCESS_FAILED, "Accès refusé");
				return false;
			}
			// check user right. TODO
			return true;
		case CONNECTED:
			connected = (User) ctx.getParamValue(Container.CONNECTED);
			if (connected == null) {
				ctx.setParam(Container.ACCESS_FAILED, "Connexion est requise");
				return false;
			}
			return true;
		case DISCONNECTED:
			connected = (User) ctx.getParamValue(Container.CONNECTED);
			if (connected != null) {
				ctx.setParam(Container.ACCESS_FAILED, "Vous êtes déjà connecté");
				return false;
			}
			return true;
		case OWNER:
			connected = (User) ctx.getParamValue(Container.CONNECTED);
			if (connected == null) {
				ctx.setParam(Container.ACCESS_FAILED, "Accès refusé");
				return false;
			}
			// check user is the owner of object TODO
			return true;

		default:
			break;
		}
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
		ctx.removeParam(Container.ACCESS_TYPE);
		System.out.println("Security Service post-invoked...");
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
