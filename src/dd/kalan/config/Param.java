package dd.kalan.config;

import java.util.Arrays;
import java.util.Iterator;

public class Param implements Iterable<String> {
	String name;
	String[] params;

	public Param() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param params
	 */
	public Param(String name, String[] params) {
		this.name = name;
		this.params = params;
	}

	@Override
	public Iterator<String> iterator() {
		return Arrays.asList(params).iterator();
	}

}