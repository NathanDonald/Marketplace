package Application.Screens.InventoryScreen;

import Application.Screens.AssetScreen.Asset;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the InventoryDataSource table
 * application.
 */
public interface InventoryDataSource {
    /**
     * Adds an Inventory to the inventory list, if it is not already in the list
     *
     * @param i Inventory to add
     */
    void addInventory(Inventory i);

    /**
     * Modifies an Inventory on the inventory list.
     *
     * @param a Inventory to modify
     */
    void modifyInventory(Inventory a);

    /**
     * Extracts all the details of a Inventory from the inventory data source on the
     * inventoryName passed in.
     *
     * @param inventoryID The inventoryID as a string to search for.
     * @return all details in a Inventory object with the inventoryID
     */
    Inventory getInventory(Integer inventoryID);

    /**
     * Gets the number of records in the inventory table.
     *
     * @return size of inventory table.
     */
    int getSize();

    /**
     * Deletes a Inventory from the inventory table.
     *
     * @param inventoryID The inventoryID to delete from the inventory table.
     */
    void deleteInventory(Integer inventoryID);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of inventoryID from the data source that are used in
     * the inventory list.
     *
     * @return set of inventoryIDs.
     */
    Set<Integer> inventoryIDSet();
}
