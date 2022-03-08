package Application.Screens.AssetScreen;

import Application.Screens.UserScreen.JDBCUserDataSource;
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

public class AssetData {

    DefaultListModel listModel;
    ArrayList<Integer> listModelId;


    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public AssetData() {
        listModel = new DefaultListModel();
        listModelId = new ArrayList<>();
        new JDBCUserDataSource();


        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();
        ArrayList<String> assetRequest = new ArrayList<>();
        assetRequest.add("GetAssets");
        client.sendData(assetRequest);

        //Get return from server
        ArrayList<String> serverReturn = new ArrayList<>();
        try {
            serverReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //Create tree set of department IDs
        Set<String> assetNames = new TreeSet<String>();

        for (String s : serverReturn) {
            assetNames.add(s);
        }

        // add the retrieved data to the list model
        for (String assetName : assetNames) {
            listModel.addElement(assetName);
        }




        client.disconnect();

    }

    /**
     * Adds an asset to the asset table.
     *
     * @param a An asset to add to the asset table.
     */
    public void add(Asset a) {

        // check to see if the department is already in the table
        // if not add to the department table and the list model
        if (!listModel.contains(a.getAssetName())) {
            listModel.addElement(a.getAssetName());

            //Server request
            //Retrieve host and port from config file
            GetPropertyValues val = new GetPropertyValues();
            String serverAddress = val.getHost();
            Integer portNumber = val.getPort();

            Client client = new Client(serverAddress, portNumber);
            if (!client.start())
                client.start();

            ArrayList<String> assetAddRequest = new ArrayList<>();
            assetAddRequest.add("CreateAsset");
            assetAddRequest.add("admin");

            //Fill list with users data
            assetAddRequest.add(Integer.toString(a.getAssetID()));
            assetAddRequest.add(a.getAssetName());
            assetAddRequest.add(a.getAssetDescription());
            assetAddRequest.add(a.getAssetCategory());
            String truthVal;
            if (a.getAssetActive()) {
                truthVal = "true";
            } else {
                truthVal = "false";
            }
            assetAddRequest.add(truthVal);

            client.sendData(assetAddRequest);
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
            showMessageDialog(null, "User ID must be unique");
        }
    }

    /**
     * Modifys an asset in the asset table.
     *
     * @param a An asset to modify from the asset table.
     */
    public void modify(Asset a, Integer index, String assetName) {
        listModel.setElementAt(assetName, index);
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> assetAddRequest = new ArrayList<>();
        assetAddRequest.add("ModifyAsset");
        assetAddRequest.add("admin");
        //Fill list with users data
        assetAddRequest.add(Integer.toString(a.getAssetID()));
        assetAddRequest.add(a.getAssetName());
        assetAddRequest.add(a.getAssetDescription());
        assetAddRequest.add(a.getAssetCategory());

        String truthVal;
        //Translate bool to string
        if(a.getAssetActive()){
            truthVal = "true";
        }else{
            truthVal = "false";
        }
        assetAddRequest.add(truthVal);

        client.sendData(assetAddRequest);
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
     * Based on the assetID of the asset in the asset table, delete the asset.
     *
     * @param key
     */
    public void remove(Object key) {

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

        ArrayList<String> assetDeleteRequest = new ArrayList<>();
        assetDeleteRequest.add("DeleteAsset");
        assetDeleteRequest.add("admin");
        //Fill list with users data
        assetDeleteRequest.add((String) key);
        client.sendData(assetDeleteRequest);

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
     * Retrieves Asset details from the model.
     *
     * @param key the name to retrieve.
     * @return the Asset object related to the assetID.
     */
    public Asset get(Object key) {
        String inventoryID = key.toString();
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userGetRequest = new ArrayList<>();
        userGetRequest.add("GetSingleAsset");
        userGetRequest.add("admin");
        //Fill list with users data
        userGetRequest.add(inventoryID);
        client.sendData(userGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        //Check if assetActive is true or false
        boolean truthValue;
        if(newServerReturn.get(4).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }
        //create user object
        Asset a = new Asset(Integer.valueOf(newServerReturn.get(0)), newServerReturn.get(1), newServerReturn.get(2), newServerReturn.get(3),  truthValue);


        //Clear this instance of client
        client.disconnect();

        return a;
    }

    /**
     * Retrieves Asset details from the model.
     *
     * @param key the ID to retrieve.
     * @return the Asset object related to the assetID.
     */
    public Asset get(Integer key) {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userGetRequest = new ArrayList<>();
        userGetRequest.add("GetSingleAssetID");
        userGetRequest.add("admin");
        //Fill list with users data
        userGetRequest.add(Integer.toString(key));
        client.sendData(userGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        //Check if assetActive is true or false
        boolean truthValue;
        if(newServerReturn.get(4).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }
        //create user object
        Asset a = new Asset(Integer.valueOf(newServerReturn.get(0)), newServerReturn.get(1), newServerReturn.get(2), newServerReturn.get(3),  truthValue);


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
     * @return the number of assetIDs in the asset table.
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
        userGetRequest.add("GetAssetSize");
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

