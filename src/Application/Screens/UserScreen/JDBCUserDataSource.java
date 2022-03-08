package Application.Screens.UserScreen;

import Application.Objects.DBConnection;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for retrieving data from the XML file holding the asset list.
 */
public class JDBCUserDataSource implements UserDataSource {

    private static final Integer VARCHAR_LIMIT = 60;
    private static final Integer VARCHAR_ENCRYPTION = 64;

    public static final String CREATE_TABLE =

    "CREATE TABLE IF NOT EXISTS users ("
                    + "userID INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "firstName VARCHAR(" + VARCHAR_LIMIT + ") NOT NULL,"
                    + "surname VARCHAR(" + VARCHAR_LIMIT + ") NOT NULL,"
                    + "email VARCHAR(" + VARCHAR_LIMIT*2 + ") NOT NULL UNIQUE,"
                    + "departmentID INTEGER NOT NULL,"
                    + "permissionLevel VARCHAR(5) NOT NULL,"
                    + "password VARCHAR(" + VARCHAR_ENCRYPTION + ") NOT NULL,"
                    + "salt VARCHAR(" + VARCHAR_ENCRYPTION + ") NOT NULL,"
                    + "userIsActive TINYINT NOT NULL" + ");";


    private static final String INSERT_USER = "INSERT INTO users (userID, firstName, surname, email, departmentID, permissionLevel, password, salt, userIsActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String MODIFY_USER = "UPDATE users SET firstName = ?, surname = ?, email = ?, departmentID = ?, permissionLevel = ?, password = ?, salt = ?, userIsActive = ? WHERE userID = ?;";

    private static final String GET_USERNAMES = "SELECT firstName, surname, userID FROM users";

    private static final String GET_USER = "SELECT * FROM users WHERE userID=?";

    private static final String DELETE_USER = "DELETE FROM users WHERE userID=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM users";

    public static final String CHANGE_PASSWORD = "UPDATE users SET Password=?, Salt=? WHERE UserID=?";

    private Connection connection;

    private PreparedStatement addUser;

    private PreparedStatement modifyUser;

    private PreparedStatement getUserNameList;

    private PreparedStatement getUser;

    private PreparedStatement deleteUser;

    private PreparedStatement rowCount;

    private PreparedStatement changePassword;

   public JDBCUserDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            addUser = connection.prepareStatement(INSERT_USER);
            modifyUser = connection.prepareStatement(MODIFY_USER);
            getUserNameList = connection.prepareStatement(GET_USERNAMES);
            getUser = connection.prepareStatement(GET_USER);
            deleteUser = connection.prepareStatement(DELETE_USER);
            rowCount = connection.prepareStatement(COUNT_ROWS);
            changePassword = connection.prepareStatement(CHANGE_PASSWORD);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
   }

    /**
     * @see UserDataSource#addUser(User)
     */
    public void addUser(User a) {
        try {
            addUser.setInt(1, a.getUserID());
            addUser.setString(2, a.getFirstName());
            addUser.setString(3, a.getSurname());
            addUser.setString(4, a.getEmail());
            addUser.setInt(5, a.getDepartmentID());
            addUser.setString(6, a.getPermissionLevel());
            addUser.setString(7, a.getPassword());
            addUser.setString(8, a.getSalt());
            addUser.setBoolean(9, a.getUserIsActive());
            addUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see UserDataSource#modifyUser(User)
     */
    public void modifyUser(User a) {
        try {
            modifyUser.setInt(9, a.getUserID());
            modifyUser.setString(1, a.getFirstName());
            modifyUser.setString(2, a.getSurname());
            modifyUser.setString(3, a.getEmail());
            modifyUser.setInt(4, a.getDepartmentID());
            modifyUser.setString(5, a.getPermissionLevel());
            modifyUser.setString(6, a.getPassword());
            modifyUser.setString(7, a.getSalt());
            modifyUser.setBoolean(8, a.getUserIsActive());
            modifyUser.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     //* @see Objectsnew.UserDataSource#userNameSet()
     */
    public Set<String> userNameSet() {
        Set<String> userNames = new TreeSet<String>();
        ResultSet rs = null;


        try {
            rs = getUserNameList.executeQuery();
            while (rs.next()) {
                userNames.add(rs.getInt("userID") + " " + rs.getString("firstName")
                        + " " + rs.getString("surname"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userNames;
    }

    /**
     * @see UserDataSource#getUser(Integer)
     */
    public User getUser(Integer userID) {
        User a = new User();
        ResultSet rs = null;

        try {
            getUser.setInt(1, userID);
            rs = getUser.executeQuery();
            rs.next();
            a.setUserID(rs.getInt("userID"));
            a.setFirstName(rs.getString("firstName"));
            a.setSurname(rs.getString("surname"));
            a.setEmail(rs.getString("email"));
            a.setDepartmentID(rs.getInt("departmentID"));
            a.setPermissionLevel(rs.getString("permissionLevel"));
            a.setPassword(rs.getString("password"));
            a.setSalt(rs.getString("salt"));
            a.setUserIsActive(rs.getBoolean("userIsActive"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    /**
     * @see UserDataSource#getSize()
     */
    public int getSize() {
        ResultSet rs = null;
        int rows = 0;

        try {
            rs = rowCount.executeQuery();
            rs.next();
            rows = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rows;
    }

    /**
     * @see UserDataSource#deleteUser(Integer)
     */
    public void deleteUser(Integer userID) {
        try {
            deleteUser.setInt(1, userID);
            deleteUser.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see UserDataSource#close()
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}