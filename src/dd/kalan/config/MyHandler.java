package dd.kalan.config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author Cheick Mahady Sissoko
 */
public class MyHandler extends DefaultHandler {

	Value value;
	Value current;
	String item = null;
	String qName;
	int profond = 0;
	boolean flag = false, start = true;

	/**
	 * 
	 * @throws SAXException
	 */
	@Override
	public void startDocument() throws SAXException {
		value = new ValueNode(null);
	}

	/**
	 * 
	 * @throws SAXException
	 */
	@Override
	public void endDocument() throws SAXException {
	}

	/**
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 * @param attributes
	 * @throws SAXException
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.qName = qName;
		flag = true;
		profond++;
		try {
			current = new ValueNode(current, qName);
			value.add(current, profond);
			for (int i = 0; i < attributes.getLength(); i++) {
				String token = attributes.getQName(i);
				String value = attributes.getValue(i);
				ValueLeaf vl = new ValueLeaf(current, token, value);
				// System.out.println(vl);
				current.add(vl);
			}
		} catch (Exception ex) {
			Logger.getLogger(MyHandler.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	/**
	 * 
	 * @param uri
	 * @param localName
	 * @param qName
	 * @throws SAXException
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		flag = false;
		profond--;
	}

	private boolean condition(String item) {
		return item == null || item.matches("[ \n\t]+") || item.isEmpty()
				|| item.equalsIgnoreCase("");
	}

	@Override
	public void characters(char[] chars, int i, int i1) throws SAXException {
		item = new String(chars, i, i1);
		if (flag) {
			if (!condition(item)) {
				try {
					current.value = item;
					if (current.values.isEmpty())
						current.leaf = true;
					else {
						String value = item.replaceAll("[\n]+", "");
						current.add(new ValueLeaf(current, "value", value));
					}
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
			flag = false;
		} else {
			try {
				if (!condition(item)) {
					String value = item.replaceAll("[\n]+", "");
					current.add(new ValueLeaf(current, "value", value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
		// System.out.println("ignorableWhitespace: "
		// + new String(ch, start, length));
	}
}
