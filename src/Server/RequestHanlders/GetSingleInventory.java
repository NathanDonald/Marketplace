package Server.RequestHanlders;

import Application.Screens.AssetScreen.Asset;
import Application.Screens.AssetScreen.JDBCAssetDataSource;
import Application.Screens.InventoryScreen.Inventory;
import Application.Screens.InventoryScreen.JDBCInventoryDataSource;

import java.util.ArrayList;

public class GetSingleInventory {
    /**
     * @param inventoryDetails an arraylist containing the asset ID
     *                     0- Request type
     *                     1- Permissions
     *                     2- Inventory ID
     *
     * @return an arraylist with items [0] - [4] being the values in order used to create an online trade object
     */
    public ArrayList<String> getSingleInventory(ArrayList<String> inventoryDetails){

        JDBCInventoryDataSource jdbcInventoryDataSource = new JDBCInventoryDataSource();
        Inventory returnInventory = jdbcInventoryDataSource.getInventory(Integer.valueOf(inventoryDetails.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();



        returnArray.add(Integer.toString(returnInventory.getInventoryID()));
        returnArray.add(Integer.toString(returnInventory.getAssetID()));
        returnArray.add(Integer.toString(returnInventory.getDepartmentID()));
        returnArray.add(Integer.toString(returnInventory.getQuantity()));

        String active;
        if (returnInventory.getInventoryActive()){
            active = "true";
        }else{
            active = "false";
        }
        returnArray.add(active);

        return returnArray;
    }
}
