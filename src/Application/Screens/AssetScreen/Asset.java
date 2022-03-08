//========//========//========//========//========
package Application.Screens.AssetScreen;

//========//========//========//========//========
import static Application.Reference.Colours.*;
import java.io.Serializable;
import java.awt.*;

//========//========//========//========//========//========//========//========//========//========//========//========//========
/**
 * The class that represents the Asset types in the system. This class contains the methods to create and remove an
 * Asset from the Database.
 */
public class Asset  implements Comparable<Asset>, Serializable
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private static final long serialVersionUID = -7092701502990374424L; /**TODO: Confirm what this line does*/
    private Integer assetID;
    private String assetName;
    private String assetDescription;
    private String assetCategory;
    private String assetColour;
    private Boolean assetActive;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * No args constructor.
     */
    public Asset()
    {
        //
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Constructor method that creates the department in the database and then assigns the inputted name and credits
     * to the department.
     *
     * @param assetID The unique departmentID that will be assigned to the asset.
     * @param assetName The name that will be assigned to the asset.
     * @param assetDescription Further information to describe the asset.
     * @param assetCategory The category of asset to which the asset belongs.
     * @param assetActive The Active status that will be assigned to the asset.
     */
    public Asset(Integer assetID, String assetName, String assetDescription, String assetCategory, Boolean assetActive)
    {
        //TODO: Better comment here
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetDescription = assetDescription;
        this.assetCategory = assetCategory;
        this.assetActive = assetActive;
        // Initialise Asset with a new, randomly generated colour:
        this.assetColour = randomColour();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @return the assetID
     */
    public Integer getAssetID() {
        return assetID;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @param assetID the assetID to set
     */
    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @return the assetName
     */
    public String getAssetName() {
        return assetName;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @param assetName the asset name to set
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @return the assetDescription
     */
    public String getAssetDescription() {
        return assetDescription;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @param assetDescription the asset description to set
     */
    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @return the assetCategory
     */
    public String getAssetCategory() {
        return assetCategory;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @param assetCategory the asset category to set
     */
    public void setAssetCategory(String assetCategory) {
        this.assetCategory = assetCategory;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @return the assetActive
     */
    public Boolean getAssetActive() {
        return assetActive;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @param assetActive the asset active status to set
     */
    public void setAssetActive(Boolean assetActive) {
        this.assetActive = assetActive;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @return the assetColour
     */
    public String getAssetColorValue() {
        return assetColour;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @param assetColour the asset colour to set
     */
    public void setAssetColourValue(String assetColour) {
        this.assetColour = assetColour;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getColor():
     * Converts the colour information AssetColour from String to Color.
     *
     * @return Color
     * @see Color
     */
    public Color getAssetColor()
    {
        String[] rgb = this.assetColour.split("_", 3);
        return new Color(   Integer.parseInt(rgb[0]),
                Integer.parseInt(rgb[1]),
                Integer.parseInt(rgb[2]),
                MAX_ALPHA);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
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
    public int compareTo(Asset other) {
        return this.assetID.compareTo(other.assetID);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * @see Object#toString()
     */
    public String toString() {
        return assetID + " " + assetName + ", " + assetDescription + " " + assetCategory + " " + assetActive;
    }
}