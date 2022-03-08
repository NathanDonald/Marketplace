package Server.RequestHanlders;

import Application.Screens.InventoryScreen.JDBCInventoryDataSource;
import Application.Screens.InventoryScreen.Inventory;

import java.util.ArrayList;

public class ModifyInventory {
    /**
     * @param inventoryDetails arraylist with modification details
     *                     0- Request type
     *                     1- Permissions
     *                     3- Asset ID
     *                     4- Department ID
     *                     5- Quantity
     *                     6- Boolean Inventory Active
     * @return If the modification worked
     *
     */
    public ArrayList<String> modifyInventory(ArrayList<String> inventoryDetails) {

        JDBCInventoryDataSource jdbcInventoryDataSource = new JDBCInventoryDataSource();
        ArrayList<String> returnArray = new ArrayList<>();

        //Get all data for the specified user
        Inventory returnInventory = jdbcInventoryDataSource.getInventory(Integer.valueOf(inventoryDetails.get(2)));

        //Check if assetActive is true or false
        boolean truthValue;
        if(inventoryDetails.get(6).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }


        Inventory a = new Inventory(returnInventory.getInventoryID(), Integer.valueOf(inventoryDetails.get(3)), Integer.valueOf(inventoryDetails.get(4)), Integer.valueOf(inventoryDetails.get(5)), truthValue);

        jdbcInventoryDataSource.modifyInventory(a);


        returnArray.add("Success");
        returnArray.add("Inventory " + inventoryDetails.get(2) + " has been modified");

        System.out.println("edited " + inventoryDetails.get(2) + " in db");
        return returnArray;

    }
}
