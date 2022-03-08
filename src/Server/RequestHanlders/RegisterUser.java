package Server.RequestHanlders;

import Application.Screens.UserScreen.JDBCUserDataSource;
import Application.Screens.UserScreen.User;

import java.util.ArrayList;
import java.util.Set;

/**
 * Handle request to register a new user
 */
public class RegisterUser {
    /**
     * @param userDetails an arraylist holding all the details to create a user from [2] - [10]
     *
     * @return an arraylist containing the result of the registration attempt; success or failure
     */
    public ArrayList<String> registerUser(ArrayList<String> userDetails){
        //Check if assetActive is true or false
        boolean truthValue;
        if(userDetails.get(10).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }

        User a = new User(Integer.valueOf(userDetails.get(2)), userDetails.get(3), userDetails.get(4), userDetails.get(5), Integer.valueOf(userDetails.get(6)), userDetails.get(7), userDetails.get(8), userDetails.get(9), truthValue);

        System.out.println(userDetails.get(8));
        System.out.println(userDetails.get(9));

        JDBCUserDataSource jdbcUserDataSource = new JDBCUserDataSource();
        ArrayList<String> returnArray = new ArrayList<>();

        //Check if user already exists, return an error if it does
        Set<String> setEmail = jdbcUserDataSource.userNameSet();

        if(!checkEmail(userDetails.get(3), setEmail)){
            returnArray.add("error");
            returnArray.add("User with email " + userDetails.get(3) + " already exists");
            System.out.println("failed to add " + userDetails.get(3) + " to db");
            return returnArray;
        }

        jdbcUserDataSource.addUser(a);



        returnArray.add("Success");
        returnArray.add("User " + userDetails.get(2) + " has been added");
        System.out.println("added " + userDetails.get(2) + " to db");
        return returnArray;

    }

    //check if the email has already been used in another account
    public boolean checkEmail(String x, Set<String> emailSet){
        if (emailSet.contains(x)){
            return false;
        }
        return true;
    }
}
