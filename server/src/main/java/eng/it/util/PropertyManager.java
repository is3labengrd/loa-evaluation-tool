package eng.it.util;



import java.io.IOException;
import java.util.Properties;



public class PropertyManager {

	private static Properties props;

	static {
		props = new Properties();
		try {
			props.load(PropertyManager.class.getClassLoader().getResourceAsStream("config.property"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(SARProperty propName) {
		return props.getProperty(propName.toString());
	}

}
