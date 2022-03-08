package Application.Screens.AssetScreen;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the DepartmentDataSource table TODO: Better comment here
 * application.
 */
public interface AssetDataSource {
    /**
     * Adds an Asset to the assets list, if it is not already in the list
     *
     * @param a Asset to add
     */
    void addAsset(Asset a);

    /**
     * Modifies an Asset on the assets list.
     *
     * @param a Asset to modify
     */
    void modifyAsset(Asset a);

    /**
     * Extracts all the details of a Asset from the asset data source on the
     * assetName passed in.
     *
     * @param assetName The assetName as a string to search for.
     * @return all details in a Asset object with the assetName
     */
    Asset getAsset(String assetName);

    /**
     * Extracts all the details of a Asset from the asset data source on the
     * assetID passed in.
     *
     * @param assetID The assetID as a Integer to search for.
     * @return all details in a Asset object with the assetName
     */
    Asset getAsset(Integer assetID);

    /**
     * Gets the number of records in the asset table.
     *
     * @return size of asset table.
     */
    int getSize();

    /**
     * Deletes a Asset from the asset table.
     *
     * @param assetName The assetName to delete from the asset table.
     */
    void deleteAsset(String assetName);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of assetNames from the data source that are used in
     * the asset list.
     *
     * @return set of assetNames.
     */
    Set<String> assetNameSet();
}

