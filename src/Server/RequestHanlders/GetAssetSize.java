package Server.RequestHanlders;

import Application.Screens.AssetScreen.JDBCAssetDataSource;
import Application.Screens.UserScreen.JDBCUserDataSource;

import java.util.ArrayList;

public class GetAssetSize {
    /**
     * Request handler to find the number of assets in the database
     *
     * @param assetDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the size at [0] - if failed will return error
     */
    public ArrayList<String> getAssetSize(ArrayList<String> assetDetails) {

        JDBCAssetDataSource jdbcAssetDataSource = new JDBCAssetDataSource();
        ArrayList<String> returnArray = new ArrayList<>();
        int count = jdbcAssetDataSource.getSize();

        returnArray.add(Integer.toString(count));

        return returnArray;
    }
}
