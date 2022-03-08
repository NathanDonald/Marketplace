package Application.Screens.OnlineTrade;

import java.util.Set;

/**
 * Provides functionality needed by any data source for the DepartmentDataSource table
 * application.
 */
public interface OnlineTradeDataSource {
    /**
     * Adds an OnlineTrade to the onlineTrades list, if it is not already in the list
     *
     * @param a OnlineTrade to add
     */
    void addOnlineTrade(OnlineTrade a);

    /**
     * Modifies an OnlineTrade on the onlineTrades list.
     *
     * @param a OnlineTrade to modify
     */
    void modifyOnlineTrade(OnlineTrade a);

    /**
     * Extracts all the details of a OnlineTrade from the onlineTrade data source on the
     * onlineTradeID passed in.
     *
     * @param onlineTradeID The onlineTradeID as a Integer to search for.
     * @return all details in a OnlineTrade object with the onlineTradeName
     */
    OnlineTrade getOnlineTrade(Integer onlineTradeID);

    /**
     * Gets the number of records in the onlineTrade table.
     *
     * @return size of onlineTrade table.
     */
    int getSize();

    /**
     * Deletes a OnlineTrade from the onlineTrade table.
     *
     * @param onlineTradeName The onlineTradeName to delete from the onlineTrade table.
     */
    void deleteOnlineTrade(Integer onlineTradeName);

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

    /**
     * Retrieves a set of onlineTradeNames from the data source that are used in
     * the onlineTrade list.
     *
     * @return set of onlineTradeNames.
     */
    Set<String> onlineTradeNameSet();
}
