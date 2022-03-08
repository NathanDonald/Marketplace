package Server.RequestHanlders;

import Server.Encryption;

import java.util.ArrayList;

/**
 * Handle Login request on server side
 */
public class Login {
    /**
     * Request handler to Login Users
     *
     * @param loginDetails An arraylist containing the login details
     *                     0- Request type
     *                     1- Login request type
     *                     2- User ID
     *                     3- Hashed password
     *
     * @return An arraylist containing the result of the login; success of failure
     */
    public ArrayList<String> login(ArrayList<String> loginDetails){
        //Create array to be returned
        ArrayList<String> toReturn = new ArrayList<>();
        //Clear the array to be safe
        toReturn.clear();

        //Check if details are correct
        ArrayList<String> userDetails = new ArrayList<>();
        ArrayList<String> detailsToSend = new ArrayList<>();
        detailsToSend.add("GetUser");
        detailsToSend.add("CalledFromLogin");
        detailsToSend.add(loginDetails.get(2));
        GetSingleUser getSingleUser = new GetSingleUser();
        userDetails = getSingleUser.getSingleUser(detailsToSend);


        System.out.println(userDetails.get(0));
        System.out.println(loginDetails.get(3));
        if(userDetails.get(0).equals(loginDetails.get(3))){
            toReturn.add("Success");

            //create encrypted permissions and user token
            try{
                Encryption encryption = new Encryption();
                final String secretKey = "ssshhhhhhhhhhh!!!!";
                String userToken = encryption.encrypt(userDetails.get(1), secretKey);
                String permToken = encryption.encrypt(userDetails.get(1), secretKey);
                toReturn.add(userToken);
                toReturn.add(permToken);

            }catch (Exception e){
                System.out.println("Failed to encrypt with exception: " + e);
                toReturn.add("Failed to encrypt");
            }


        } else{
            toReturn.add("Login Details Incorrect");
        }





        return toReturn;
    }
}
