package Application.Screens.UserScreen;

import Server.Client;
import Server.GetPropertyValues;
import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * This version uses a UserDataSource and its methods to retrieve data
 */

public class UserData {

    DefaultListModel listModel;


    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public UserData() {
        listModel = new DefaultListModel();
        new JDBCUserDataSource();

        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();
        ArrayList<String> userRequest = new ArrayList<>();
        userRequest.add("GetUsers");
        client.sendData(userRequest);

        //Get return from server
        ArrayList<String> serverReturn = new ArrayList<>();
        try {
            serverReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        for (String item : serverReturn) {
            System.out.println(item);
        }
        Set<String> userNames = new TreeSet<String>();

        for (String s : serverReturn) {
            userNames.add(s);
        }

        // add the retrieved data to the list model
        for (String userName : userNames) {
            listModel.addElement(userName);
        }

        client.disconnect();
    }

    /**
     * Adds an user to the user table.
     *
     * @param a An user to add to the user table.
     */
    public void add(User a) throws NoSuchAlgorithmException {

        // check to see if the user is already in the table
        // if not add to the user table and the list model
        if (!listModel.contains(a.getUserID())) {
            String userName = a.getUserID() + " " + a.getFirstName() + " " + a.getSurname();
            listModel.addElement(userName);

            //Server request
            //Retrieve host and port from config file
            GetPropertyValues val = new GetPropertyValues();
            String serverAddress = val.getHost();
            Integer portNumber = val.getPort();

            Client client = new Client(serverAddress, portNumber);
            if (!client.start())
                client.start();

            ArrayList<String> userAddRequest = new ArrayList<>();
            userAddRequest.add("RegisterUser");
            userAddRequest.add("admin");

            //Fill list with users data
            userAddRequest.add(Integer.toString(a.getUserID()));
            userAddRequest.add(a.getFirstName());
            userAddRequest.add(a.getSurname());
            userAddRequest.add(a.getEmail());
            userAddRequest.add(Integer.toString(a.getDepartmentID()));
            userAddRequest.add(a.getPermissionLevel());
            userAddRequest.add(a.getPassword());
            userAddRequest.add(a.getSalt());
            String truthVal;
            if(a.getUserIsActive()){
                 truthVal = "true";
            }else{
                 truthVal = "false";
            }
            userAddRequest.add(truthVal);

            client.sendData(userAddRequest);
            //Server response
            ArrayList<String> newServerReturn = new ArrayList<>();
            try {
                newServerReturn = client.getList();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

            for (String item : newServerReturn) {
                System.out.println(item);
            }

            //Clear this instance of client
            client.disconnect();

        }
    }

    /**
     * Modifies a user in the user table.
     *
     * @param a A user to modify from the user table.
     */
    public void modify(User a, Integer index, String userID) {
        listModel.setElementAt(userID, index);

        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userAddRequest = new ArrayList<>();
        userAddRequest.add("ModifyUser");
        userAddRequest.add("admin");
        //Fill list with users data
        userAddRequest.add(Integer.toString(a.getUserID()));
        userAddRequest.add(a.getFirstName());
        userAddRequest.add(a.getSurname());
        userAddRequest.add(a.getEmail());
        userAddRequest.add(Integer.toString(a.getDepartmentID()));
        userAddRequest.add(a.getPermissionLevel());
        userAddRequest.add(a.getPassword());
        userAddRequest.add(a.getSalt());

        String truthVal;
        //Translate bool to string
        if(a.getUserIsActive()){
            truthVal = "true";
        }else{
            truthVal = "false";
        }
        userAddRequest.add(truthVal);

        client.sendData(userAddRequest);
        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        for (String item : newServerReturn) {
            System.out.println(item);
        }
        //Clear this instance of client
        client.disconnect();

    }

    /**
     * Based on the userID of the user in the user table, delete the user.
     *
     * @param key The userID + first name + surname of a user.
     */
    public void remove(Object key) {
        String userName = key.toString();
        Integer userID = Integer.parseInt(userName.replaceAll("\\D+","")) ;

        // remove from both list and map
        listModel.removeElement(key);

        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userAddRequest = new ArrayList<>();
        userAddRequest.add("DeleteUser");
        userAddRequest.add("admin");
        //Fill list with users data
        userAddRequest.add(Integer.toString(userID));
        client.sendData(userAddRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //Clear this instance of client
        client.disconnect();

    }



    /**
     * Retrieves User details from the model.
     *
     * @param key the ID to retrieve.
     * @return the User object related to the userID.
     */
    public User get(Integer key) {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userGetRequest = new ArrayList<>();
        userGetRequest.add("GetSingleUser");
        userGetRequest.add("admin");
        //Fill list with users data
        userGetRequest.add(Integer.toString(key));
        client.sendData(userGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        //Check if assetActive is true or false
        boolean truthValue;
        if(newServerReturn.get(7).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }
        //create user object
        User a = new User(Integer.valueOf(newServerReturn.get(0)), newServerReturn.get(1), newServerReturn.get(2), newServerReturn.get(3), Integer.valueOf(newServerReturn.get(4)), newServerReturn.get(5), "notSecure", newServerReturn.get(6), truthValue);


        //Clear this instance of client
        client.disconnect();

        return a;
    }

    /**
     * Accessor for the list model.
     *
     * @return the listModel to display.
     */
    public ListModel getModel() {
        return listModel;
    }

    /**
     * @return the number of userIDs in the user table.
     */
    public int getSize() {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> userGetRequest = new ArrayList<>();
        userGetRequest.add("GetUserSize");
        userGetRequest.add("admin");

        client.sendData(userGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

        //Clear this instance of client
        client.disconnect();

        return Integer.valueOf(newServerReturn.get(0));
    }
}
