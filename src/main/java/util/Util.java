package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {
    /**
     * 获取properties文件值
     *
     * @param fileName 配置文件名称
     * @param key
     */

    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        InputStream inputStream = Util.class.getClassLoader()
                .getResourceAsStream(fileName);
        if (key != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties.getProperty(key);
        }
        return null;
    }
}
