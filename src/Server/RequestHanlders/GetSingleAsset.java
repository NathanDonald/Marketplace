package Server.RequestHanlders;

import Application.Screens.AssetScreen.*;

import java.util.ArrayList;

public class GetSingleAsset {
    /**
     * @param assetDetails an arraylist containing the asset Name
     *                     0- Request type
     *                     1- Permissions
     *                     2- Asset Name
     *
     * @return an arraylist with items [0] - [4] being the values in order used to create an online trade object
     */
    public ArrayList<String> getSingleAsset(ArrayList<String> assetDetails){

        JDBCAssetDataSource jdbcAssetDataSource = new JDBCAssetDataSource();
        Asset returnAsset = jdbcAssetDataSource.getAsset(assetDetails.get(2));

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add(Integer.toString(returnAsset.getAssetID()));
        returnArray.add(returnAsset.getAssetName());
        returnArray.add(returnAsset.getAssetCategory());
        returnArray.add(returnAsset.getAssetDescription());

        String active;
        if (returnAsset.getAssetActive()){
            active = "true";
        }else{
            active = "false";
        }
        returnArray.add(active);

        return returnArray;
    }
}
