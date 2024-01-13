package com.nnga.utils.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertiesHelper {
    private static Properties properties;
    private static FileInputStream fileIn;
    private static FileOutputStream fileOut;

    static String projectPath = System.getProperty("user.dir") + "/";
    private static String propertiesFilePathRoot = "src\\test\\resources\\Data.properties";

    public static void setPropertiesFile() {
        properties = new Properties();
        try {
            fileIn = new FileInputStream(projectPath + propertiesFilePathRoot);
            properties.load(fileIn);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }

    public static String getPropValue(String KeyProp) {
        String value = null;
        try {
            value = properties.getProperty(KeyProp);
            return value;
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
        return value;
    }

    public static void setPropValue(String KeyProp, String Value) {
        try {
            fileOut = new FileOutputStream(projectPath + propertiesFilePathRoot);
            properties.setProperty(KeyProp, Value);
            properties.store(fileOut, "Set new value in properties file");
            System.out.println("Set new value in file properties success.");
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            System.out.println(exp.getCause());
            exp.printStackTrace();
        }
    }
}