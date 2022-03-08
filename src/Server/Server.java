package Server;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Will open server sockets and allow clients to connect to it.
 * Holds the Client handler method that will handle each client on a different thread
 */

class Server {
    public static void main(String[] args)
    {
        ServerSocket server = null;
        GetPropertyValues val = new GetPropertyValues();
        Integer port = val.getPort();

        try {

            //Opens the server on the port stored in the config file
            server = new ServerSocket(port);
            server.setReuseAddress(true);

            // Constant check for new clients trying to connect
            while (true) {

                //Creates socket objects to connect any client attempting to connect
                Socket client = server.accept();


                // create a new thread object
                ClientHandler clientSock
                        = new ClientHandler(client);

                // When a client connects a new thread will be started to handle their request seperate to other client
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //Close the server
            if (server != null) {
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //new comment test
    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }

        public void run()
        {
            PrintWriter out = null;
            BufferedReader in = null;
            boolean keepRunning = true;
            try {


                // get the outputstream of client
                out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

                // get the inputstream of client
                InputStream inputStream = clientSocket.getInputStream();
                // create a DataInputStream so we can read data from it.
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                RequestDirector requestDirector = new RequestDirector();

                do {
                    ArrayList<String> listOfMessages = (ArrayList<String>) objectInputStream.readObject();

                    //take input from client

                    ArrayList<String> requestReturn = new ArrayList<>();

                    //Check if closing port
                    if(listOfMessages.get(0).equals("close")){
                        keepRunning = false;
                    }

                    //return arrayList of data
                    requestReturn = requestDirector.request(listOfMessages);
                    oos.writeObject(requestReturn);

                    //clear arrayLists
                    listOfMessages.clear();
                    if(requestReturn != null){
                        requestReturn.clear();
                    }

                    } while (keepRunning);

            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println("Client Stream Disconnected");
            }
            finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null) {
                        in.close();

                        clientSocket.close();
                    }
                }
                catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}
//Comment