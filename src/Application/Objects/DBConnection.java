//========//========//========//========//========
package Application.Objects;

//========//========//========//========//========
import static Server.GetPropertyValues.*;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

//========//========//========//========
public class DBConnection
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * The singleton instance of the database connection.
     */
    private static Connection instance = null;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Constructor intializes the connection.
     */
    private DBConnection()
    {
        Properties props = new Properties();
        FileInputStream in = null;
        try {
            instance = DriverManager.getConnection("jdbc:mariadb://localhost:" + getDbPort() + "/" + getDbName(), getDbUser(), getDbPassword());
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Provides global access to the singleton instance of the UrlSet.
     *
     * @return a handle to the singleton instance of the UrlSet.
     */
    public static Connection getInstance()
    {
        new DBConnection();
        return instance;
    }
}

