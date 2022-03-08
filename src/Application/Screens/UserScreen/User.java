package Application.Screens.UserScreen;

/**
 * The class that represents the Users of the system. The methods of this class allow one to interact with the User's
 * information, assigning it to them in the database or retrieving it for use in the system. One can also create an
 * online trade object instance via a method as well.
 */
public class User {

    private Integer userID;

    private String firstName;

    private String surname;

    private String email;

    private Integer departmentID;

    private String permissionLevel;

    private String password;

    private String salt;

    private boolean userIsActive;


    /**
     * No args constructor.
     */
    public User() {
    }

    /**
     * A Constructor method for the User class that takes input of the username and password and assigns it to the User
     * in the database.
     *
     * @param userID          The id of the user
     * @param firstName       The first name of the user
     * @param surname         The surname of the user
     * @param email           The email of the user
     * @param departmentID    The department ID of the user
     * @param permissionLevel The permission level of the user
     * @param password        The password of the user
     * @param userIsActive    Whether the user is active or not
     */
    public User(Integer userID, String firstName, String surname, String email, Integer departmentID, String permissionLevel, String password, String salt, boolean userIsActive) {
        //Code that adds the User instance to the database, assigns them their username and password.
        this.userID = userID;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.departmentID = departmentID;
        this.permissionLevel = permissionLevel;
        this.userIsActive = userIsActive;
        this.password = password;
        this.salt = salt;
    }

    /**
     * Gets the user ID
     *
     * @return the userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets the user ID to the one inputted
     *
     * @param userID the userID to set
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Gets the first name
     *
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name to the one inputted
     *
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the surname
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname to the one inputted
     *
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email to the one inputted
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the department ID
     *
     * @return the departmentID
     */
    public Integer getDepartmentID() {
        return departmentID;
    }

    /**
     * Sets the department ID to the one inputted
     *
     * @param departmentID the departmentID to set
     */
    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    /**
     * Gets the permission level
     *
     * @return the firstName
     */
    public String getPermissionLevel() {
        return permissionLevel;
    }

    /**
     * Sets the permission level to the one inputted
     *
     * @param permissionLevel the permission level to set
     */
    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    /**
     * Gets the password
     *
     * @return the passworde
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password to the one inputted
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the salt
     *
     * @return the salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Sets the salt to the one inputted
     *
     * @param salt the salt to set
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Gets the active status
     *
     * @return the active status
     */
    public boolean getUserIsActive() {
        return userIsActive;
    }

    /**
     * Sets the active status to the one inputted
     *
     * @param userIsActive the active status to set
     */
    public void setUserIsActive(boolean userIsActive) {
        this.userIsActive = userIsActive;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @param other The other User object to compare against.
     * @return a negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     * @throws ClassCastException if the specified object's type prevents it from
     *                            being compared to this object.
     */
    public int compareTo(User other) {
        return this.userID.compareTo(other.userID);
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return userID + " " + firstName + ", " + surname + " " + email + " " + departmentID + " " + permissionLevel + " " + password + " " + salt + " " + userIsActive;
    }


}