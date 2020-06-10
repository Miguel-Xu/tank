package indi.edward.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private PropertyMgr(){};
    static Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getValue(String key){
        return properties == null ? null : properties.get(key);
    }


}
