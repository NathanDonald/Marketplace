package Application.Screens.DepartmentScreen;

import Application.Objects.DBConnection;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for retrieving data from the XML file holding the department list.
 */
public class JDBCDepartmentDataSource implements DepartmentDataSource {

    private static final Integer VARCHAR_LIMIT = 60;

    public static final String CREATE_TABLE =

            "CREATE TABLE IF NOT EXISTS departments ("
                    + "departmentID INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "departmentName VARCHAR(" + VARCHAR_LIMIT + "),"
                    + "departmentManager INTEGER,"
                    + "departmentCredits INTEGER,"
                    + "departmentActive TINYINT" + ");";


    private static final String INSERT_DEPARTMENT = "INSERT INTO departments (departmentID, departmentName, departmentManager, departmentCredits, departmentActive) VALUES (?, ?, ?, ?, ?);";

    private static final String GET_DEPARTMENTIDS = "SELECT departmentID FROM departments";

    private static final String GET_DEPARTMENT = "SELECT * FROM departments WHERE departmentID=?";

    private static final String DELETE_DEPARTMENT = "DELETE FROM departments WHERE departmentID=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM departments";

    private Connection connection;

    private PreparedStatement addDepartment;

    private PreparedStatement getDepartmentIDList;

    private PreparedStatement getDepartment;

    private PreparedStatement deleteDepartment;

    private PreparedStatement rowCount;

    public JDBCDepartmentDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            addDepartment = connection.prepareStatement(INSERT_DEPARTMENT);
            getDepartmentIDList = connection.prepareStatement(GET_DEPARTMENTIDS);
            getDepartment = connection.prepareStatement(GET_DEPARTMENT);
            deleteDepartment = connection.prepareStatement(DELETE_DEPARTMENT);
            rowCount = connection.prepareStatement(COUNT_ROWS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see DepartmentDataSource#addDepartment(Department)
     */
    public void addDepartment(Department d) {
        try {
            addDepartment.setInt(1, d.getDepartmentID());
            addDepartment.setString(2, d.getDepartmentName());
            addDepartment.setInt(3, d.getDepartmentManager());
            addDepartment.setInt(4, d.getDepartmentCredits());
            addDepartment.setBoolean(5, d.getDepartmentActive());
            addDepartment.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     //* @see Objectsnew.DepartmentDataSource#departmentIDSet()
     */
    public Set<Integer> departmentIDSet() {
        Set<Integer> departmentIDs = new TreeSet<Integer>();
        ResultSet rs = null;

        try {
            rs = getDepartmentIDList.executeQuery();
            while (rs.next()) {
                departmentIDs.add(rs.getInt("departmentID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return departmentIDs;
    }

    /**
     * @see DepartmentDataSource#getDepartment(Integer)
     */
    public Department getDepartment(Integer departmentID) {
        Department d = new Department();
        ResultSet rs = null;

        try {
            getDepartment.setInt(1, departmentID);
            rs = getDepartment.executeQuery();
            rs.next();
            d.setDepartmentID(rs.getInt("departmentID"));
            d.setDepartmentName(rs.getString("departmentName"));
            d.setDepartmentManager(rs.getInt("departmentManager"));
            d.setDepartmentCredits(rs.getInt("departmentCredits"));
            d.setDepartmentActive(rs.getBoolean("departmentActive"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return d;
    }

    /**
     * @see DepartmentDataSource#getSize()
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
     * @see DepartmentDataSource#deleteDepartment(Integer)
     */
    public void deleteDepartment(Integer departmentID) {
        try {
            deleteDepartment.setInt(1, departmentID);
            deleteDepartment.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see DepartmentDataSource#close()
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
