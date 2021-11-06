package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfigLoader {

    private static final String CONFIG_NAME = "props.properties";
    private static final Properties GLOBAL_CONFIG = new Properties();
     private static FileInputStream fileInputStream;



    public static String getProperty(String property) {
        try {
            fileInputStream=new FileInputStream(CONFIG_NAME);
            try {
                GLOBAL_CONFIG.load(fileInputStream);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return GLOBAL_CONFIG.getProperty(property);
    }
}


