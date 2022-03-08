package Server;
import java.io.*;
import java.net.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import Application.*;
import javax.swing.*;
import java.io.IOException;
/**
 * This will run on the clients computer to allow them to connect to the server
 *
 */
// Client class
public class Client {

    private ObjectInputStream ois;        // to read from the socket
    private ObjectOutputStream ous;        // to write on the socket
    private Socket socket;

    private String host;
    private int port;
    private String token = "NoToken";
    private ArrayList <String> returnData;


    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    //Initiate connection to the server
    public boolean start() {

        // try to connect
        try {
            socket = new Socket(host, port);
        } catch (Exception ec) {
            System.out.println("Error connecting to server:" + ec);
            return false;
        }


        //Create an input and output stream
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            ous = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException eIO) {
            System.out.println("Exception creating new Input/output Streams: " + eIO);
            return false;
        }

        // creates the Thread to listen from the server
        new ListenFromServer().start();

        return true;
    }

    //Method to be called from other classes to send an arraylist request to the server
    public void sendData(ArrayList<String> sendArray) {
        try {
            ous.writeObject(sendArray);
        } catch (IOException e) {
            System.out.println("Exception writing to server: " + e);
        }
    }

    //Method to disconnect the client from the server
    public void disconnect() {
        try {
            if (ois != null) ois.close();
        } catch (Exception e) {
        }
        try {
            if (ous != null) ous.close();
        } catch (Exception e) {
        }
        try {
            if (socket != null) socket.close();
        } catch (Exception e) {
        }

    }



    //Get the ArrayList returned from the server
    //To be called by methods in other classes
    public ArrayList<String> getList() throws InterruptedException {
        //Waits until return has been made
        int y = 0;
        //Time out from the server if request does not get a return from the server in time
        while(y < 20){
            Thread.sleep(100);
            if(returnData != null ){
                y = 21;
            }
            y++;
            if (y == 19){
                returnData.add("timed out");
            }
        }
        return returnData;
    }

    //Method to clear the data being stored in the list of returned data
    public void clearData(){
        if(returnData != null ){
            returnData.clear();
        }
    }

    //Main class that will create the Application Gui which will then connect to the server
    public static void main(String[] args) throws InterruptedException, UnsupportedLookAndFeelException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchAlgorithmException {



        Application.create();


    }

    //Sets the returnData list
    void setList(ArrayList<String> op){
        returnData = op;
    }

    //Thread that will be listening for returns from the server while connected
    class ListenFromServer extends Thread {


        public void run() {
            while(true) {
                try {

                    ArrayList<String> returnedList = (ArrayList<String>) ois.readObject();

                    setList(returnedList);

                }
                catch(IOException e) {

                    break;
                }

                catch(ClassNotFoundException e2) {
                }

            }
        }
    }

}
