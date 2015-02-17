/**
 * 
 */
package dd.kalan.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Cheick Mahady Sissoko
 * @date 9 avr. 2014 23:19:15
 */
public class ParamReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TreeMap<String, ArrayList<String[]>> map =
		// readParams("cfg/Merged.cfg",
		// args);
		// for (String key : readParam("cfg/Merged.cfg",
		// args)) {
		// System.out.println(key);
		// }
		// Config config = Config.readFromFile("config.cfg", args);
		// config.type = Type.CFG;
		// config.name = "Config";
		// config.write(".", "xml");
		String regex = "((\t| )+?=(\t| )+?|(\t| )+)";
		String text = "c   =cheick";
		// System.out.println(text.matches(regex));
		System.out.println(Arrays.toString(text.split(regex, 2)));
	}

	public static Param readParam(String config, String... args)
			throws IOException {
		return readParam(new File(config), args);
	}

	/**
	 * 
	 * @param config
	 * @return
	 * @throws IOException
	 */
	public static Param readParam(File config, String... args)
			throws IOException {
		TreeMap<String, ArrayList<String[]>> map = new TreeMap<String, ArrayList<String[]>>();
		ArrayList<String> paramsList = new ArrayList<String>();
		int row = 0;
		Pattern pattern = Pattern.compile("\\$[0-9]+");
		String currentCFG = "";
		BufferedReader br = new BufferedReader(new FileReader(config));
		String line = br.readLine();// Type of file
		row++;
		while (line != null
				&& (line.trim().startsWith("#") || line.matches("[ ]+") || line
						.isEmpty())) {
			line = br.readLine();
			row++;
		}
		if (line == null) {
			br.close();
			throw new Error("Empty configuration file " + row);
		}
		if (line.contains("#")) {
			line = line.substring(0, line.indexOf("#")).trim();
		}
		if (!line.equalsIgnoreCase("CFG")) {
			br.close();
			throw new Error("Config file unsupported " + row);
		}
		while (line != null) {
			line = br.readLine();
			row++;
			while (line != null
					&& (line.trim().startsWith("#") || line.matches("[ ]+") || line
							.isEmpty())) {
				line = br.readLine();
				row++;
			}
			if (line == null) {
				break;
			}
			if (line.contains("#")) {
				line = line.substring(0, line.indexOf("#"));
			}
			if (line.startsWith("%=".trim())) {
				currentCFG = line.substring(line.indexOf("%=") + 2).trim();
				continue;
			}
			if (line.startsWith("%End".trim())) {
				int length = paramsList.size();
				// System.out.println(length);
				String[] params = new String[length];
				for (int i = 0; i < length; i++) {
					params[i] = paramsList.get(i);
				}
				ArrayList<String[]> pms = map.get(currentCFG);
				if (pms == null) {
					pms = new ArrayList<String[]>();
					map.put(currentCFG, pms);
				}
				pms.add(params);
				paramsList = new ArrayList<String>();
				br.close();
				Param param = new Param(currentCFG, params);
				return param;
			}
			Matcher m = pattern.matcher(line);
			while (m.find()) {
				String arg = m.group();
				int index = Integer.parseInt(arg.substring(1));
				if (index < args.length) {
					line = line.replaceFirst(pattern.pattern(), args[index]);
				} else {
					line = line.replaceFirst(pattern.pattern(), "");
				}
			}
			paramsList.add(line);
		}
		br.close();
		return null;
	}

	public static TreeMap<String, ArrayList<String[]>> readParams(
			String config, String... args) {
		return readParams(new File(config), args);
	}

	/**
	 * 
	 * @param config
	 * @return
	 */
	public static TreeMap<String, ArrayList<String[]>> readParams(File config,
			String... args) {
		TreeMap<String, ArrayList<String[]>> map = new TreeMap<String, ArrayList<String[]>>();
		ArrayList<String> paramsList = new ArrayList<String>();
		int row = 0;
		Pattern pattern = Pattern.compile("\\$[0-9]+");
		String currentCFG = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(config));
			String line = br.readLine();// Type of file
			row++;
			while (line != null
					&& (line.trim().startsWith("#") || line.matches("[ ]+") || line
							.isEmpty())) {
				line = br.readLine();
				row++;
			}
			if (line == null) {
				br.close();
				throw new Error("Empty configuration file " + row);
			}
			if (!line.equalsIgnoreCase("CFG")) {
				br.close();
				throw new Error("Config file unsupported " + row);
			}
			while (line != null) {
				line = br.readLine();
				row++;
				while (line != null
						&& (line.trim().startsWith("#") || line.matches("[ ]+") || line
								.isEmpty())) {
					line = br.readLine();
					row++;
				}
				if (line == null) {
					break;
				}
				if (line.startsWith("%=".trim())) {
					currentCFG = line.substring(line.indexOf("%=") + 2).trim();
					continue;
				}
				if (line.startsWith("%End".trim())) {
					int length = paramsList.size();
					// System.out.println(length);
					String[] params = new String[length];
					for (int i = 0; i < length; i++) {
						params[i] = paramsList.get(i);
					}
					ArrayList<String[]> pms = map.get(currentCFG);
					if (pms == null) {
						pms = new ArrayList<String[]>();
						map.put(currentCFG, pms);
					}
					pms.add(params);
					paramsList = new ArrayList<String>();
					continue;
				}
				// if (!line.startsWith("--") && line.contains("=")) {
				// line = "--" + line;
				// }
				// if (line.equals("help") || line.equals("trace")) {
				// line = "--" + line;
				// }
				Matcher m = pattern.matcher(line);
				while (m.find()) {
					String arg = m.group();
					int index = Integer.parseInt(arg.substring(1));
					if (index < args.length) {
						line = line
								.replaceFirst(pattern.pattern(), args[index]);
					} else {
						line = line.replaceFirst(pattern.pattern(), "");
					}
				}
				paramsList.add(line);
			}
			br.close();
			// return params;
			return map;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
