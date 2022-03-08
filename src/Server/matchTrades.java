package Server;

import java.sql.*;
import java.util.Locale;

import Application.Objects.DBConnection;
import Application.Screens.OnlineTrade.OnlineTrade;

public class matchTrades {

    private static final String MATCH_TRADES_SINGLE = "SELECT * FROM onlinetradelistings WHERE onlineTradeID=?";

    private static final String MATCH_TRADES_MULTIPLE = "SELECT * FROM onlinetradelistings WHERE tradeType=?";

    private static final String CHANGE_TRADE_QUANTITY = "UPDATE onlinetradelistings SET quantity = ?, orderStatus = ? WHERE onlineTradeID = ?";

    private static final String CHANGE_CREDITS = "UPDATE departments SET departmentCredits = ? WHERE departmentID = ?";

    private static final String RETRIEVE_CREDITS = "SELECT departmentCredits FROM departments WHERE departmentID = ?";

    private static final String INSERT_INVENTORY = "INSERT INTO inventories (inventoryID, assetID, departmentID, quantity, inventoryActive) VALUES (?, ?, ?, ?, ?);";

    private static final String CHANGE_INVENTORIES = "UPDATE inventories SET quantity = ? WHERE inventoryID = ?";

    private static final String RETRIEVE_INVENTORIES = "SELECT inventoryID, quantity FROM inventories WHERE departmentID = ? AND assetID = ?";

    private static final String COUNT_ROWS = "SELECT COUNT(*) FROM inventories";

    private Connection connection;

    private PreparedStatement singleOnlineTrade;

    private PreparedStatement multipleOnlineTrade;

    private PreparedStatement changeOnlineTrade;

    private PreparedStatement changeCredits;

    private PreparedStatement retrieveCredits;

    private PreparedStatement insertInventories;

    private PreparedStatement changeInventories;

    private PreparedStatement retrieveInventories;

    private PreparedStatement countRowsInventories;

    public matchTrades(Integer onlineTradeID) {
        matchTradeListings(onlineTradeID);
    }

