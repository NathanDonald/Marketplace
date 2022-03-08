package Server.RequestHanlders;

import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;

import java.util.ArrayList;

/**
 * Handle request to delete an organisation
 * Only users with admin permissions can use
 */
public class DeleteOrganisation {
    /**
     * @param organisationDetails arraylist holding details of the department to be deleted
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return confirmation of the department deleted
     */
    public ArrayList<String> deleteOrganisation(ArrayList<String> organisationDetails){
        JDBCDepartmentDataSource jdbcDepartmentDataSource = new JDBCDepartmentDataSource();
        jdbcDepartmentDataSource.deleteDepartment(Integer.valueOf(organisationDetails.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add("Success");
        returnArray.add("Department " + organisationDetails.get(2) + " has been deleted");
        System.out.println("deleted " + organisationDetails.get(2) + " from db");
        return returnArray;

    }
}
