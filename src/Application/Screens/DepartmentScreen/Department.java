package Application.Screens.DepartmentScreen;

import java.io.Serial;
import java.io.Serializable;

/**
 * The class that represents the Departments in the system. The methods in this class allow one to create Departments and
 * assigns them to the database, get the current Online Trade Listings in the system made by the Department and change
 * the amount of credits owned by the Department in the database.
 */
public class Department implements Comparable<Department>, Serializable {

    @Serial
    private static final long serialVersionUID = -7092701502990374424L; /**TODO: Confirm what this line does*/

    private Integer departmentID;

    private String departmentName;

    private Integer departmentManager;

    private Integer departmentCredits;

    private Boolean departmentActive;

    /**
     * No args constructor.
     */
    public Department()
    {
        //
    }

    /**
     * Constructor method that creates the department in the database and then assigns the inputted name and credits
     * to the department.
     *
     * @param departmentID The unique departmentID that will be assigned to the department.
     * @param departmentName The name that will be assigned to the department.
     * @param departmentManager The managerID that will be assigned to the department.
     * @param departmentCredits The amount of credits that will be assigned to the department.
     * @param departmentActive The Active status that will be assigned to the department.
     */
    public Department(Integer departmentID, String departmentName, Integer departmentManager, Integer departmentCredits, Boolean departmentActive) {
        //TODO: Should this comment be removed?
        // Code that creates the department record in the database then assigns the departmentID, departmentName, departmentManager, credits and departmentActive status to the department.

        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentManager = departmentManager;
        this.departmentCredits = departmentCredits;
        this.departmentActive = departmentActive;
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
     * @return the departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName the department name to set
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return the departmentManager
     */
    public Integer getDepartmentManager() {
        return departmentManager;
    }

    /**
     * @param departmentManager the department manager to set
     */
    public void setDepartmentManager(Integer departmentManager) {
        this.departmentManager = departmentManager;
    }

    /**
     * @return the departmentCredits
     */
    public Integer getDepartmentCredits() {
        return departmentCredits;
    }

    /**
     * @param departmentCredits the department credits to set
     */
    public void setDepartmentCredits(Integer departmentCredits) {
        this.departmentCredits = departmentCredits;
    }

    /**
     * @return the departmentActive
     */
    public Boolean getDepartmentActive() {
        return departmentActive;
    }

    /**
     * @param departmentActive the department active status to set
     */
    public void setDepartmentActive(Boolean departmentActive) {
        this.departmentActive = departmentActive;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @param other The other Department object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *            being compared to this object.
     */
    public int compareTo(Department other) {
        return this.departmentID.compareTo(other.departmentID);
    }

    /**
     * @see Object#toString()
     */
    public String toString() {
        return departmentID + " " + departmentName + ", " + departmentManager + " " + departmentCredits + " " + departmentActive;
    }

    /**
     * Method that changes the department's credits balance to the amount inputted.
     *
     * @param amount The amount that the department's credit balance will change to.
     */
    public void changeCreditBalance(Integer amount){
        //Code to change the balance of the department's credits in the database to the amount inputted.
    }
}
