package Server.RequestHanlders;

import Application.Screens.UserScreen.JDBCUserDataSource;
import Application.Screens.UserScreen.User;

import java.util.ArrayList;

public class GetSingleUser {
    /**
     * @param userDetails arraylist holding details of the department to be found
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return arraylist containing the data used to create a User object
     *              [0] - [7]
     *
     */
    public ArrayList<String> getSingleUser(ArrayList<String> userDetails) {

        JDBCUserDataSource jdbcUserDataSource = new JDBCUserDataSource();
        ArrayList<String> returnArray = new ArrayList<>();

        try{
            User returnUser = jdbcUserDataSource.getUser(Integer.valueOf(userDetails.get(2)));


            //Check if called from login, then only send password and Permission
            if(userDetails.get(1).equals("CalledFromLogin")){
                returnArray.add(returnUser.getPassword());
                returnArray.add(returnUser.getPermissionLevel());
                returnArray.add(Integer.toString(returnUser.getDepartmentID()));
                return returnArray;
            }
            if(userDetails.get(1).equals("SaltRequest")){
                returnArray.add(returnUser.getSalt());
                return returnArray;
            }

            returnArray.add(Integer.toString(returnUser.getUserID()));
            returnArray.add(returnUser.getFirstName());
            returnArray.add(returnUser.getSurname());
            returnArray.add(returnUser.getEmail());
            returnArray.add(Integer.toString(returnUser.getDepartmentID()));
            returnArray.add(returnUser.getPermissionLevel());

            returnArray.add(returnUser.getSalt());


            String active;
            if (returnUser.getUserIsActive()) {
                active = "true";
            } else {
                active = "false";
            }
            returnArray.add(active);

        }catch (Exception e){
            if(returnArray != null){
                returnArray.clear();
                returnArray.add("error");
                returnArray.add("Incorrect ID");
            }

        }

        return returnArray;
    }
}
