package Server.RequestHanlders;

import Application.Screens.AssetScreen.JDBCAssetDataSource;
import Application.Screens.InventoryScreen.JDBCInventoryDataSource;

import java.util.ArrayList;

public class DeleteInventory {
    /**
     * @param inventoryDetails arraylist holding details of the Inventory to be deleted
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return confirmation of the department added
     */
    public ArrayList<String> deleteInventory(ArrayList<String> inventoryDetails){

        JDBCInventoryDataSource jdbcInventoryDataSource = new JDBCInventoryDataSource();
        jdbcInventoryDataSource.deleteInventory(Integer.valueOf(inventoryDetails.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add("Success");
        returnArray.add("Inventory " + inventoryDetails.get(2) + " has been deleted");
        System.out.println("deleted " + inventoryDetails.get(2) + " from db");
        return returnArray;

    }
}
