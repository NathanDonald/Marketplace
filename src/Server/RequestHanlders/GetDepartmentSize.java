package Server.RequestHanlders;
import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;
import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;
public class GetDepartmentSize {
    /**
     * Request handler to find the number of departments in the database
     *
     * @param depDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the size at [0] - if failed will return error
     */
    public ArrayList<String> getDepartmentSize(ArrayList<String> depDetails) {

        JDBCDepartmentDataSource jdbcDepartmentDataSource = new JDBCDepartmentDataSource();
        ArrayList<String> returnArray = new ArrayList<>();
        int count = jdbcDepartmentDataSource.getSize();

        returnArray.add(Integer.toString(count));

        return returnArray;
    }
}
