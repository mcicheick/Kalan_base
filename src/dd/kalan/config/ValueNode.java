package dd.kalan.config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.LinkedList;

/**
 * 
 * @author Cheick Mahady Sissoko
 */
public class ValueNode extends Value {

	public ValueNode(Value parent) {
		this(parent, null, new LinkedList<Value>());
	}

	public ValueNode(Value parent, String token, LinkedList<Value> values) {
		this.parent = parent;
		this.token = token;
		this.values = values;
	}

	public ValueNode(Value parent, String token) {
		this(parent, token, new LinkedList<Value>());
	}

	/**
	 * 
	 * @param value
	 * @throws Exception
	 */
	@Override
	public void add(Value value) throws Exception {
		values.add(value);
	}

	@Override
	public void add(Value value, int profond) throws Exception {
		if (profond == 1) {
			values.add(value);
		} else {
			if (!values.isEmpty()) {
				Value v = values.getLast();
				v.add(value, profond - 1);
			} else {
				add(value, profond - 1);
			}

		}

	}
}
