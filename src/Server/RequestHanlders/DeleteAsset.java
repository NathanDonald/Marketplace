package Server.RequestHanlders;

import Application.Screens.AssetScreen.JDBCAssetDataSource;

import java.util.ArrayList;

/**
 * Handle request to delete an asset
 * Only users with admin permissions can use
 */
public class DeleteAsset {
    /**
     * @param assetDetails arraylist holding details of the Asset to be deleted
     *                            0- request type
     *                            1- permission level
     *                            2- ID
     * @return confirmation of the department added
     */
    public ArrayList<String> deleteAsset(ArrayList<String> assetDetails){

        JDBCAssetDataSource jdbcAssetDataSource = new JDBCAssetDataSource();
        jdbcAssetDataSource.deleteAsset(assetDetails.get(2));

        ArrayList<String> returnArray = new ArrayList<>();

        returnArray.add("Success");
        returnArray.add("asset " + assetDetails.get(2) + " has been deleted");
        System.out.println("deleted " + assetDetails.get(2) + " from db");
        return returnArray;

    }
}
