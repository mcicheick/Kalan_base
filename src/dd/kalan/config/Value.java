package dd.kalan.config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 
 * @author Cheick Mahady Sissoko
 */
public abstract class Value {

	Value parent;
	String token;
	Object value;
	boolean leaf = false;
	LinkedList<Value> values;

	public boolean isLeaf() {
		return leaf;
	}

	public void add(Value v) throws Exception {
		if (isLeaf()) {
			throw new Exception("La feuille ne peut pas prendre d'enfant");
		}
		if (values == null) {
			throw new NullPointerException(
					"La liste des enfants doivent être initialiser ");
		}
	}

	@Override
	public String toString() {
		String text = "";
		if (token == null) {
			if (!values.isEmpty())
				text = values.get(0).toString();
		} else {
			text = print("");
		}
		return text;
	}

	String print(String tab) {
		String text = tab;
		text += tab;
		if (isLeaf()) {
			text += token + ": " + value + "\n";
		} else {
			text += token + ":\n";
			for (Value v : values) {
				text += v.print(tab + "  ");
			}
		}
		return text;
	}

	public void add(Value value, int profond) throws Exception {
		if (isLeaf()) {
			throw new Exception("La feuille ne peut pas prendre d'enfant");
		}
		if (values == null) {
			throw new NullPointerException(
					"La liste des enfants doivent ??tre initialiser ");
		}
	}

	public boolean remove(Value value) {
		return values.remove(value);
	}

	/**
	 * 
	 * @param token
	 * @param vals
	 * @return
	 */
	public List<Value> getTokens(String token, List<Value> vals) {
		if (token == null) {
			return vals;
		}
		if (token.equals(this.token)) {
			vals.add(this);
		}
		if (!isLeaf()) {
			for (Value v : values) {
				v.getTokens(token, vals);
			}
		}
		return vals;
	}

	/**
	 * 
	 * @param token
	 * @return
	 */
	public Value getToken(String token) {
		if (token == null) {
			return null;
		}
		if (token.equals(this.token)) {
			return this;
		}
		if (!isLeaf()) {
			for (Value v : values) {
				if (token.equalsIgnoreCase(v.token)) {
					return v;
				}
				Value vs = v.getToken(token);
				if (vs != null) {
					return vs;
				}
			}
		}
		return null;
	}

	public Value getToken(String token, int index) {
		if (token == null) {
			return null;
		}
		if (index < 0) {
			return null;
		}
		if (token.equals(this.token)) {
			if (index == 0) {
				return this;
			}
			index--;
		}
		if (!isLeaf()) {
			for (Value v : values) {
				if (token.equalsIgnoreCase(v.token)) {
					if (index == 0) {
						return v;
					}
				}
				Value vs = v.getToken(token, index);
				if (vs != null) {
					if (index == 0) {
						return vs;
					}
					index--;
				}
			}
		}
		return null;
	}

	public int size(String token) {
		int sz = 0;
		if (token == null)
			return sz;
		for (Value v : values) {
			if (token.equalsIgnoreCase(v.token))
				sz++;
		}
		return sz;
	}

	public DefaultMutableTreeNode toTreeNode() {
		if (isLeaf()) {
			return new DefaultMutableTreeNode(this);
		}
		DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(token);
		for (Value v : values) {
			dmtn.add(v.toTreeNode());
		}
		return dmtn;
	}

	public Integer getIntegerValue() {
		if (value == null) {
			return null;
		}
		try {
			return Integer.parseInt(value.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return
	 */
	public Long getLongValue() {
		if (value == null) {
			return null;
		}
		try {
			return Long.parseLong(value.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Boolean getBooleanValue() {
		if (value == null) {
			return null;
		}
		try {
			return Boolean.parseBoolean(value.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Double getDoubleValue() {
		if (value == null) {
			return null;
		}
		try {
			return Double.parseDouble(value.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Float getFloatValue() {
		if (value == null) {
			return null;
		}
		try {
			return Float.parseFloat(value.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Short getShortValue() {
		if (value == null) {
			return null;
		}
		try {
			return Short.parseShort(value.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getValue() {
		return value;
	}

	/**
	 * 
	 * @param filename
	 * @return
	 */
	public void xmlize(PrintWriter pw) {
		if (isLeaf()) {
			pw.print("<" + token + ">");
			pw.print(value);
			pw.print("</" + token + ">");
		} else {
			if (token == null) {
				for (Value v : values) {
					v.xmlize(pw);
				}
			} else {
				pw.print("<" + token + ">");
				for (Value v : values) {
					v.xmlize(pw);
				}
				pw.print("</" + token + ">");
			}
		}
	}
}
