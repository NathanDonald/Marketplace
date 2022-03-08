package Server.RequestHanlders;

import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GetUsers {
    /**
     * Request handler to retrieve a set of UserNames
     *
     * @param userDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the different Names from [0] to n
     */
    public ArrayList<String> getUsers(ArrayList<String> userDetails) {

        JDBCUserDataSource jdbcUserDataSource = new JDBCUserDataSource();
        ArrayList<String> returnArray = new ArrayList<>();
        Set<String> userNames = new TreeSet<String>();
        userNames = jdbcUserDataSource.userNameSet();

        for (String s : userNames) {
            returnArray.add(s);
        }

        return returnArray;
    }
}
