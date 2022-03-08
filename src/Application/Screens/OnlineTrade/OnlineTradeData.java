package Application.Screens.OnlineTrade;

import Application.Screens.AssetScreen.AssetData;
import Server.Client;
import Server.GetPropertyValues;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This version uses a AssetDataSource and its methods to retrieve data
 */

public class OnlineTradeData {

    DefaultListModel listModel;
    ArrayList<Integer> listModelId;

    OnlineTradeDataSource onlineTradeData;

    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public OnlineTradeData() {
        listModel = new DefaultListModel();
        listModelId = new ArrayList<>();

        onlineTradeData = new JDBCOnlineTradeDataSource();

        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();
        ArrayList<String> tradeRequest = new ArrayList<>();
        tradeRequest.add("GetTrades");
        client.sendData(tradeRequest);

        //Get return from server
        ArrayList<String> serverReturn = new ArrayList<>();
        try {
            serverReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //Create tree set of department IDs
        Set<String> tradeNames = new TreeSet<String>();

        for (String s : serverReturn) {
            tradeNames.add(s);
        }

        // add the retrieved data to the list model
        for (String tradeName : tradeNames) {
            listModel.addElement(tradeName);
        }

        // add the retrieved data to the list model - Only add the IDs
        for (String tradeName : tradeNames) {
            Integer onlineTradeID = Integer.parseInt(tradeName.replaceAll(" .*", ""));
            listModelId.add(onlineTradeID);
        }

        client.disconnect();

    }

    /**
     * Adds an onlineTrade to the onlineTrade table.
     *
     * @param a An onlineTrade to add to the onlineTrade table.
     */
    public void add(OnlineTrade a) {

        // check to see if the onlineTrade is already in the table
        // if not add to the onlineTrade table and the list model
        if (!listModelId.contains(a.getOnlineTradeID())) {
            AssetData assetData = new AssetData();


            if (!(assetData.getModelId().contains(a.getAssetIDOnlineTrade()))) {
                String onlineTradeName = a.getOnlineTradeID() + " " + a.getTradeType() + " " + a.getAssetIDOnlineTrade() + " " + a.getQuantity() + " " + a.getUnitPrice();
                listModel.addElement(onlineTradeName);
                //Server request
                //Retrieve host and port from config file
                GetPropertyValues val = new GetPropertyValues();
                String serverAddress = val.getHost();
                Integer portNumber = val.getPort();

                Client client = new Client(serverAddress, portNumber);
                if (!client.start())
                    client.start();

                ArrayList<String> tradeAddRequest = new ArrayList<>();
                tradeAddRequest.add("AddOrder");
                tradeAddRequest.add("admin");



                //Fill list with users data
                tradeAddRequest.add(Integer.toString(a.getOnlineTradeID()));
                tradeAddRequest.add(a.getTradeType());
                tradeAddRequest.add(Integer.toString(a.getUserIDOnlineTrade()));
                tradeAddRequest.add(Integer.toString(a.getAssetIDOnlineTrade()));
                tradeAddRequest.add(Integer.toString(a.getDepartmentIDOnlineTrade()));
                tradeAddRequest.add(Integer.toString(a.getQuantity()));
                tradeAddRequest.add(Integer.toString(a.getUnitPrice()));
                tradeAddRequest.add(Integer.toString(a.getOrderStatus()));



                client.sendData(tradeAddRequest);
                //Server response
                ArrayList<String> newServerReturn = new ArrayList<>();
                try {
                    newServerReturn = client.getList();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                for (String item : newServerReturn) {
                    System.out.println(item);
                }

                //Clear this instance of client
                client.disconnect();
            }else{
                showMessageDialog(null, "Asset ID invalid");
            }


        }else{
            showMessageDialog(null, "Trade ID must be unique");
        }
    }

    /**
     * Modifys an onlineTrade in the onlineTrade table.
     *
     * @param a An onlineTrade to modify from the onlineTrade table.
     */
    public void modify(OnlineTrade a, Integer index, String onlineTradeName) {
        listModel.setElementAt(onlineTradeName, index);



        onlineTradeData.modifyOnlineTrade(a);

        listModel.setElementAt(onlineTradeName, index);

        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        //Using the details retrieved from the config file, connects to the server and sends the "ModifyTrade" request
        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userAddRequest = new ArrayList<>();
        userAddRequest.add("ModifyTrade");
        userAddRequest.add("admin");

        //Fill list with users data
        userAddRequest.add(Integer.toString(a.getOnlineTradeID()));
        userAddRequest.add(a.getTradeType());
        userAddRequest.add(Integer.toString(a.getUserIDOnlineTrade()));
        userAddRequest.add(Integer.toString(a.getAssetIDOnlineTrade()));
        userAddRequest.add(Integer.toString(a.getDepartmentIDOnlineTrade()));
        userAddRequest.add(Integer.toString(a.getQuantity()));
        userAddRequest.add(Integer.toString(a.getUnitPrice()));
        userAddRequest.add(Integer.toString(a.getOrderStatus()));

        client.sendData(userAddRequest);
        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        for (String item : newServerReturn) {
            System.out.println(item);
        }
        //Clear this instance of client
        client.disconnect();
    }

    /**
     * Based on the onlineTradeID of the onlineTrade in the onlineTrade table, delete the onlineTrade.
     *
     * @param key
     */
    public void remove(Object key) {
        String onlineTradeName = key.toString();
        Integer onlineTradeID = Integer.parseInt(onlineTradeName.replaceAll(" .*", ""));

        // remove from both list and map
        listModel.removeElement(key);

        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> tradeDeleteRequest = new ArrayList<>();
        tradeDeleteRequest.add("DeleteOrder");
        tradeDeleteRequest.add("admin");
        //Fill list with users data
        tradeDeleteRequest.add(Integer.toString(onlineTradeID));
        client.sendData(tradeDeleteRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //Clear this instance of client
        client.disconnect();

    }


    /**
     * Retrieves OnlineTrade details from the model.
     *
     * @param key the name to retrieve.
     * @return the OnlineTrade object related to the onlineTradeID.
     */
    public OnlineTrade get(Object key) {

        return get((Integer) key);
    }

    /**
     * Retrieves OnlineTrade details from the model.
     *
     * @param key the ID to retrieve.
     * @return the OnlineTrade object related to the onlineTradeID.
     */
    public OnlineTrade get(Integer key) {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> orderGetRequest = new ArrayList<>();
        orderGetRequest.add("GetSingleOrder");
        orderGetRequest.add("admin");
        //Fill list with users data
        orderGetRequest.add(Integer.toString(key));
        client.sendData(orderGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //create user object
        OnlineTrade a = new OnlineTrade(Integer.valueOf(newServerReturn.get(0)), newServerReturn.get(1),Integer.valueOf(newServerReturn.get(2)),Integer.valueOf(newServerReturn.get(3)),Integer.valueOf(newServerReturn.get(4)),Integer.valueOf(newServerReturn.get(5)),
                Integer.valueOf(newServerReturn.get(6)), newServerReturn.get(7), Integer.valueOf(newServerReturn.get(8)));


        //Clear this instance of client
        client.disconnect();

        return a;
    }

    /**
     * Accessor for the list model.
     *
     * @return the listModel to display.
     */
    public ListModel getModel() {
        return listModel;
    }

    public ArrayList<Integer> getModelId() {
        return listModelId;
    }

    /**
     * @return the number of onlineTradeIDs in the onlineTrade table.
     */
    public int getSize() {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userGetRequest = new ArrayList<>();
        userGetRequest.add("GetTradeSize");
        userGetRequest.add("admin");

        client.sendData(userGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //Clear this instance of client
        client.disconnect();

        return Integer.valueOf(newServerReturn.get(0));

    }

}
