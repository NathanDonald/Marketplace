package Server.RequestHanlders;

import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;


public class GetUserSize {
    /**
     * Request handler to find the number of users in the database
     *
     * @param userDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the size at [0] - if failed will return error
     */
    public ArrayList<String> getUserSize(ArrayList<String> userDetails) {

        JDBCUserDataSource jdbcUserDataSource = new JDBCUserDataSource();
        ArrayList<String> returnArray = new ArrayList<>();
        int count = jdbcUserDataSource.getSize();

        returnArray.add(Integer.toString(count));

    return returnArray;
    }

}
