package dd.kalan.config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 
 * @author Cheick Mahady Sissoko
 */
public class XmlParser {

	/**
	 * 
	 * @param xml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static Value parse(String xml) throws ParserConfigurationException,
			SAXException, UnsupportedEncodingException, IOException {
		xml = xml.replaceAll("><", ">\n<");
		SAXParserFactory spfac = SAXParserFactory.newInstance();
		SAXParser sp = spfac.newSAXParser();
		MyHandler handler = new MyHandler();
		InputStream xmlStream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
		sp.parse(new InputSource(xmlStream), handler);
		return handler.value;
	}

	/**
	 * 
	 * @param xmlFile
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static Value parse(File xmlFile)
			throws ParserConfigurationException, SAXException,
			UnsupportedEncodingException, IOException {
		SAXParserFactory spfac = SAXParserFactory.newInstance();
		SAXParser sp = spfac.newSAXParser();
		MyHandler handler = new MyHandler();
		InputStream xmlStream = new FileInputStream(xmlFile);
		sp.parse(new InputSource(xmlStream), handler);
		return handler.value;
	}
}
