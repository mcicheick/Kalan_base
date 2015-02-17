/**
 * 
 */
package dd.kalan.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * This class reads a configure file to initiate container parameters <BR>
 * It can read to kind of file format. CFG and XML. <BR>
 * 
 * @author Cheick Mahady Sissoko
 * @date 24 avr. 2014 15:28:35
 */
public class Config implements Iterable<Value> {
	Set<String> keySet;

	enum Type {
		XML, CFG
	}

	String name;
	private Value value;
	Type type;

	/**
	 * the default name is Config
	 */
	public Config() {
		this("Config");
	}

	/**
	 * The default type is XML
	 * 
	 * @param name
	 */
	public Config(String name) {
		this(name, Type.XML);
	}

	/**
	 * @param name
	 * @param type
	 */
	public Config(String name, Type type) {
		super();
		this.name = name;
		this.type = type;
		this.value = new ValueNode(null, name);
		this.keySet = new HashSet<String>();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		Value param = new ValueNode(this.value, "param");
		try {
			param.add(new ValueLeaf(param, key, value));
			this.value.add(param);
			this.keySet.add(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object getParam(String key) {
		Value v = this.value.getToken(key);
		if (v == null)
			return null;
		return v.value;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Object getParam(String key, Object defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return v.value;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Integer getParamAsInteger(String key) {
		Value v = this.value.getToken(key);
		if (v == null)
			return null;
		return v.getIntegerValue();
	}

	/**
	 * @param parametersUserPageLength
	 * @param l
	 * @return
	 */
	public Integer getParamAsInteger(String key, Integer defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return v.getIntegerValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Long getParamAsLong(String key) {
		Value v = this.value.getToken(key);
		if (v == null)
			return null;
		return v.getLongValue();
	}

	/**
	 * @param parametersUserPageLength
	 * @param l
	 * @return
	 */
	public Long getParamAsLong(String key, Long defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return v.getLongValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Double getParamAsDouble(String key) {
		Value v = this.value.getToken(key);
		if (v == null)
			return null;
		return v.getDoubleValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Double getParamAsDouble(String key, Double defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return v.getDoubleValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Float getParamAsFloat(String key) {
		Value v = this.value.getToken(key);
		if (v == null)
			return null;
		return v.getFloatValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Float getParamAsFloat(String key, Float defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return v.getFloatValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Boolean getParamAsBoolean(String key) {
		Value v = this.value.getToken(key);
		if (v == null)
			return false;
		return v.getBooleanValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Boolean getParamAsBoolean(String key, Boolean defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return v.getBooleanValue();
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getParamAsString(String key) {
		return (String) getParam(key);
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public String getParamAsString(String key, String defaultValue) {
		Value v = this.value.getToken(key);
		if (v == null)
			return defaultValue;
		return (String) getParam(key);
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public boolean paramExists(String param) {
		return getParam(param) != null;
	}

	public void setParam(String key, String value) {
		Value v = this.value.getToken(key);
		if (v == null) {
			Value param = new ValueNode(this.value, "param");
			try {
				param.add(new ValueLeaf(param, key, value));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			v.value = value;
		}
	}

	private void format() {
		List<Value> lists = new ArrayList<Value>();
		List<Value> params = value.getTokens("param", lists);
		for (Value param : params) {
			Value lname = param.getToken("name");
			Value lvalue = param.getToken("value");
			if (lname == null || lvalue == null) {
				return;
			}
			param.remove(lname);
			param.remove(lvalue);
			ValueLeaf vleaf = new ValueLeaf(param, (String) lname.value,
					lvalue.value);
			try {
				param.add(vleaf);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static Config readFromFile(String filename, String... args)
			throws IOException {
		int extIndex = filename.lastIndexOf(".");
		String name = filename;
		if (extIndex > 0) {
			name = filename.substring(0, extIndex);
		}
		String extension = filename.substring(extIndex + 1);
		Type type;
		try {
			type = Type.valueOf(extension.toUpperCase());
		} catch (IllegalArgumentException e) {
			type = Type.XML;
		}
		Config config = new Config(name, type);
		switch (type) {
		case CFG:
			Param param = ParamReader.readParam(new File(filename), args);
			if (param == null) {
				System.err.println("Fichier de configuration introuvable.");
				System.exit(1);
			}
			config.name = param.name;
			for (int i = 0; i < param.params.length; i++) {
				String ptab[] = param.params[i].split("(\t| )+", 2);
				if (param.params[i].contains("=")) {
					ptab = param.params[i].split("=", 2);
				}
				switch (ptab.length) {
				case 1:
					config.put(ptab[0].trim(), "true");
					break;
				case 2:
					String key = ptab[0];
					String value = ptab[1];
					if (value.matches(".+#.+")) {
						value = value.split("#")[0];
					}
					config.put(key.trim(), value.trim());
					break;
				default:
					System.out.println("Param incorrect");
					break;
				}
			}
			break;
		case XML:
			try {
				config.value = XmlParser.parse(new File(filename));
				config.format();
			} catch (UnsupportedEncodingException e) {
				System.err.println(e);
			} catch (ParserConfigurationException e) {
				System.err.println(e);
			} catch (SAXException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
			break;
		default:
			break;
		}
		return config;
	}

	/**
	 * output in current dir
	 */
	public void write() {
		write(".", type.name());
	}

	/**
	 * 
	 * @param outdir
	 */
	public void write(String outdir) {
		if (outdir == null) {
			write();
			return;
		}
		write(outdir, type.name());
	}

	/**
	 * 
	 * @param outdir
	 * @param type
	 *            xml or cfg
	 */
	public void write(String outdir, String type) {
		if (type == null) {
			write(outdir);
			return;
		}
		write(outdir, type, name);
	}

	/**
	 * 
	 * @param outdir
	 *            output dir
	 * @param type
	 *            xml or cfg
	 * @param name
	 *            filename
	 * 
	 */
	public void write(String outdir, String type, String name) {
		if (outdir == null) {
			write();
			return;
		}
		if (name == null) {
			write(outdir, type);
			return;
		}
		File tmp = new File(name);
		String filename = outdir + "/"
				+ tmp.getName().replaceAll(".+\\..+", "") + "."
				+ type.toLowerCase();
		Type t = this.type;
		try {
			t = Type.valueOf(type.toUpperCase());
		} catch (IllegalArgumentException e) {
		}
		try {
			PrintWriter pw = new PrintWriter(new File(filename));
			switch (t) {
			case CFG:
				pw.println("CFG");
				pw.println("%=" + this.name);
				for (Value param : this) {
					for (int i = 0; i < param.values.size(); i++) {
						Value leaf = param.values.get(i);
						pw.println(leaf.token + "=" + leaf.value);
					}
				}
				pw.println("%End");
				pw.close();
				break;

			case XML:
				pw.println("<config version=\"1\">");
				for (Value param : this) {
					for (int i = 0; i < param.values.size(); i++) {
						Value leaf = param.values.get(i);
						pw.print("  ");
						pw.println("<param name=\"" + leaf.token + "\">"
								+ leaf.value + "</param>");
					}
				}
				pw.println("</config>");
				pw.close();
				break;

			default:
				pw.close();
				break;
			}
		} catch (FileNotFoundException e) {
			System.err.println("Fichier introuvable");
			System.err.println(e);
		}
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public Iterator<Value> iterator() {
		return value.getTokens("param", new ArrayList<Value>()).iterator();
	}

}
