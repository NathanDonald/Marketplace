package Server.RequestHanlders;

import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GetDepartments {
    /**
     * Request handler to retrieve a set of department IDs
     *
     * @param departmentDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the different IDs from [0] to n
     */
    public ArrayList<String> getDepartments(ArrayList<String> departmentDetails) {

        JDBCDepartmentDataSource jdbcDepartmentDataSource = new JDBCDepartmentDataSource();

        ArrayList<String> returnArray = new ArrayList<>();
        Set<Integer> departmentNames = new TreeSet<Integer>();
        departmentNames = jdbcDepartmentDataSource.departmentIDSet();

        for (Integer s : departmentNames) {
            returnArray.add(Integer.toString(s));
        }

        return returnArray;
    }
}
