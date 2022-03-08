package Application.Screens.InventoryScreen;

import Application.Objects.DBConnection;

import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for retrieving data from the XML file holding the inventory list.
 */
public class JDBCInventoryDataSource implements InventoryDataSource {

    public static final String CREATE_TABLE =

    "CREATE TABLE IF NOT EXISTS inventories ("
                    + "inventoryID INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "assetID INTEGER NOT NULL,"
                    + "departmentID INTEGER NOT NULL,"
                    + "quantity INTEGER NOT NULL,"
                    + "inventoryActive TINYINT NOT NULL" + ");";


    private static final String INSERT_INVENTORY = "INSERT INTO inventories (inventoryID, assetID, departmentID, quantity, inventoryActive) VALUES (?, ?, ?, ?, ?);";

    private static final String MODIFY_INVENTORY = "UPDATE inventories SET assetID = ?, departmentID = ?, quantity = ?, inventoryActive = ? WHERE inventoryID = ?;";

    private static final String GET_INVENTORYIDS = "SELECT inventoryID FROM inventories";

    private static final String GET_INVENTORY = "SELECT * FROM inventories WHERE inventoryID=?";

    private static final String DELETE_INVENTORY = "DELETE FROM inventories WHERE inventoryID=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM inventories";

    private Connection connection;

    private PreparedStatement addInventory;

    private PreparedStatement modifyInventory;

    private PreparedStatement getInventoryIDList;

    private PreparedStatement getInventory;

    private PreparedStatement deleteInventory;

    private PreparedStatement rowCount;

   public JDBCInventoryDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            addInventory = connection.prepareStatement(INSERT_INVENTORY);
            modifyInventory = connection.prepareStatement(MODIFY_INVENTORY);
            getInventoryIDList = connection.prepareStatement(GET_INVENTORYIDS);
            getInventory = connection.prepareStatement(GET_INVENTORY);
            deleteInventory = connection.prepareStatement(DELETE_INVENTORY);
            rowCount = connection.prepareStatement(COUNT_ROWS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
   }

    /**
     * @see InventoryDataSource#addInventory(Inventory)
     */
    public void addInventory(Inventory i) {
        try {
            addInventory.setInt(1, i.getInventoryID());
            addInventory.setInt(2, i.getAssetID());
            addInventory.setInt(3, i.getDepartmentID());
            addInventory.setInt(4, i.getQuantity());
            addInventory.setBoolean(5, i.getInventoryActive());
            addInventory.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see InventoryDataSource#modifyInventory(Inventory)
     */
    public void modifyInventory(Inventory a) {
        try {
            modifyInventory.setInt(5, a.getInventoryID());
            modifyInventory.setInt(1, a.getAssetID());
            modifyInventory.setInt(2, a.getDepartmentID());
            modifyInventory.setInt(3, a.getQuantity());
            modifyInventory.setBoolean(4, a.getInventoryActive());
            modifyInventory.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     //* @see Objectsnew.InventoryDataSource#inventoryIDSet()
     */
    public Set<Integer> inventoryIDSet() {
        Set<Integer> inventoryID = new TreeSet<Integer>();
        ResultSet rs = null;

        try {
            rs = getInventoryIDList.executeQuery();
            while (rs.next()) {
                inventoryID.add(rs.getInt("inventoryID"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return inventoryID;
    }

    /**
     * @see InventoryDataSource#getInventory(Integer)
     */
    public Inventory getInventory(Integer inventoryID) {
        Inventory i = new Inventory();
        ResultSet rs = null;

        try {
            getInventory.setInt(1, inventoryID);
            rs = getInventory.executeQuery();
            rs.next();
            i.setInventoryID(rs.getInt("inventoryID"));
            i.setAssetID(rs.getInt("assetID"));
            i.setDepartmentID(rs.getInt("departmentID"));
            i.setQuantity(rs.getInt("quantity"));
            i.setInventoryActive(rs.getBoolean("inventoryActive"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return i;
    }

    /**
     * @see InventoryDataSource#getSize()
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
     * @see InventoryDataSource#deleteInventory(Integer)
     */
    public void deleteInventory(Integer inventoryID) {
        try {
            deleteInventory.setInt(1, inventoryID);
            deleteInventory.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see InventoryDataSource#close()
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}