package Application.Screens.OnlineTrade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class that represents the Online Trade Listings of the system. The methods of this class allow one to create
 * and remove Online Trade Listings from the database.
 */
public class OnlineTrade implements Comparable<OnlineTrade>, Serializable {

    private Integer onlineTradeID;

    private String tradeType;

    private Integer userID;

    private Integer assetID;

    private Integer departmentID;

    private Integer quantity;

    private Integer unitPrice;

    private String orderDate;

    private Integer orderStatus;

    private String expiryDate;

    /**
     * Constructor method that creates the listing in the database and assigns the inputted details to that listing.
     *
     * @param onlineTradeID
     * @param tradeType
     * @param userID
     * @param assetID
     * @param departmentID
     * @param quantity
     * @param unitPrice
     * @param orderStatus
     */
    public OnlineTrade(Integer onlineTradeID, String tradeType, Integer userID, Integer assetID, Integer departmentID,
                       Integer quantity, Integer unitPrice, Integer orderStatus) {
        //Code that creates the listing in the database and assigns the inputted details to the listing.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();

        this.onlineTradeID = onlineTradeID;
        this.tradeType = tradeType;
        this.userID = userID;
        this.assetID = assetID;
        this.departmentID = departmentID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = formatter.format(currentDate);
        this.orderStatus = orderStatus;
    }

    public OnlineTrade(Integer onlineTradeID, String tradeType, Integer userID, Integer assetID, Integer departmentID,
                       Integer quantity, Integer unitPrice, String date,  Integer orderStatus) {
        //Code that creates the listing in the database and assigns the inputted details to the listing.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();

        this.onlineTradeID = onlineTradeID;
        this.tradeType = tradeType;
        this.userID = userID;
        this.assetID = assetID;
        this.departmentID = departmentID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = date;
        this.orderStatus = orderStatus;

    }

    /**
     * No args constructor.
     */
    public OnlineTrade() {
    }

    /**
     * @return the onlineTradeID
     */
    public Integer getOnlineTradeID() {
        return onlineTradeID;
    }

    /**
     * @param onlineTradeID the onlineTradeID to set
     */
    public void setOnlineTradeID(Integer onlineTradeID) {
        this.onlineTradeID = onlineTradeID;
    }

    /**
     * @return the trade type
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * @param tradeType the trade type to set
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * @return the userID
     */
    public Integer getUserIDOnlineTrade() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserIDOnlineTrade(Integer userID) {
        this.userID = userID;
    }

    /**
     * @return the assetID
     */
    public Integer getAssetIDOnlineTrade() {
        return assetID;
    }

    /**
     * @param assetID the assetID to set
     */
    public void setAssetIDOnlineTrade(Integer assetID) {
        this.assetID = assetID;
    }

    /**
     * @return the departmentID
     */
    public Integer getDepartmentIDOnlineTrade() {
        return departmentID;
    }

    /**
     * @param departmentID the departmentID to set
     */
    public void setDepartmentIDOnlineTrade(Integer departmentID) {
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
     * @return the unitPrice
     */
    public Integer getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the orderDate
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderStatus
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @param other The other Asset object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *            being compared to this object.
     */
    public int compareTo(OnlineTrade other) {
        return this.onlineTradeID.compareTo(other.onlineTradeID);
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @param other The other Asset object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *            being compared to this object.
     */
    public int compareToQuantity(OnlineTrade other) {
        return this.quantity.compareTo(other.quantity);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return onlineTradeID + " " + tradeType + " " + userID + " " + assetID + " " + departmentID  + " " + quantity +
                " " + unitPrice + " " + orderDate + " " + orderStatus + " " + expiryDate;
    }
}
