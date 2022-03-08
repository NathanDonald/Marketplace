package Application.Screens.DepartmentScreen;

import Server.Client;
import Server.GetPropertyValues;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * This version uses a DepartmentDataSource and its methods to retrieve data
 */

public class DepartmentData {

    DefaultListModel listModel;

    DepartmentDataSource departmentData;

    /**
     * Constructor initializes the list model that holds names as Strings and
     * attempts to read any data saved from previous invocations of the
     * application.
     *
     */
    public DepartmentData() {
        listModel = new DefaultListModel();
        departmentData = new JDBCDepartmentDataSource();

        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();
        ArrayList<String> departmentRequest = new ArrayList<>();
        departmentRequest.add("GetDepartments");
        client.sendData(departmentRequest);

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

        //Create tree set of department IDs
        Set<Integer> departmentNames = new TreeSet<Integer>();

        for (String s : serverReturn) {
            departmentNames.add(Integer.valueOf(s));
        }

        // add the retrieved data to the list model
        for (int departmentName : departmentNames) {
            listModel.addElement(departmentName);
        }

        client.disconnect();

    }

    /**
     * Adds a department to the department table.
     *
     * @param d A department to add to the department table.
     */
    public void add(Department d) {

        // check to see if the department is already in the table
        // if not add to the department table and the list model
        if (!listModel.contains(d.getDepartmentID())) {
            listModel.addElement(d.getDepartmentID());

            //Server request
            //Retrieve host and port from config file
            GetPropertyValues val = new GetPropertyValues();
            String serverAddress = val.getHost();
            Integer portNumber = val.getPort();

            Client client = new Client(serverAddress, portNumber);
            if (!client.start())
                client.start();

            ArrayList<String> departmentAddRequest = new ArrayList<>();
            departmentAddRequest.add("CreateOrganisation");
            departmentAddRequest.add("admin");

            //Fill list with users data
            departmentAddRequest.add(Integer.toString(d.getDepartmentID()));
            departmentAddRequest.add(d.getDepartmentName());
            departmentAddRequest.add(Integer.toString(d.getDepartmentManager()));
            departmentAddRequest.add(Integer.toString(d.getDepartmentCredits()));
            String truthVal;
            if(d.getDepartmentActive()){
                truthVal = "true";
            }else{
                truthVal = "false";
            }
            departmentAddRequest.add(truthVal);

            client.sendData(departmentAddRequest);
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
     * Based on the departmentID of the department in the department table, delete the department.
     *
     * @param key
     */
    public void remove(Object key) {

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

        ArrayList<String> departmentAddRequest = new ArrayList<>();
        departmentAddRequest.add("DeleteOrganisation");
        departmentAddRequest.add("admin");
        //Fill list with users data
        departmentAddRequest.add(Integer.toString((int) key));
        client.sendData(departmentAddRequest);

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
     * Retrieves Department details from the model.
     *
     * @param key the name to retrieve.
     * @return the Department object related to the departmentID.
     */
    public Department get(Object key) {
        //Server request
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();

        Client client = new Client(serverAddress, portNumber);
        if (!client.start())
            client.start();

        ArrayList<String> departmentGetRequest = new ArrayList<>();
        departmentGetRequest.add("GetSingleOrganisation");
        departmentGetRequest.add("admin");
        //Fill list with users data
        departmentGetRequest.add(Integer.toString((int) key));
        client.sendData(departmentGetRequest);

        //Server response
        ArrayList<String> newServerReturn = new ArrayList<>();
        try {
            newServerReturn = client.getList();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }


        //Check if assetActive is true or false
        boolean truthValue;
        if(newServerReturn.get(4).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }
        //create user object
        Department a = new Department(Integer.valueOf(newServerReturn.get(0)), newServerReturn.get(1), Integer.valueOf(newServerReturn.get(2)), Integer.valueOf(newServerReturn.get(3)), truthValue);


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
     * @return the number of departmentIDs in the department table.
     */
    public int getSize() {


        return departmentData.getSize();
    }
}
