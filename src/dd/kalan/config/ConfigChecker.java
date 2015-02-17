/**
 * 
 */
package dd.kalan.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sissoko
 *
 */
public class ConfigChecker {

	private Map<String, ConfigItem> items = new HashMap<String, ConfigItem>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private class ConfigItem {
		String name;
		boolean required;
		String message;

		/**
		 * @param name
		 * @param required
		 * @param message
		 */
		public ConfigItem(String name, boolean required, String message) {
			this.name = name;
			this.required = required;
			this.message = message;
		}

	}

	/**
	 * 
	 * @param name
	 * @param required
	 * @param message
	 */
	public void addConfigItem(String name, boolean required, String message) {
		if (name == null || name.isEmpty()) {
			throw new NullPointerException();
		}
		ConfigItem item = new ConfigItem(name, required, message);
		items.put(name, item);
	}

	/**
	 * 
	 * @param config
	 * @return
	 */
	public String check(Config config) {
		String param = null;
		for (String key : items.keySet()) {
			ConfigItem item = items.get(key);
			String formConfig = config.getParamAsString(key);
			if (formConfig == null && item.required) {
				System.out.println(item.name + " : " + item.message);
				return item.name;
			}
		}
		return param;
	}
}
