package Server;

import Server.RequestHanlders.*;

import java.util.ArrayList;

/**
 * This will run on the server to organise and redirect requests to the appropriate request handler
 */
public class RequestDirector {
    /**
     * Takes the request and sends the data to the matching request
     *
     * @return data given back from the appropriate request handler
     */
    public ArrayList<String> request(ArrayList<String> requestArray){
        //Create and clear an array that will be filled with data to be returned
        ArrayList<String> returnArray = new ArrayList<>();
        returnArray.clear();

        //Check to see what request is being made and direct it to the appropriate request handler
        try{
            if (requestArray.get(0).equals("Login")){
                Login login = new Login();
                returnArray = login.login(requestArray);

            } else if (requestArray.get(0).equals("AddOrder")) {
                AddOrder addOrder = new AddOrder();
                returnArray = addOrder.addOrder(requestArray);

            } else if (requestArray.get(0).equals("AddInventory")) {
                AddInventory addInventory = new AddInventory();
                returnArray = addInventory.addInventory(requestArray);

            } else if (requestArray.get(0).equals("ModifyUser")) {
                ModifyUser modifyUser = new ModifyUser();
                returnArray = modifyUser.modifyUser(requestArray);

            } else if (requestArray.get(0).equals("CreateAsset")) {
                CreateAsset createAsset = new CreateAsset();
                returnArray = createAsset.createAsset(requestArray);

            } else if (requestArray.get(0).equals("CreateOrganisation")) {
                CreateOrganisation createOrganisation = new CreateOrganisation();
                returnArray = createOrganisation.createOrganisation(requestArray);

            } else if (requestArray.get(0).equals("DeleteAsset")) {
                DeleteAsset deleteAsset = new DeleteAsset();
                returnArray = deleteAsset.deleteAsset(requestArray);

            } else if (requestArray.get(0).equals("DeleteOrder")) {
                DeleteOrder deleteOrder = new DeleteOrder();
                returnArray = deleteOrder.deleteOrder(requestArray);

            } else if (requestArray.get(0).equals("DeleteInventory")) {
                DeleteInventory deleteInventory = new DeleteInventory();
                returnArray = deleteInventory.deleteInventory(requestArray);

            } else if (requestArray.get(0).equals("DeleteOrganisation")) {
                DeleteOrganisation deleteOrganisation = new DeleteOrganisation();
                returnArray = deleteOrganisation.deleteOrganisation(requestArray);

            } else if (requestArray.get(0).equals("DeleteUser")) {
                DeleteUser deleteUser = new DeleteUser();
                returnArray = deleteUser.deleteUser(requestArray);

            } else if (requestArray.get(0).equals("RegisterUser")) {
                RegisterUser registerUser = new RegisterUser();
                returnArray = registerUser.registerUser(requestArray);

            } else if (requestArray.get(0).equals("TransferAssets")) {


            } else if (requestArray.get(0).equals("TransferCredits")) {

            } else if (requestArray.get(0).equals("GetSingleAsset")) {
                GetSingleAsset getSingleAsset = new GetSingleAsset();
                returnArray = getSingleAsset.getSingleAsset(requestArray);
            }else if (requestArray.get(0).equals("GetSingleOrganisation")) {
                GetSingleOrganisation getSingleOrganisation = new GetSingleOrganisation();
                returnArray = getSingleOrganisation.getSingleOrganisation(requestArray);
            }else if (requestArray.get(0).equals("GetSingleUser")) {
                GetSingleUser getSingleUser = new GetSingleUser();
                returnArray = getSingleUser.getSingleUser(requestArray);
            }else if (requestArray.get(0).equals("GetSingleInventory")) {
                GetSingleInventory getSingleInventory = new GetSingleInventory();
                returnArray = getSingleInventory.getSingleInventory(requestArray);
            }else if (requestArray.get(0).equals("GetUsers")) {
                GetUsers getUsers = new GetUsers();
                returnArray = getUsers.getUsers(requestArray);
            }else if (requestArray.get(0).equals("GetInventories")) {
                GetInventories getInventories = new GetInventories();
                returnArray = getInventories.getInventories(requestArray);
            }else if (requestArray.get(0).equals("GetDepartments")) {
                GetDepartments getDepartments = new GetDepartments();
                returnArray = getDepartments.getDepartments(requestArray);
            }else if (requestArray.get(0).equals("GetUserSize")) {
                GetUserSize getUserSize = new GetUserSize();
                returnArray = getUserSize.getUserSize(requestArray);
            }else if (requestArray.get(0).equals("GetInventorySize")) {
                GetInventorySize getInventorySize = new GetInventorySize();
                returnArray = getInventorySize.getInventorySize(requestArray);
            }else if (requestArray.get(0).equals("GetDepartmentSize")) {
                GetDepartmentSize getDepartmentSize = new GetDepartmentSize();
                returnArray = getDepartmentSize.getDepartmentSize(requestArray);
            }else if (requestArray.get(0).equals("GetAssets")) {
                GetAssets getAssets = new GetAssets();
                returnArray = getAssets.getAssets(requestArray);
            }else if (requestArray.get(0).equals("ModifyAsset")) {
                ModifyAsset modifyAsset = new ModifyAsset();
                returnArray = modifyAsset.modifyAsset(requestArray);
            }else if (requestArray.get(0).equals("GetSingleAssetID")) {
                GetSingleAssetID getSingleAssetID = new GetSingleAssetID();
                returnArray = getSingleAssetID.getSingleAssetID(requestArray);
            }else if (requestArray.get(0).equals("GetAssetSize")) {
                GetAssetSize getAssetSize = new GetAssetSize();
                returnArray = getAssetSize.getAssetSize(requestArray);
            }else if (requestArray.get(0).equals("GetTrades")) {
                GetTrades getTrades = new GetTrades();
                returnArray = getTrades.getTrades(requestArray);
            }else if (requestArray.get(0).equals("ModifyTrade")) {
                ModifyTrade modifyTrade = new ModifyTrade();
                returnArray = modifyTrade.modifyTrade(requestArray);
            }else if (requestArray.get(0).equals("ModifyInventory")) {
                ModifyInventory modifyInventory = new ModifyInventory();
                returnArray = modifyInventory.modifyInventory(requestArray);
            }else if (requestArray.get(0).equals("GetSingleOrder")) {
                GetSingleOrder getSingleOrder = new GetSingleOrder();
                returnArray = getSingleOrder.getSingleOrder(requestArray);
            }else if (requestArray.get(0).equals("GetSingleOrder")) {
                GetSingleOrder getSingleOrder = new GetSingleOrder();
                returnArray = getSingleOrder.getSingleOrder(requestArray);
            }else if (requestArray.get(0).equals("GetInventories")) {
                GetInventories getInventories = new GetInventories();
                returnArray = getInventories.getInventories(requestArray);
            }else if (requestArray.get(0).equals("AddInventory")) {
                AddInventory addInventory = new AddInventory();
                returnArray = addInventory.addInventory(requestArray);
            }else if (requestArray.get(0).equals("GetInventorySize")) {
                GetInventorySize getInventorySize = new GetInventorySize();
                returnArray = getInventorySize.getInventorySize(requestArray);
            }else if (requestArray.get(0).equals("GetTradeSize")) {
                GetTradeSize getTradeSize = new GetTradeSize();
                returnArray = getTradeSize.getTradeSize(requestArray);
            }else {
                returnArray.add("Failure");
                returnArray.add("No Request Matches: " + requestArray.get(0) );
            }
        } catch (Exception e){
            System.out.println("Error while directing requests: " + e);
            returnArray.add("Error");
            returnArray.add("Error while directing requests" + e);
        }

        return returnArray;
    }
}
