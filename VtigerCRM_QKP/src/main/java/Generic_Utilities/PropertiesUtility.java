package Generic_Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	//this properties class object use only for this class only so keep as private for data secure
	private Properties Property;
	/**
	 * This method initializes Properties file
	 * @param filepath
	 */
	public void propertiesInit(String filepath) {
		FileInputStream fis=null;
		try {
			fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Property=new Properties();
		try {
			Property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method fetches the value of the key specified from properties file
	 * @param key
	 * @return 
	 */
public String readFromproperties(String key) {
	return Property.getProperty(key);
	
	
}
	
}

