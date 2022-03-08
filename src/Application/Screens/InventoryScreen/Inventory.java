package Application.Screens.InventoryScreen;

import java.io.Serializable;

/**
 * The class that represents the Inventory types in the system. This class contains the methods to create and remove an
 * Inventory from the Database.
 */
public class Inventory implements Comparable<Inventory>, Serializable {

    private Integer inventoryID;

    private Integer assetID;

    private Integer departmentID;

    private Integer quantity;

    private Boolean inventoryActive;

    /**
     * No args constructor.
     */
    public Inventory() {
    }

    /**
     * Constructor method that creates the department in the database and then assigns the inputted name and credits
     * to the department.
     *
     * @param inventoryID The unique inventoryID that will be assigned to the inventory.
     * @param assetID The assetID identifying which asset is in this inventory.
     * @param departmentID The departmentID identifying which department this inventory belongs to.
     * @param quantity The quantity of the asset in this inventory.
     * @param inventoryActive The Active status that will be assigned to the inventory.
     */
    public Inventory(Integer inventoryID, Integer assetID, Integer departmentID, Integer quantity, Boolean inventoryActive) {

        this.inventoryID = inventoryID;
        this.assetID = assetID;
        this.departmentID = departmentID;
        this.quantity = quantity;
        this.inventoryActive = inventoryActive;
    }

    /**
     * @return the inventoryID
     */
    public Integer getInventoryID() {
        return inventoryID;
    }

    /**
     * @param inventoryID the inventoryID to set
     */
    public void setInventoryID(Integer inventoryID) {
        this.inventoryID = inventoryID;
    }

    /**
     * @return the assetID
     */
    public Integer getAssetID() {
        return assetID;
    }

    /**
     * @param assetID the assetID to set
     */
    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }

    /**
     * @return the departmentID
     */
    public Integer getDepartmentID() {
        return departmentID;
    }

    /**
     * @param departmentID the departmentID to set
     */
    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the inventoryActive
     */
    public Boolean getInventoryActive() {
        return inventoryActive;
    }

    /**
     * @param inventoryActive the inventory active status to set
     */
    public void setInventoryActive(Boolean inventoryActive) {
        this.inventoryActive = inventoryActive;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @param other The other Inventory object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *            being compared to this object.
     */
    public int compareTo(Inventory other) {
        return this.inventoryID.compareTo(other.inventoryID);
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return inventoryID + " " + assetID + ", " + departmentID + " " + quantity + " " + inventoryActive;
    }
}