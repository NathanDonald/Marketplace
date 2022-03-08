package Server.RequestHanlders;

import Application.Screens.InventoryScreen.Inventory;
import Application.Screens.InventoryScreen.JDBCInventoryDataSource;
import Application.Screens.OnlineTrade.JDBCOnlineTradeDataSource;
import Application.Screens.OnlineTrade.OnlineTrade;

import java.util.ArrayList;

public class AddInventory {
    /**
     *
     * @param inventoryDetails An arraylist including all the data needed to create an OnlineTrade
     *                     0- Request type
     *                     1- Permissions
     *                     2- InventoryID
     *                     3- assetID
     *                     4- departmentID
     *                     5- quantity
     *                     6- inventoryActive
     *
     * @return If the add worked or not
     */
    public ArrayList<String> addInventory(ArrayList<String> inventoryDetails){
        //Check if assetActive is true or false
        boolean truthValue;
        if(inventoryDetails.get(6).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }
        Inventory a = new Inventory(Integer.valueOf(inventoryDetails.get(2)), Integer.valueOf(inventoryDetails.get(3)), Integer.valueOf(inventoryDetails.get(4)),Integer.valueOf(inventoryDetails.get(5)),
               truthValue);
        JDBCInventoryDataSource jdbcInventoryDataSource = new JDBCInventoryDataSource();
        ArrayList<String> returnArray = new ArrayList<>();



        jdbcInventoryDataSource.addInventory(a);



        returnArray.add("Success");
        returnArray.add("Inventory " + inventoryDetails.get(2) + " has been added");
        System.out.println("added " + inventoryDetails.get(2) + " to db");
        return returnArray;

    }
}
