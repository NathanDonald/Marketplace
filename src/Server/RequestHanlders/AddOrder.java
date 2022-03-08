package Server.RequestHanlders;


import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;
import Application.Screens.OnlineTrade.OnlineTrade;
import Server.matchTrades;

import java.util.ArrayList;

/**
 * Handle request to add an order to the market
 *
 */
public class AddOrder {
    /**
     *
     * @param orderDetails An arraylist including all the data needed to create an OnlineTrade
     *                     0- Request type
     *                     2- Permissions
     *                     3- TradeID
     *                     4- Trade type
     *                     5- UserID
     *                     6- AssetID
     *                     7- Department ID
     *                     8- Quantity
     *                     9- Unit Price
     *                     10- Order Status
     *                     11- Expiry Date and Time
     * @return If the add worked or not
     */
    public ArrayList<String> addOrder(ArrayList<String> orderDetails){

        OnlineTrade a = new OnlineTrade(Integer.valueOf(orderDetails.get(2)), orderDetails.get(3), Integer.valueOf(orderDetails.get(4)),Integer.valueOf(orderDetails.get(5)),
                Integer.valueOf(orderDetails.get(6)), Integer.valueOf(orderDetails.get(7)), Integer.valueOf(orderDetails.get(8)),Integer.valueOf(orderDetails.get(9)));
        JDBCOnlineTradeDataSource jdbcOnlineTradeDataSource = new JDBCOnlineTradeDataSource();
        ArrayList<String> returnArray = new ArrayList<>();



        jdbcOnlineTradeDataSource.addOnlineTrade(a);



        returnArray.add("Success");
        returnArray.add("Trade " + orderDetails.get(2) + " has been added");
        System.out.println("added " + orderDetails.get(2) + " to db");

        new matchTrades(a.getOnlineTradeID());

        return returnArray;

    }
}
