package Server.RequestHanlders;

import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;
import Application.Screens.OnlineTrade.OnlineTrade;
import Server.matchTrades;

import java.util.ArrayList;

public class ModifyTrade {
    /**
     * @param tradeDetails arraylist with modification details
     *                     0- Request type
     *                     1- Permissions
     *                     2- Trade ID
     *                     3- Trade Type
     *                     4- User ID
     *                     5- Asset ID
     *                     6- Department ID
     *                     7- Quantity
     *                     8- Unit Price
     *                     9- Order Status
     *                     10- Expiry Date
     * @return If the modification worked
     *
     */
    public ArrayList<String> modifyTrade(ArrayList<String> tradeDetails) {

        JDBCOnlineTradeDataSource jdbcOnlineTradeDataSource = new JDBCOnlineTradeDataSource();
        ArrayList<String> returnArray = new ArrayList<>();


        OnlineTrade a = new OnlineTrade(Integer.valueOf(tradeDetails.get(2)), tradeDetails.get(3), Integer.valueOf(tradeDetails.get(4)),Integer.valueOf(tradeDetails.get(5)),
                Integer.valueOf(tradeDetails.get(6)), Integer.valueOf(tradeDetails.get(7)), Integer.valueOf(tradeDetails.get(8)),Integer.valueOf(tradeDetails.get(9)));


        jdbcOnlineTradeDataSource.modifyOnlineTrade(a);


        returnArray.add("Success");
        returnArray.add("User " + tradeDetails.get(2) + " has been modified");

        System.out.println("edited " + tradeDetails.get(2) + " in db");

        new matchTrades(a.getOnlineTradeID());

        return returnArray;

    }
}
