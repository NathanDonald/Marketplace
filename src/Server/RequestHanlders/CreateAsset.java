package Server.RequestHanlders;

import Application.Screens.AssetScreen.Asset;
import Application.Screens.AssetScreen.JDBCAssetDataSource;

import java.util.ArrayList;
import java.util.Set;

/**
 * Handle request to create an asset
 */
public class CreateAsset {
    /**
     * Adds asset to database
     * @param assetDetails an arraylist storing the assets details
     *                     0- Request type
     *                     2- Permissions
     *                     3- AssetID
     *                     4- Asset Name
     *                     5- Asset Description
     *                     6- Boolean of asset active
     *
     * @return an arraylist holding confirmation that the asset has been added
     */
    public ArrayList<String> createAsset(ArrayList<String> assetDetails){

        //Check if assetActive is true or false
        boolean truthValue;
        if(assetDetails.get(6).equals("true")){
            truthValue = true;
        }else{
            truthValue = false;
        }
        Asset a = new Asset(Integer.valueOf(assetDetails.get(2)), assetDetails.get(3), assetDetails.get(4), assetDetails.get(5), truthValue);
        JDBCAssetDataSource jdbcAssetDataSource = new JDBCAssetDataSource();
        ArrayList<String> returnArray = new ArrayList<>();

        //Check if asset already exists, return an error if it does
        Set<String> setName = jdbcAssetDataSource.assetNameSet();

        if(!checkName(assetDetails.get(3), setName)){
            returnArray.add("error");
            returnArray.add("asset " + assetDetails.get(3) + " already exists");
            System.out.println("failed to add " + assetDetails.get(3) + " to db");
            return returnArray;
        }

        jdbcAssetDataSource.addAsset(a);



        returnArray.add("Success");
        returnArray.add("asset " + assetDetails.get(2) + " has been added");
        System.out.println("added " + assetDetails.get(2) + " to db");
        return returnArray;
    }
    public boolean checkName(String x, Set<String> nameSet){
        if (nameSet.contains(x)){
            return false;
        }
        return true;
    }
}
