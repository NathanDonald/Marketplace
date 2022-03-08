package Application.Screens.UserScreen;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the UserDataSource table
 * application.
 */
public interface UserDataSource {
    /**
     * Adds an User to the users list, if it is not already in the list
     *
     * @param a User to add.
     */
    void addUser(User a);

    /**
     * Modifies an User on the users list.
     *
     * @param a User to modify
     */
    void modifyUser(User a);

    /**
     * Extracts all the details of a User from the user data source on the
     * userID passed in.
     *
     * @param userID The userID as a string to search for.
     * @return all details in a User object with the userName
     */
    User getUser(Integer userID);

    /**
     * Gets the number of records in the user table.
     *
     * @return size of user table.
     */
    int getSize();

    /**
     * Deletes a User from the user table.
     *
     * @param userID The userID to delete from the asset table.
     */
    void deleteUser(Integer userID);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of userNames from the data source that are used in
     * the user list.
     *
     * @return set of userNames.
     */
    Set<String> userNameSet();
}
