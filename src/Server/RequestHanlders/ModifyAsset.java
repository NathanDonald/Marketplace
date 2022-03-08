package Server.RequestHanlders;

import Application.Screens.AssetScreen.JDBCAssetDataSource;
import Application.Screens.AssetScreen.Asset;

import java.util.ArrayList;

public class ModifyAsset {
    /**
     * @param assetDetails arraylist with modification details
     *                     0- Request type
     *                     1- Permissions
     *                     3- Asset name
     *                     4- Asset description
     *                     5- Asset Category
     *                     6- Boolean Is Asset Active
     * @return If the modification worked
     *
     */
    public ArrayList<String> modifyAsset(ArrayList<String> assetDetails) {

        JDBCAssetDataSource jdbcAssetDataSource = new JDBCAssetDataSource();
        ArrayList<String> returnArray = new ArrayList<>();

        //Get all data for the specified user
        Asset returnAsset = jdbcAssetDataSource.getAsset(Integer.valueOf(assetDetails.get(2)));

        //Check if assetActive is true or false
        boolean truthValue;
        if(assetDetails.get(6).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }


        Asset a = new Asset(returnAsset.getAssetID(), assetDetails.get(3), assetDetails.get(5), assetDetails.get(4), truthValue);

        jdbcAssetDataSource.modifyAsset(a);


        returnArray.add("Success");
        returnArray.add("User " + assetDetails.get(2) + " has been modified");

        System.out.println("edited " + assetDetails.get(2) + " in db");
        return returnArray;

    }
}
