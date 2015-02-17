/**
 * 
 */
package dd.kalan.container;

import java.util.List;

import dd.kalan.db.InterfaceDatabase;
import dd.kalan.exceptions.KalanException;
import dd.kalan.models.User;

/**
 * @author sissoko
 * @date 16 févr. 2015 02:44:02
 */
public class InterceptorProxy implements InterfaceDatabase {

	/**
	 * The container that manages the Magasin instance and the associated
	 * intercepting chain
	 */
	private Container container = null;

	/**
	 * Indicates the type of concrete Interceptor associated to this Proxy (e.g.
	 * Security, Logging or Monitoring)
	 */
	private String serviceType = null;

	/**
	 * The Interceptor service associated to this Proxy The Proxy notifies this
	 * Interceptor service whenever it receives a Magasin method call or
	 * response
	 */
	private Interceptor interceptor = null;

	/**
	 * the next Proxy in the intercepting chain this Proxy will forward incoming
	 * method calls to the next Proxy (if this Proxy is the last one in the
	 * intercepting chain, then the nextProxy will be the object) [not clear]
	 */
	private InterfaceDatabase nextProxy = null;

	/**
	 * @param container
	 * @param serviceType
	 */
	public InterceptorProxy(Container container, String serviceType) {
		this.container = container;
		// instantiate specific Interceptor service, depending on the given
		// serviceType
		if (Interceptor.SECURITY_SERVICE.equals(serviceType)) {
			this.interceptor = new SecurityInterceptor();
		} else if (Interceptor.LOGGING_SERVICE.equals(serviceType)) {
			this.interceptor = new LoggingInterceptor();
		} else if (Interceptor.MONITORING_SERVICE.equals(serviceType)) {
			this.interceptor = new MonitoringInterceptor();
		} else {
			if (Container.verbose > 0)
				System.out.println("WARNING!! unknown service type: "
						+ serviceType);
		}
		// set the serviceType
		this.serviceType = serviceType;
	}

	/**
	 * @param interceptor
	 *            the interceptor to set
	 */
	public void setInterceptor(Interceptor interceptor) {
		this.interceptor = interceptor;
	}

	/**
	 * @param nextProxy
	 *            the nextProxy to set
	 */
	public void setNextProxy(InterfaceDatabase nextProxy) {
		this.nextProxy = nextProxy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#createUser(dd.kalan.models.User)
	 */
	@Override
	public User createUser(User user) throws KalanException {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		User result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.createUser(user);
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserById(java.lang.Long)
	 */
	@Override
	public User findUserById(Long id) throws KalanException {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		User result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.findUserById(id);
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#update(dd.kalan.models.User)
	 */
	@Override
	public User updateUser(User user) throws KalanException {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		User result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.updateUser(user);
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#deleteUser(java.lang.Long)
	 */
	@Override
	public User deleteUser(Long id) throws KalanException {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		User result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.deleteUser(id);
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserByPage(java.lang.Long)
	 */
	@Override
	public List<User> findUserByPage(Integer page) {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		List<User> result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.findUserByPage(page);
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserByUsername(java.lang.String)
	 */
	@Override
	public User findUserByUsername(String username) {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		User result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.findUserByUsername(username);
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findAllUser()
	 */
	@Override
	public List<User> findAllUser() {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		List<User> result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.findAllUser();
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#deleteAllUser()
	 */
	@Override
	public Boolean deleteAllUser() {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		Boolean result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.deleteAllUser();
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#authenticate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User authenticate(String username, String password) {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		User result = null;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.authenticate(username, password);
		if (result == null) {
			context.setParam(Container.LOGIN_FAILED, "Connexion failed");
		}
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#logout()
	 */
	@Override
	public Boolean logout() {
		// get the context object from the container
		Context context = this.container.getContext();

		// determines whether or not this Proxy will forward the incoming method
		// call down the intercepting chain
		boolean doContinue;

		Boolean result = false;
		// preinvoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodCalled_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
			doContinue = false;
		}

		// determine the pre-invocation outcome; return if unsuccessful
		if (!doContinue) {
			return result;
		}
		result = this.nextProxy.logout();
		// post-invoke the associated Interceptor service
		if (null != this.interceptor) {
			doContinue = this.interceptor.methodReturned_callback(context);
		} else {
			if (Container.verbose > 0)
				System.out
						.println("WARNING!! NULL Interceptor Service for Proxy: "
								+ serviceType);
		}
		return result;
	}
}
