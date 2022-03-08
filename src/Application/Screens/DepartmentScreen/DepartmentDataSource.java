package Application.Screens.DepartmentScreen;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the department table TODO: Better comment here
 * application.
 */
public interface DepartmentDataSource {
    /**
     * Adds a Department to the departments list, if it is not already in the list
     *
     * @param d Department to add
     */
    void addDepartment(Department d);

    /**
     * Extracts all the details of a Department from the department data source on the
     * department ID passed in.
     *
     * @param departmentID The departmentID as an Integer to search for.
     * @return all details in a Department object with the departmentID
     */
    Department getDepartment(Integer departmentID);

    /**
     * Gets the number of records in the department table. TODO: Are we retrieving records from department table?
     *
     * @return size of department table.
     */
    int getSize();

    /**
     * Deletes a Department from the department table.
     *
     * @param departmentID The departmentID to delete from the department table.
     */
    void deleteDepartment(Integer departmentID);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of departmentIDs from the data source that are used in
     * the department list.
     *
     * @return set of departmentIDs.
     */
    Set<Integer> departmentIDSet();
}

