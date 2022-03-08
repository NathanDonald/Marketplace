package Server.RequestHanlders;

import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;
import Application.Screens.InventoryScreen.JDBCInventoryDataSource;

import java.util.ArrayList;

public class GetInventorySize {
    /**
     * Request handler to find the number of departments in the database
     *
     * @param inventoryDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the size at [0] - if failed will return error
     */
    public ArrayList<String> getInventorySize(ArrayList<String> inventoryDetails) {

        JDBCInventoryDataSource jdbcInventoryDataSource = new JDBCInventoryDataSource();
        ArrayList<String> returnArray = new ArrayList<>();
        int count = jdbcInventoryDataSource.getSize();

        returnArray.add(Integer.toString(count));

        return returnArray;
    }
}