    /**
     * The method to match online trade listings with each other. To be run everytime the database updates.
     *
     * @return boolean that is true if the trade matches, false otherwise.
     */
    public boolean matchTradeListings(Integer onlineTradeID) {
        //Setting up variables and statements
        setupStatements();
        boolean match = false;
        ResultSet rs = null;
        OnlineTrade onlineTrade = new OnlineTrade();


        //Get the details of the new online trade
        rs = getSingleTrade(rs, onlineTradeID);

        //Set the details of the online trade object
        try {
            rs.next();
            onlineTrade.setOnlineTradeID(rs.getInt("onlineTradeID"));
            onlineTrade.setTradeType(rs.getString("tradeType"));
            onlineTrade.setUserIDOnlineTrade(rs.getInt("userID"));
            onlineTrade.setAssetIDOnlineTrade(rs.getInt("assetID"));
            onlineTrade.setDepartmentIDOnlineTrade(rs.getInt("departmentID"));
            onlineTrade.setQuantity(rs.getInt("quantity"));
            onlineTrade.setUnitPrice(rs.getInt("unitPrice"));
            onlineTrade.setOrderDate(rs.getString("orderDate"));
            onlineTrade.setOrderStatus(rs.getInt("orderStatus"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        rs = null;

        //Get a list of all the online trades
        rs = getAllTrades(rs, onlineTrade);

        //Go through the trades and find any matches
        try {
            while (rs.next()) {
                //Different routes if the new order is BUY or SELL
                if (onlineTrade.getTradeType().toUpperCase(Locale.ROOT).equals("BUY")) {

                    //Checks that the assetIDs match, the departmentIDs don't match, the unit price of the buy order is
                    //above or equal to the sell order and the orders are both active
                    if (onlineTrade.getAssetIDOnlineTrade().equals(rs.getInt("assetID")) &&
                        !onlineTrade.getDepartmentIDOnlineTrade().equals(rs.getInt("departmentID")) &&
                        onlineTrade.getUnitPrice() >= rs.getInt("unitPrice") &&
                        onlineTrade.getOrderStatus() == 1 && rs.getInt("orderStatus") == 1) {

                        //Creates an object for the matched trade and adds its details
                        OnlineTrade onlineTrade2 = new OnlineTrade();
                        onlineTrade2.setOnlineTradeID(rs.getInt("onlineTradeID"));
                        onlineTrade2.setTradeType(rs.getString("tradeType"));
                        onlineTrade2.setUserIDOnlineTrade(rs.getInt("userID"));
                        onlineTrade2.setAssetIDOnlineTrade(rs.getInt("assetID"));
                        onlineTrade2.setDepartmentIDOnlineTrade(rs.getInt("departmentID"));
                        onlineTrade2.setQuantity(rs.getInt("quantity"));
                        onlineTrade2.setUnitPrice(rs.getInt("unitPrice"));
                        onlineTrade2.setOrderDate(rs.getString("orderDate"));
                        onlineTrade2.setOrderStatus(rs.getInt("orderStatus"));

                        //Attempts to process the orders
                        try {
                            orderEdit(onlineTrade, onlineTrade2);
                            match = true;
                        } catch(SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else if (onlineTrade.getTradeType().toUpperCase(Locale.ROOT).equals("SELL")){

                    //Checks that the assetIDs match, the departmentIDs don't match, the unit price of the buy order is
                    //above or equal to the sell order and the orders are both active
                    if (onlineTrade.getAssetIDOnlineTrade().equals(rs.getInt("assetID")) &&
                            !onlineTrade.getDepartmentIDOnlineTrade().equals(rs.getInt("departmentID")) &&
                            onlineTrade.getUnitPrice() <= rs.getInt("unitPrice") &&
                            onlineTrade.getOrderStatus() == 1 && rs.getInt("orderStatus") == 1) {

                        //Creates an object for the matched trade and adds its details
                        OnlineTrade onlineTrade2 = new OnlineTrade();
                        onlineTrade2.setOnlineTradeID(rs.getInt("onlineTradeID"));
                        onlineTrade2.setTradeType(rs.getString("tradeType"));
                        onlineTrade2.setUserIDOnlineTrade(rs.getInt("userID"));
                        onlineTrade2.setAssetIDOnlineTrade(rs.getInt("assetID"));
                        onlineTrade2.setDepartmentIDOnlineTrade(rs.getInt("departmentID"));
                        onlineTrade2.setQuantity(rs.getInt("quantity"));
                        onlineTrade2.setUnitPrice(rs.getInt("unitPrice"));
                        onlineTrade2.setOrderDate(rs.getString("orderDate"));
                        onlineTrade2.setOrderStatus(rs.getInt("orderStatus"));

                        //Attempts to process the orders
                        try {
                            orderEdit(onlineTrade2, onlineTrade);
                            match = true;
                        } catch(SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return match;
    }

    /**
     * Method to retrieve all the trades with trade types opposite to the inputted online trade and return the result
     * set of these trades.
     *
     * @param rs The empty result passed in
     * @param onlineTrade The online trade passed in
     * @return The result set filled with online trades with opposite trade types.
     */
    public ResultSet getAllTrades(ResultSet rs, OnlineTrade onlineTrade){
        String tradeType = null;

        //Checks which trade type the online trade object is so that we can limit the amount of results received
        if (onlineTrade.getTradeType().toUpperCase(Locale.ROOT).equals("BUY")){
            tradeType = "SELL";
        }
        else if (onlineTrade.getTradeType().toUpperCase(Locale.ROOT).equals("SELL")){
            tradeType = "BUY";
        }

        //Code to grab all the onlineTrades with the opposite trade type and put them in the result set
        try {
            multipleOnlineTrade.setString(1, tradeType);
            rs = multipleOnlineTrade.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * Gets the full information of the trade listing tied to the onlineTradeID passed in and returns the result set of
     * that information.
     *
     * @param rs The empty result set passed in
     * @param onlineTradeID The onlineTradeID passed in
     * @return The result set with the information of the trade listing.
     */
    public ResultSet getSingleTrade(ResultSet rs, Integer onlineTradeID) {

        //Code to grab the onlineTrade that is tied to the onlineTradeID inputted.
        try {
            singleOnlineTrade.setInt(1, onlineTradeID);
            rs = singleOnlineTrade.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * The method that processes the two matched trade listings passed in.
     *
     * @param onlineTrade1 The BUY trade listing
     * @param onlineTrade2 The SELL trade listing
     * @throws SQLException The exception to throw in case the processing fails
     */
    public void orderEdit(OnlineTrade onlineTrade1, OnlineTrade onlineTrade2) throws SQLException{
        //Setup variables
        Integer check;
        ResultSet rs;
        Integer differenceQuantity = 0;
        Integer price = onlineTrade2.getUnitPrice();

        //Determine which listing's quantity is larger
        check = onlineTrade1.compareToQuantity(onlineTrade2);

        //Change the quantities based on which is larger, 1 meaning
        if(check > 0){
            differenceQuantity = onlineTrade2.getQuantity();
            onlineTrade1.setQuantity(onlineTrade1.getQuantity() - onlineTrade2.getQuantity());
            onlineTrade2.setQuantity(0);
            onlineTrade2.setOrderStatus(2);
        }
        else if(check < 0){
            differenceQuantity = onlineTrade1.getQuantity();
            onlineTrade2.setQuantity(onlineTrade2.getQuantity() - onlineTrade1.getQuantity());
            onlineTrade1.setQuantity(0);
            onlineTrade1.setOrderStatus(2);
        }
        else if(check == 0){
            differenceQuantity = onlineTrade1.getQuantity();
            onlineTrade1.setQuantity(0);
            onlineTrade2.setQuantity(0);
            onlineTrade1.setOrderStatus(2);
            onlineTrade2.setOrderStatus(2);
        }
        Savepoint savePoint = connection.setSavepoint();
        try {
            //Applying changes to the first trade listing
            changeOnlineTrade.setInt(1, onlineTrade1.getQuantity());
            changeOnlineTrade.setInt(2, onlineTrade1.getOrderStatus());
            changeOnlineTrade.setInt(3, onlineTrade1.getOnlineTradeID());
            changeOnlineTrade.execute();

            //Applying changes to the second trade listing
            changeOnlineTrade.setInt(1, onlineTrade2.getQuantity());
            changeOnlineTrade.setInt(2, onlineTrade2.getOrderStatus());
            changeOnlineTrade.setInt(3, onlineTrade2.getOnlineTradeID());
            changeOnlineTrade.execute();

            //Retrieve and then Change the credits of the first trade listing's department
            retrieveCredits.setInt(1, onlineTrade1.getDepartmentIDOnlineTrade());
            rs = retrieveCredits.executeQuery();
            rs.next();
            Integer amountOwed = differenceQuantity * price;
            Integer newTotal = rs.getInt("departmentCredits") - amountOwed;
            changeCredits.setInt(1, newTotal);
            changeCredits.setInt(2, onlineTrade1.getDepartmentIDOnlineTrade());
            changeCredits.execute();

            //Retrieve and then Change the credits of the second trade listing's department
            retrieveCredits.setInt(1, onlineTrade2.getDepartmentIDOnlineTrade());
            rs = retrieveCredits.executeQuery();
            rs.next();
            newTotal = amountOwed + rs.getInt("departmentCredits");
            changeCredits.setInt(1, newTotal);
            changeCredits.setInt(2, onlineTrade2.getDepartmentIDOnlineTrade());
            changeCredits.execute();

            //Retrieve and then Change the inventories of the first trade listing
            retrieveInventories.setInt(1, onlineTrade1.getDepartmentIDOnlineTrade());
            retrieveInventories.setInt(2, onlineTrade1.getAssetIDOnlineTrade());
            rs = retrieveInventories.executeQuery();
            //Check if the buyer has an inventory of the asset, create one if they don't.
            if (!rs.isBeforeFirst()){
                rs = countRowsInventories.executeQuery();
                rs.next();
                insertInventories.setInt(1, rs.getInt(1) + 1);
                insertInventories.setInt(2, onlineTrade1.getAssetIDOnlineTrade());
                insertInventories.setInt(3, onlineTrade1.getDepartmentIDOnlineTrade());
                insertInventories.setInt(4, differenceQuantity);
                insertInventories.setBoolean(5, true);
                insertInventories.execute();
            } else {
                rs.next();
                newTotal = differenceQuantity + rs.getInt("quantity");
                changeInventories.setInt(1, newTotal);
                changeInventories.setInt(2, rs.getInt("inventoryID"));
                changeInventories.execute();
            }

            //Retrieve and then Change the inventories of the second trade listing
            retrieveInventories.setInt(1, onlineTrade2.getDepartmentIDOnlineTrade());
            retrieveInventories.setInt(2, onlineTrade2.getAssetIDOnlineTrade());
            rs = retrieveInventories.executeQuery();
            rs.next();
            newTotal = rs.getInt("quantity") - differenceQuantity;
            changeInventories.setInt(1, newTotal);
            changeInventories.setInt(2, rs.getInt("inventoryID"));
            changeInventories.execute();

        } catch (SQLException e) {
            connection.rollback(savePoint);
        }
    }

    /**
     * Method to setup the statements and connection used in the trade matching methods.
     */
    public void setupStatements() {
        connection = DBConnection.getInstance();
        try {
            singleOnlineTrade = connection.prepareStatement(MATCH_TRADES_SINGLE);
            multipleOnlineTrade = connection.prepareStatement(MATCH_TRADES_MULTIPLE);
            changeOnlineTrade = connection.prepareStatement(CHANGE_TRADE_QUANTITY);
            retrieveCredits = connection.prepareStatement(RETRIEVE_CREDITS);
            changeCredits = connection.prepareStatement(CHANGE_CREDITS);
            retrieveInventories = connection.prepareStatement(RETRIEVE_INVENTORIES);
            changeInventories = connection.prepareStatement(CHANGE_INVENTORIES);
            insertInventories = connection.prepareStatement(INSERT_INVENTORY);
            countRowsInventories = connection.prepareStatement(COUNT_ROWS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
