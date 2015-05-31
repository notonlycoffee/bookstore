package cn.itcast.factory;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {

	private Properties conf = new Properties();
	private DaoFactory() {
		try {
			InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("cn/itcast/factory/factory.properties");
			conf.load(in);
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		
	};
	private static final DaoFactory instance = new DaoFactory();
	public static DaoFactory getInstances() {
		return instance;
	}
	
	
	public <T> T createDao(Class<T> interfaceClass) {
		try {
			String key = interfaceClass.getSimpleName();
			String className = conf.getProperty(key);
			return (T) Class.forName(className).newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
}
