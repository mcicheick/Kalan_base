package dd.kalan.config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Cheick Mahady SISSOKO
 *
 */
public class ValueLeaf extends Value {

	/**
	 * 
	 * @param parent
	 * @param token
	 * @param value
	 */
	public ValueLeaf(Value parent, String token, Object value) {
		this.parent = parent;
		this.token = token;
		this.value = value;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}
}