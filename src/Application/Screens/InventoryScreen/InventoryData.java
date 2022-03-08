package Application.Screens.InventoryScreen;

import Application.Screens.AssetScreen.Asset;
import Server.Client;
import Server.GetPropertyValues;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This version uses a InventoryDataSource and its methods to retrieve data
 */

public class InventoryData {

    DefaultListModel listModel;


    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public InventoryData() {
        listModel = new DefaultListModel();

        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();
        ArrayList<String> inventoryRequest = new ArrayList<>();
        inventoryRequest.add("GetInventories");
        client.sendData(inventoryRequest);

        //Get return from server
        ArrayList<String> serverReturn = new ArrayList<>();
        try {
            serverReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        //Create tree set of department IDs
        Set<Integer> inventoryNames = new TreeSet<Integer>();

        for (String s : serverReturn) {
            inventoryNames.add(Integer.valueOf(s));
        }

        // add the retrieved data to the list model
        for (int inventoryName : inventoryNames) {
            listModel.addElement(inventoryName);
        }

        client.disconnect();

    }

    /**
     * Adds an inventory to the inventory table.
     *
     * @param i An inventory to add to the inventory table.
     */
    public void add(Inventory i) {
        // check to see if the department is already in the table
        // if not add to the department table and the list model
        if (!listModel.contains(i.getInventoryID())) {
            listModel.addElement(i.getInventoryID());

            //Server request
            //Retrieve host and port from config file
            GetPropertyValues val = new GetPropertyValues();
            String serverAddress = val.getHost();
            Integer portNumber = val.getPort();

            Client client = new Client(serverAddress, portNumber);
            if (!client.start())
                client.start();

            ArrayList<String> inventoryAddRequest = new ArrayList<>();
            inventoryAddRequest.add("AddInventory");
            inventoryAddRequest.add("admin");

            //Fill list with users data
            inventoryAddRequest.add(Integer.toString(i.getInventoryID()));
            inventoryAddRequest.add(Integer.toString(i.getAssetID()));
            inventoryAddRequest.add(Integer.toString(i.getDepartmentID()));
            inventoryAddRequest.add(Integer.toString(i.getQuantity()));
            String truthVal;
            if (i.getInventoryActive()) {
                truthVal = "true";
            } else {
                truthVal = "false";
            }
            inventoryAddRequest.add(truthVal);

            client.sendData(inventoryAddRequest);
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
            showMessageDialog(null, "Inventory ID must be unique");
        }
    }

    /**
     * Modifys an asset in the asset table.
     *
     * @param a An asset to modify from the asset table.
     */
    public void modify(Inventory a, Integer index, Integer inventoryID) {
        listModel.setElementAt(inventoryID, index);
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> assetAddRequest = new ArrayList<>();
        assetAddRequest.add("ModifyInventory");
        assetAddRequest.add("admin");
        //Fill list with users data
        assetAddRequest.add(Integer.toString(a.getInventoryID()));
        assetAddRequest.add(Integer.toString(a.getAssetID()));
        assetAddRequest.add(Integer.toString(a.getDepartmentID()));
        assetAddRequest.add(Integer.toString(a.getQuantity()));

        String truthVal;
        //Translate bool to string
        if(a.getInventoryActive()){
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
     * Based on the inventoryID of the inventory in the inventory table, delete the inventory.
     *
     * @param key
     */
    public void remove(Object key) {
        String inventoryID = key.toString();

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

        ArrayList<String> inventoryDeleteRequest = new ArrayList<>();
        inventoryDeleteRequest.add("DeleteInventory");
        inventoryDeleteRequest.add("admin");
        //Fill list with users data
        inventoryDeleteRequest.add(inventoryID);
        client.sendData(inventoryDeleteRequest);

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
     * Retrieves inventory details from the model.
     *
     * @param key the name to retrieve.
     * @return the inventory object related to the inventoryID.
     */
    public Inventory get(Object key) {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> inventoryGetRequest = new ArrayList<>();
        inventoryGetRequest.add("GetSingleInventory");
        inventoryGetRequest.add("admin");
        //Fill list with users data
        inventoryGetRequest.add(Integer.toString( (Integer) key));
        client.sendData(inventoryGetRequest);

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
        Inventory a = new Inventory(Integer.valueOf(newServerReturn.get(0)), Integer.valueOf(newServerReturn.get(1)), Integer.valueOf(newServerReturn.get(2)), Integer.valueOf(newServerReturn.get(3)),  truthValue);


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
        userGetRequest.add("GetInventorySize");
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
