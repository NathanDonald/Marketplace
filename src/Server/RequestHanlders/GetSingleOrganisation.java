package Server.RequestHanlders;

import Application.Screens.DepartmentScreen.Department;
import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;

import java.util.ArrayList;

public class GetSingleOrganisation {
    /**
     * @param departmentDetails arraylist holding details of the department to be found
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return arraylist containing the data used to create a department object
     *                            0- ID
     *                            1- Name
     *                            2- Manager ID
     *                            3- Credits
     *                            4- Active bool
     *
     */
    public ArrayList<String> getSingleOrganisation(ArrayList<String> departmentDetails) {

        JDBCDepartmentDataSource jdbcDepartmentDataSource = new JDBCDepartmentDataSource();
        Department returnDepartment = jdbcDepartmentDataSource.getDepartment(Integer.valueOf(departmentDetails.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();


        returnArray.add(Integer.toString(returnDepartment.getDepartmentID()));
        returnArray.add(returnDepartment.getDepartmentName());
        returnArray.add(Integer.toString(returnDepartment.getDepartmentManager()));
        returnArray.add(Integer.toString(returnDepartment.getDepartmentCredits()));

        String active;
        if (returnDepartment.getDepartmentActive()) {
            active = "true";
        } else {
            active = "false";
        }
        returnArray.add(active);

        return returnArray;
    }
}
