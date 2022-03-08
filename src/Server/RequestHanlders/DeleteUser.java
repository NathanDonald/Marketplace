package Server.RequestHanlders;

import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;

/**
 * Handle request to delete an asset
 * Only users with admin permissions can use
 */
public class DeleteUser {
    /**
     * @param userDetail arraylist holding details of the user to be deleted
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return confirmation of the department deleted
     */
    public ArrayList<String> deleteUser(ArrayList<String> userDetail){

        JDBCUserDataSource jdbcUserDataSource = new JDBCUserDataSource();
        jdbcUserDataSource.deleteUser(Integer.valueOf(userDetail.get(2)));

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add("Success");
        returnArray.add("User " + userDetail.get(2) + " has been deleted");
        System.out.println("deleted " + userDetail.get(2) + " from db");
        return returnArray;

    }
}
