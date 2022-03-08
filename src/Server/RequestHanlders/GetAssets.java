package Server.RequestHanlders;

import Application.Screens.AssetScreen.JDBCAssetDataSource;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class GetAssets {
    /**
     * Request handler to retrieve a set of Asset Names
     *
     * @param assetDetails Only stores the request type at [0] as no other params are needed
     *
     * @return An arraylist containing the different Names from [0] to n
     */
    public ArrayList<String> getAssets(ArrayList<String> assetDetails) {

        JDBCAssetDataSource jdbcAssetDataSource = new JDBCAssetDataSource();

        ArrayList<String> returnArray = new ArrayList<>();
        Set<String> assetNames = new TreeSet<String>();
        assetNames = jdbcAssetDataSource.assetNameSet();

        for (String s : assetNames) {
            returnArray.add(s);
        }

        return returnArray;
    }
}
