package Application.Screens.OnlineTrade;

import Application.Objects.DBConnection;
import java.sql.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class for retrieving data from the XML file holding the asset list.
 */
public class JDBCOnlineTradeDataSource implements OnlineTradeDataSource {

    public static final String CREATE_TABLE =

            "CREATE TABLE IF NOT EXISTS onlinetradelistings ("
                    + "onlineTradeID INTEGER PRIMARY KEY NOT NULL UNIQUE,"
                    + "tradeType VARCHAR(5) NOT NULL,"
                    + "userID INTEGER NOT NULL,"
                    + "assetID INTEGER NOT NULL,"
                    + "departmentID INTEGER NOT NULL,"
                    + "quantity INTEGER NOT NULL,"
                    + "unitPrice INTEGER NOT NULL,"
                    + "orderDate DATETIME NOT NULL,"
                    + "orderStatus TINYINT NOT NULL" + ");";


    private static final String INSERT_ONLINETRADE = "INSERT INTO onlinetradelistings (onlineTradeID, tradeType, userID," +
            " assetID, departmentID, quantity, unitPrice, orderDate, orderStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String MODIFY_ONLINETRADE = "UPDATE onlinetradelistings SET tradeType = ?, userID = ?," +
            " assetID = ?, departmentID = ?, quantity = ?, unitPrice = ?, orderStatus = ? WHERE onlineTradeID = ?;";

    private static final String GET_ONLINETRADENAMES = "SELECT onlinetradeID, tradeType, assetID, quantity, unitPrice FROM onlinetradelistings";

    private static final String GET_ONLINETRADE = "SELECT * FROM onlinetradelistings WHERE onlineTradeID=?";

    private static final String DELETE_ONLINETRADE = "DELETE FROM onlinetradelistings WHERE onlineTradeID=?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM onlinetradelistings";

    private Connection connection;

    private PreparedStatement addOnlineTrade;

    private PreparedStatement modifyOnlineTrade;

    private PreparedStatement getOnlineTradeNameList;

    private PreparedStatement getOnlineTrade;

    private PreparedStatement deleteOnlineTrade;

    private PreparedStatement rowCount;

    public JDBCOnlineTradeDataSource() {
        connection = DBConnection.getInstance();
        try {
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

            addOnlineTrade = connection.prepareStatement(INSERT_ONLINETRADE);
            modifyOnlineTrade = connection.prepareStatement(MODIFY_ONLINETRADE);
            getOnlineTradeNameList = connection.prepareStatement(GET_ONLINETRADENAMES);
            getOnlineTrade = connection.prepareStatement(GET_ONLINETRADE);
            deleteOnlineTrade = connection.prepareStatement(DELETE_ONLINETRADE);
            rowCount = connection.prepareStatement(COUNT_ROWS);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see OnlineTradeDataSource#addOnlineTrade(OnlineTrade)
     */
    public void addOnlineTrade(OnlineTrade a) {
        try {
            addOnlineTrade.setInt(1, a.getOnlineTradeID());
            addOnlineTrade.setString(2, a.getTradeType());
            addOnlineTrade.setInt(3, a.getUserIDOnlineTrade());
            addOnlineTrade.setInt(4, a.getAssetIDOnlineTrade());
            addOnlineTrade.setInt(5, a.getDepartmentIDOnlineTrade());
            addOnlineTrade.setInt(6, a.getQuantity());
            addOnlineTrade.setInt(7, a.getUnitPrice());
            addOnlineTrade.setString(8, a.getOrderDate());
            addOnlineTrade.setInt(9, a.getOrderStatus());
            addOnlineTrade.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see OnlineTradeDataSource#modifyOnlineTrade(OnlineTrade)
     */
    public void modifyOnlineTrade(OnlineTrade a) {
        try {
            modifyOnlineTrade.setInt(8, a.getOnlineTradeID());
            modifyOnlineTrade.setString(1, a.getTradeType());
            modifyOnlineTrade.setInt(2, a.getUserIDOnlineTrade());
            modifyOnlineTrade.setInt(3, a.getAssetIDOnlineTrade());
            modifyOnlineTrade.setInt(4, a.getDepartmentIDOnlineTrade());
            modifyOnlineTrade.setInt(5, a.getQuantity());
            modifyOnlineTrade.setInt(6, a.getUnitPrice());
            modifyOnlineTrade.setInt(7, a.getOrderStatus());
            modifyOnlineTrade.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     //* @see Objectsnew.OnlineTradeDataSource#onlineTradeNameSet()
     */
    public Set<String> onlineTradeNameSet() {
        Set<String> onlineTradeNames = new TreeSet<String>();
        ResultSet rs = null;

        try {
            rs = getOnlineTradeNameList.executeQuery();
            while (rs.next()) {
                onlineTradeNames.add(rs.getInt("onlineTradeID") + " " + rs.getString("tradeType")
                        + " " + rs.getInt("assetID") + " " + rs.getInt("quantity") + " " + rs.getInt("unitPrice"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return onlineTradeNames;
    }

    /**
     * @see OnlineTradeDataSource#getOnlineTrade(Integer)
     */
    public OnlineTrade getOnlineTrade(Integer onlineTradeID) {
        OnlineTrade a = new OnlineTrade();
        ResultSet rs = null;

        try {
            getOnlineTrade.setInt(1, onlineTradeID);
            rs = getOnlineTrade.executeQuery();
            rs.next();
            a.setOnlineTradeID(rs.getInt("onlineTradeID"));
            a.setTradeType(rs.getString("tradeType"));
            a.setUserIDOnlineTrade(rs.getInt("userID"));
            a.setAssetIDOnlineTrade(rs.getInt("assetID"));
            a.setDepartmentIDOnlineTrade(rs.getInt("departmentID"));
            a.setQuantity(rs.getInt("quantity"));
            a.setUnitPrice(rs.getInt("unitPrice"));
            a.setOrderDate(rs.getString("orderDate"));
            a.setOrderStatus(rs.getInt("orderStatus"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return a;
    }

    /**
     * @see OnlineTradeDataSource#getSize()
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
     * @see OnlineTradeDataSource#deleteOnlineTrade(Integer)
     */
    public void deleteOnlineTrade(Integer onlineTradeID) {
        try {
            deleteOnlineTrade.setInt(1, onlineTradeID);
            deleteOnlineTrade.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @see OnlineTradeDataSource#close()
     */
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
