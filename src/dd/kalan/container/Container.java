/**
 * 
 */
package dd.kalan.container;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

import dd.kalan.config.Config;
import dd.kalan.db.InterfaceDatabase;
import dd.kalan.exceptions.KalanException;
import dd.kalan.models.User;
import dd.kalan.security.Access;

/**
 * @author sissoko
 * @date 16 févr. 2015 01:53:04
 */
public class Container implements InterfaceDatabase {

	private static final String DEPLOYMENT_DESCRIPTOR_FILE_NAME = "config.cfg";
	public static final String METHOD_PARAM_NAME = "method_name";
	public static final String PARAMETERS_PARAM_NAME = "method_parameters";
	public static final String LOG_LEVEL = "log_level";
	public static final String PARAMETERS_USER_PAGE_LENGTH = "user_page_length";
	public static final String PARAM_LOGIN = "username";
	public static final String ACCESS_TYPE = "access_type";
	private static final String PARAMS = "parameters";
	public static final String CONNECTED = "connected";
	public static final String ACCESS_FAILED = "access_failed";
	public static final String LOGIN_FAILED = "login_failed";

	public static Integer USER_PAGE_LENGTH = 10;
	public static int verbose = 0;
	public static boolean debug = true;
	/**
	 * the context in which an intercepting event is raised All Interceptor
	 * services can obtain and possibly modify the context (by calling the
	 * container)
	 */
	private Context context = null;

	/**
	 * stores the names of the Intercepter services that this container manages
	 * the order of service names in this array determines the order of
	 * Interceptor services in the intercepting chain
	 */
	private String[] serviceNames = null;

	/**
	 * actions interceptor
	 */
	private InterceptorProxy firstProxy = null;

	/**
	 * @throws KalanException
	 * @throws IOException
	 * 
	 */
	public Container(InterfaceDatabase database) throws KalanException {
		try {
			configureContainer();
		} catch (IOException e) {
			throw new KalanException(e);
		}
		// instantiate the Interceptor Proxies
		InterceptorProxy[] proxies = new InterceptorProxy[this.serviceNames.length];
		for (int i = 0; i < this.serviceNames.length; i++) {
			proxies[i] = new InterceptorProxy(this, this.serviceNames[i].trim());
		}

		// bind the Interceptor Proxies so as to form the intercepting chain
		// the container keeps a reference to the first Proxy in the chain
		this.firstProxy = proxies[0];
		for (int i = 0; i < proxies.length - 1; i++) {
			proxies[i].setNextProxy(proxies[i + 1]);
		}
		// the last Proxy in the chain keeps a reference to the concrete
		// BaseModel object
		proxies[proxies.length - 1].setNextProxy(database);
	}

	/**
	 * Returns a singleton Context instance: - if an instance does not yet
	 * exist, it creates one and returns it; - if an instance already exists, it
	 * returns the existing instance. (implementation of the Singleton design
	 * pattern)
	 * 
	 * @return the singleton Context instance
	 */
	private Context getSingletonContext() {
		if (null == this.context) {
			this.context = new Context();
		}
		return this.context;
	}

	public Context getContext() {
		return context;
	}

	/**
	 * Configures the container using information from a configuration file
	 * (deployment_descriptor) - determines the service names as specified in
	 * the configuration file - sets the serviceNames attribute of the container
	 * 
	 * @throws IOException
	 */
	private void configureContainer() throws IOException {
		Config config = Config.readFromFile(DEPLOYMENT_DESCRIPTOR_FILE_NAME);
		if (verbose > 1)
			System.out.println(config);
		Container.verbose = config.getParamAsInteger("verbose", 0);
		Container.debug = config.getParamAsBoolean("debug", false);
		String sn = config.getParamAsString("serviceNames", "Logging_Service");
		Container.USER_PAGE_LENGTH = config.getParamAsInteger(
				PARAMETERS_USER_PAGE_LENGTH, 10);
		serviceNames = sn.split(",");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#createUser(dd.kalan.models.User)
	 */
	@Override
	public User createUser(User user) throws KalanException {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#createUser(User)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ user);
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		if (!debug)
			ctx.setParam(ACCESS_TYPE, Access.ADMIN);

		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.createUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserById(java.lang.Long)
	 */
	@Override
	public User findUserById(Long id) throws KalanException {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#findUserById(Long)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ id);
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.findUserById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#update(dd.kalan.models.User)
	 */
	@Override
	public User updateUser(User user) throws KalanException {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#updateUser(User)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ user);
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		if (!debug)
			ctx.setParam(ACCESS_TYPE, Access.OWNER);

		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.updateUser(user);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#deleteUser(java.lang.Long)
	 */
	@Override
	public User deleteUser(Long id) throws KalanException {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#deleteUser(Long)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ id);
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		if (!debug)
			ctx.setParam(ACCESS_TYPE, Access.OWNER);

		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.deleteUser(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserByPage(java.lang.Long)
	 */
	@Override
	public List<User> findUserByPage(Integer page) {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#findUserByPage(Long)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ page);
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.findUserByPage(page);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserByUsername(java.lang.String)
	 */
	@Override
	public User findUserByUsername(String username) {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#findUserByUsername(String)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ username);
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.findUserByUsername(username);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findAllUser()
	 */
	@Override
	public List<User> findAllUser() {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#findAllUser()");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ "()");
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.findAllUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#deleteAllUser()
	 */
	@Override
	public Boolean deleteAllUser() {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#deleteAllUser()");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ "()");
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);

		if (!debug)
			ctx.setParam(ACCESS_TYPE, Access.ADMIN);

		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		return this.firstProxy.deleteAllUser();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#authenticate(java.lang.String)
	 */
	@Override
	public User authenticate(String username, String password) {
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#authenticate(String, String)");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ "(" + username + ", *******)");
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);

		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		User result = this.firstProxy.authenticate(username, password);
		if (result != null) {
			ctx.setParam(PARAM_LOGIN, result.getUsername());
			ctx.setParam(CONNECTED, result);
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
		// obtain the current context
		Context ctx = this.getSingletonContext();
		// add method-related information to the context
		ctx.setParam(Container.METHOD_PARAM_NAME,
				"dd.kalan.models.User#logout()");
		ctx.setParam(Container.PARAMETERS_PARAM_NAME, Container.PARAMS + "="
				+ "()");
		ctx.setParam(Container.LOG_LEVEL, Level.INFO);
		if (!debug)
			ctx.setParam(ACCESS_TYPE, Access.CONNECTED);

		// optional: print the current context's parameter values
		if (Container.verbose > 0)
			ctx.printParams();

		// forwards the method call to the first proxy and returns the result
		Boolean result = this.firstProxy.logout();
		if (result) {
			ctx.clear();
		}
		return result;
	}
}
