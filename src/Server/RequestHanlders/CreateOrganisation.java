package Server.RequestHanlders;

import Application.Screens.DepartmentScreen.Department;
import Application.Screens.DepartmentScreen.JDBCDepartmentDataSource;

import java.util.ArrayList;

/**
 * Handle request to create an organisation
 * Only users with admin permissions can use
 *
 */
public class CreateOrganisation {
    /**
     * @param organisationDetails arraylist holding details of the department to be added
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     *                            3- Name
     *                            4- Manager
     *                            5- Credits
     *                            6- Active status
     * @return confirmation of the department added
     */
    public ArrayList<String> createOrganisation(ArrayList<String> organisationDetails){
        boolean truthValue;
        if(organisationDetails.get(6).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }

        JDBCDepartmentDataSource jdbcDepartmentDataSource = new JDBCDepartmentDataSource();




        Department a = new Department(Integer.valueOf(organisationDetails.get(2)), organisationDetails.get(3), Integer.parseInt(organisationDetails.get(4)), Integer.parseInt(organisationDetails.get(5)), truthValue);


        jdbcDepartmentDataSource.addDepartment(a);

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add("Success");
        returnArray.add("asset " + organisationDetails.get(2) + " has been added");
        System.out.println("added " + organisationDetails.get(2) + " to db");
        return returnArray;

    }

}
