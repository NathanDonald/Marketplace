package Server.RequestHanlders;

import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;
import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;

/**
 * Handle request to delete a buy or sell order from the market
 */
public class DeleteOrder {
    /**
     * @param orderDetails arraylist holding details of the order to be deleted
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return confirmation of the department added
     */
    public ArrayList<String> deleteOrder(ArrayList<String> orderDetails){
        JDBCOnlineTradeDataSource jdbcOnlineTradeDataSource = new JDBCOnlineTradeDataSource();
        jdbcOnlineTradeDataSource.deleteOnlineTrade(Integer.valueOf(orderDetails.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add("Success");
        returnArray.add("User " + orderDetails.get(2) + " has been deleted");
        System.out.println("deleted " + orderDetails.get(2) + " from db");
        return returnArray;

    }
}
