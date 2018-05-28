package com.wxt.tools;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 从.properties文件中读取key,value数据
 *
 * 不想在testng.xml文件中放入大量的参数值，想独立存储一些数据可以使用属性文件
 *
 */
public class PropertiesDataProvider {
	/**
	 * 从.properties文件中读取key,value数据
	 * @param configFilePath 配置文件路径
	 * @param key
	 * @return
	 */
	public static String getPropertiesData(String configFilePath, String key) {
		Configuration config = null;
		try {
			config = new PropertiesConfiguration(configFilePath);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return String.valueOf(config.getProperty(key));

	}
}

