package Server.RequestHanlders;

import Application.Screens.AssetScreen.Asset;
import Application.Screens.AssetScreen.JDBCAssetDataSource;
import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;
import Application.Screens.OnlineTrade.OnlineTrade;

import java.util.ArrayList;

public class GetSingleOrder {
    /**
     *
     * @param orderDetails an arraylist containing the order key
     * @return an arraylist with items [0] - [9] being the values in order used to create an online trade object
     */
    public ArrayList<String> getSingleOrder(ArrayList<String> orderDetails){

        JDBCOnlineTradeDataSource jdbcOnlineTradeDataSource = new JDBCOnlineTradeDataSource();
        OnlineTrade returnTrade = jdbcOnlineTradeDataSource.getOnlineTrade(Integer.valueOf(orderDetails.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();



        returnArray.add(Integer.toString(returnTrade.getOnlineTradeID()));
        returnArray.add(returnTrade.getTradeType());
        returnArray.add(Integer.toString(returnTrade.getUserIDOnlineTrade()));
        returnArray.add(Integer.toString(returnTrade.getAssetIDOnlineTrade()));
        returnArray.add(Integer.toString(returnTrade.getDepartmentIDOnlineTrade()));
        returnArray.add(Integer.toString(returnTrade.getQuantity()));
        returnArray.add(Integer.toString(returnTrade.getUnitPrice()));
        returnArray.add(returnTrade.getOrderDate());
        returnArray.add(Integer.toString(returnTrade.getOrderStatus()));



        return returnArray;
    }
}
