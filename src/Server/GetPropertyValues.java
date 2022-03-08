//========//========//========
package Server;

//========//========//========//========//========
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

//========//========//========//========//========//========//========//========
/**
 * Holds methods to retrieve specific data from the configuration file
 */
public class GetPropertyValues
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private final static String CONFIG_FILE = "Server/config.properties";
    private final static String CONFIG_FILE_ERROR_MESSAGE = "Can't find configuration file config.properties";
    public final static String PORT = "port";
    public final static String HOST = "host";
    public final static String DB_PORT = "dbPort";
    public final static String DB_NAME = "dbName";
    public final static String DB_USER = "dbUser";
    public final static String DB_PASSWORD = "dbPassword";
    private static Properties prop = new Properties();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * initProperty():
     * Common method for initialising the Properties variable for methods within GetPropertyValues.
     * @throws IOException
     */
    public static void initProperty() throws IOException
    {
        ClassLoader classLoader = GetPropertyValues.class.getClassLoader();
        // Make sure that the configuration file exists
        URL res = Objects.requireNonNull(classLoader.getResource(CONFIG_FILE), CONFIG_FILE_ERROR_MESSAGE);
        InputStream is = GetPropertyValues.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        // load the properties file
        prop.load(is);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getPropertyAsString():
     * Returns the specified configuration file property as a String.
     *
     * @param propName The String reference correlating to the desired property to
     *                 retrieve from the specified configuration file.
     * @return
     */
    public static String getPropAsString(String propName)
    {
        try {
            initProperty();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(propName);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getPropertyAsInteger():
     * Returns the specified configuration file property as an Integer.
     *
     * @param propName The String reference correlating to the desired property to
     *                 retrieve from the specified configuration file.
     * @return
     */
    public static Integer getPropAsInteger(String propName) {
        try {
            initProperty();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(prop.getProperty(propName));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static String getHost()
    {
        return getPropAsString(HOST);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static Integer getPort()
    {
        return getPropAsInteger(PORT);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static String getDbPort()
    {
        return getPropAsString(DB_PORT);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static String getDbName()
    {
        return getPropAsString(DB_NAME);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static String getDbUser()
    {
        return getPropAsString(DB_USER);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static String getDbPassword()
    {
        return getPropAsString(DB_PASSWORD);
    }
}
