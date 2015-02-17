package dd.kalan.container;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * This class is used to transmit parameters between objects
 * 
 * @author sissoko
 * @date 16 févr. 2015 01:36:01
 */
public class Context {

	private Hashtable<String, Object> params = new Hashtable<String, Object>();

	public Context() {
	}

	/**
	 * 
	 * @param params
	 */
	public Context(Hashtable<String, Object> params) {
		this.params = params;
	}

	public Hashtable<String, Object> getParams() {
		return params;
	}

	protected void setParams(Hashtable<String, Object> params) {
		this.params = params;
	}

	public Object getParamValue(String paramName) {

		Object value = null;
		if (this.params.containsKey(paramName)) {
			Enumeration<String> keys = this.params.keys();
			String keyAux;
			while (keys.hasMoreElements()) {
				keyAux = keys.nextElement();
				if (keyAux.equals(paramName)) {
					value = this.params.get(keyAux);
				}
			}
		} else {
			if (Container.verbose > 0)
				System.out.println("Context could not find a parameter named: "
						+ paramName + "; will return null...");
		}

		return value;
	}

	protected void setParam(String name, Object value) {
		this.params.put(name, value);
	}

	protected void removeParam(String paramName) {
		if (this.params.containsKey(paramName)) {
			this.params.remove(paramName);
		} else {
			if (Container.verbose > 0)
				System.out.println("Context could not find a parameter named: "
						+ paramName + "; will return null...");
		}
	}

	public void clear() {
		this.params.clear();
	}

	public void printParams() {
		System.out.println("Context parameters:");
		Enumeration<String> keys = this.params.keys();
		String keyAux;
		Object valueAux;
		while (keys.hasMoreElements()) {
			keyAux = keys.nextElement();
			valueAux = this.params.get(keyAux);
			System.out.println("> name: " + keyAux + "; value: " + valueAux);
		}
	}

}
