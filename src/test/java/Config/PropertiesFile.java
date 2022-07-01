package Config;

import steps.playerManagementSteps;
import steps.playerSortingSteps;
import steps.loginSteps;

import java.io.*;
import java.util.Properties;

public class PropertiesFile {
    static Properties prop = new Properties();
    public static void main(String[] args) throws IOException {
        readPropertiesFile();
    }
    public static void readPropertiesFile() throws IOException {

        InputStream input = new FileInputStream("src/test/resources/config.properties");
        prop.load(input);
        loginSteps.username = prop.getProperty("username");
        loginSteps.password = prop.getProperty("password");
        loginSteps.url = prop.getProperty("url");

        playerManagementSteps.username = prop.getProperty("username");
        playerManagementSteps.password = prop.getProperty("password");
        playerManagementSteps.url = prop.getProperty("url");

        playerSortingSteps.username = prop.getProperty("username");
        playerSortingSteps.password = prop.getProperty("password");
        playerSortingSteps.url = prop.getProperty("url");
    }

    public static void writePropertiesFile() throws IOException {
        OutputStream output = new FileOutputStream("src/test/resources/config.properties");
        prop.setProperty("browser", "Chrome");
        prop.store(output, null);
    }
}
