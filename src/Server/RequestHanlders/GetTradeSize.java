package Server.RequestHanlders;

import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;
import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;

public class GetTradeSize {
    /**
     * Request handler to find the number of users in the database
     *
     * @param tradeDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the size at [0] - if failed will return error
     */
    public ArrayList<String> getTradeSize(ArrayList<String> tradeDetails) {

        JDBCOnlineTradeDataSource jdbcOnlineTradeDataSource = new JDBCOnlineTradeDataSource();
        ArrayList<String> returnArray = new ArrayList<>();
        int count = jdbcOnlineTradeDataSource.getSize();

        returnArray.add(Integer.toString(count));

        return returnArray;
    }
}
