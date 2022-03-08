package Server.RequestHanlders;

import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;
import Application.Screens.InventoryScreen.JDBCInventoryDataSource;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GetInventories {
    /**
     * Request handler to retrieve a set of Inventory IDs
     *
     * @param inventoryDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the different IDs from [0] to n
     */
    public ArrayList<String> getInventories(ArrayList<String> inventoryDetails) {

        JDBCInventoryDataSource jdbcInventoryDataSource = new JDBCInventoryDataSource();

        ArrayList<String> returnArray = new ArrayList<>();
        Set<Integer> inventoryNames = new TreeSet<Integer>();
        inventoryNames = jdbcInventoryDataSource.inventoryIDSet();

        for (Integer s : inventoryNames) {
            returnArray.add(Integer.toString(s));
        }

        return returnArray;
    }
}
