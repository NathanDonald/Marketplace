package Server.RequestHanlders;

import Application.Screens.AssetScreen.JDBCAssetDataSource;
import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GetTrades {
    /**
     * Request handler to retrieve a set of Trade Names
     *
     * @param tradeDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the different Names from [0] to n
     */
    public ArrayList<String> getTrades(ArrayList<String> tradeDetails) {

        JDBCOnlineTradeDataSource jdbcOnlineTradeDataSource = new JDBCOnlineTradeDataSource();

        ArrayList<String> returnArray = new ArrayList<>();
        Set<String> tradeNames = new TreeSet<String>();
        tradeNames = jdbcOnlineTradeDataSource.onlineTradeNameSet();

        for (String s : tradeNames) {
            returnArray.add(s);
        }

        return returnArray;
    }
}
