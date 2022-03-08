package Server.RequestHanlders;

import Application.Screens.UserScreen.JDBCUserDataSource;
import Application.Screens.UserScreen.User;

import java.util.ArrayList;

public class ModifyUser {
    /**
     * @param userDetails arraylist with modification details
     *                     0- Request type
     *                     1- Permissions
     *                     2- User ID
     *                     3- First Name
     *                     4- Surname
     *                     5- Email
     *                     6- Department ID
     *                     7- Permission Level
     *                     8- Password
     *                     9- Salt
     *                     10- Boolean is active
     * @return If the modification worked
     *
     */
    public ArrayList<String> modifyUser(ArrayList<String> userDetails) {

        JDBCUserDataSource jdbcUserDataSource = new JDBCUserDataSource();
        ArrayList<String> returnArray = new ArrayList<>();

        //Get all data for the specified user
        User returnUser = jdbcUserDataSource.getUser(Integer.valueOf(userDetails.get(2)));

        //Check if assetActive is true or false
        boolean truthValue;
        if(userDetails.get(10).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }


            User a = new User(returnUser.getUserID(), userDetails.get(3), userDetails.get(4), userDetails.get(5), Integer.valueOf(userDetails.get(6)), userDetails.get(7), userDetails.get(8), userDetails.get(9), truthValue);

            jdbcUserDataSource.modifyUser(a);


        returnArray.add("Success");
        returnArray.add("User " + userDetails.get(2) + " has been modified");

        System.out.println("edited " + userDetails.get(2) + " in db");
        return returnArray;

    }
}
