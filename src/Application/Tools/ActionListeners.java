//========//========//========//========
package Application.Tools;

//========//========//========//========//========//========//========
import Application.Screens.DepartmentScreen.DepartmentUI;
import Application.Screens.InventoryScreen.InventoryUI;
import Application.Screens.UserScreen.PasswordEncoder;
import Application.Screens.OnlineTrade.OnlineTradeUI;
import static Application.Appearance.Appearance.*;
import static Application.Reference.Constants.*;
import static Application.Screens.LoginScreen.*;
import Application.Screens.AssetScreen.AssetUI;
import java.security.NoSuchAlgorithmException;
import Application.Screens.UserScreen.UserUI;
import Application.Screens.OnlineTrades;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import Server.*;

//========//========//========//========//========//========//========//========//========
/**
 * ActionListeners:
 * The class used for consolidating all the ActionListener|s associated with
 * the GUI's features.
 *
 * @author David Bulyaki
 */
public class ActionListeners
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ActionListeners.pendingFunctionality():
     * The ActionListener designed to prompt the tester that functionality is still
     * pending for the feature being tested. Alert is displayed via a pop-up.
     *
     * @author David Bulyaki
     * @param featureName The name of the feature as String, referenced by the method.
     * @return ActionListener
     * @see Application.MenuBar.MenuBar
     * @see MenuBarOption
     */
    public static ActionListener pendingFunctionality(String featureName)
    {
        /*
            Lambda: e = ActionEvent:
            Lambda replaces ...

            return new ActionListener()
            {
                @Override actionPerformed(ActionEvent e)
                {
                    // code:
                }
            };
        */
        return e -> JOptionPane.showMessageDialog((Component)e.getSource(),featureName + ": Pending Functionality.");
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ActionListeners.fileExit():
     * The ActionListener specifically linked to the event associated
     * with clicking on JMenuBar "File" / JMenuBarItem "Exit".
     *
     * This ActionListener causes the application to close.
     *
     * @author David Bulyaki
     * @return ActionListener
     * @see Application.MenuBar.MenuBar
     * @see MenuBarOption
     */
    public static ActionListener fileExit()
    {
        return e ->
        {
            System.exit(0);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ActionListeners.selectScreen():
     * The ActionListener specifically linked to the event associated
     * with clicking on JMenuBar "Select Screen" / JMenuBarItem "Screen Name".
     *
     * This ActionListener causes the application to display the desired Screen based on ...
     * 1: the referenced JPanel, centrePanel (whose setLayout is configured to the CardLayout screens) and ...
     * 2: the referenced Graphics Component's (IE the contents added to centrePanel) identifier (as final String).
     *
     * @author David Bulyaki
     * @param compID The Component identifier used to select which JPanel to
     *               show in the cardLayout.
     * @return ActionListener
     * @see Application.MenuBar.MenuBar
     * @see Application.Screens.OnlineTrades
     * @see MenuBarOption
     */
    public static ActionListener selectScreen(final String compID, boolean logout)
    {
        return e ->
        {
            // Log the user out from accessing the main JMenuBar if the
            // ActionListener has its' logout boolean set to do so:
            if(logout)
            {
                logOutFromMenus();
            }
            screens.show(centrePanel, compID);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * authenticateLogin:
     * The method used to verify username and password login credentials:
     * @author David Bulyaki
     * @return ActionListener
     */
    public static ActionListener authenticateLogin(Client client)
    {
        return e ->
        {
            String loginView = VIEW_2_MARKET_TRADES;

            try {
                //For first launch, a special ID and will be stored to bypass login. This can be used when first initiating the server to allow admins to
                // access functions to add users to the database
                if(usernameEntry.getText().equals("BypassAsAdmin") ){
                    // Reset login credentials for next login:
                    resetLoginCredentials();
                    // Allow the user to access the main JMenuBar once logged in:
                    logIntoMenus(ADMIN_TOKEN);
                    screens.show(centrePanel, loginView);
                    return;
                }

                //Check if username exists and if it does get salt
                ArrayList<String> saltRequest = new ArrayList<>();
                saltRequest.add("GetSingleUser");
                saltRequest.add("SaltRequest");
                saltRequest.add(usernameEntry.getText());

                client.sendData(saltRequest);
                ArrayList<String> serverReturn = new ArrayList<>();
                try {
                    serverReturn = client.getList();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }


                //Check if there was a user with that id
                if (serverReturn.get(0) != null) {
                    try {
                        //Hash the password with the salt given and send to server
                        //PasswordEncoder passwordEncoder = new PasswordEncoder();
                        String hashedPass = PasswordEncoder.passSalt(String.valueOf(passwordEntry.getPassword()), serverReturn.get(0));
                        ArrayList<String> loginRequest = new ArrayList<>();
                        loginRequest.add("Login");
                        loginRequest.add("Initial");
                        loginRequest.add(usernameEntry.getText());
                        loginRequest.add(hashedPass);
                        client.sendData(loginRequest);

                        //Get return from server
                        try {
                            serverReturn = client.getList();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        for (String item : serverReturn) {
                            System.out.println(item);
                        }

                    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                        noSuchAlgorithmException.printStackTrace();
                    }
                }


                // Check if username and password are correct:

                if (serverReturn.get(0) == null || serverReturn == null) {
                    // Reset login credentials for failed attempt:
                    resetLoginCredentials();
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Login Error!!\nIncorrect username.");

                } else if (serverReturn.get(0).equals("Success")) {

                    // Reset login credentials for next login:
                    resetLoginCredentials();
                    // Allow the user to access the main JMenuBar once logged in:
                    logIntoMenus(serverReturn.get(2));
                    screens.show(centrePanel, loginView);
                } else {
                    // Reset login credentials for failed attempt:
                    resetLoginCredentials();
                    JOptionPane.showMessageDialog((Component) e.getSource(), "Login Error!!\nIncorrect username or password.");
                }
            }
            catch(Exception exception)
            {
                resetLoginCredentials();
                JOptionPane.showMessageDialog((Component)e.getSource(), "Login Error!!\nIncorrect username or password.");
            }
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     *
     */
    public static ActionListener manageAssets()
    {
        return e ->
        {
            // Update the data:
            AssetUI.fetchData();
            screens.show(centrePanel, VIEW_3_MANAGE_ASSETS);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     *
     */
    public static ActionListener manageUsers()
    {
        return e ->
        {
            // Update the data:
            UserUI.fetchData();
            screens.show(centrePanel, VIEW_4_MANAGE_USERS);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     *
     */
    public static ActionListener manageOnlineTrades()
    {
        return e ->
        {
            // Update the data:
            OnlineTradeUI.fetchData();
            screens.show(centrePanel, VIEW_6_MANAGE_ONLINETRADES);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     *
     */
    public static ActionListener manageInventories()
    {
        return e ->
        {
            // Update the data:
            InventoryUI.fetchData();
            screens.show(centrePanel, VIEW_7_MANAGE_INVENTORIES);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * manageDepartments():
     * Simple method for verifying initial Asset editing integration.
     * @return ActionListener
     */
    public static ActionListener manageDepartments()
    {
        return e ->
        {
            // Update the data:
            DepartmentUI.fetchData();
            screens.show(centrePanel, VIEW_5_MANAGE_DEPARTMENTS);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * viewAllTrades():
     * Simple method for viewing all OnlineTrade objects that exist.
     * @return ActionListener
     */
    public static ActionListener viewAllTrades()
    {
        return e ->
        {
            centrePanel.remove(onlineTradeComponents);
            onlineTradeComponents = OnlineTrades.create();
            centrePanel.add(onlineTradeComponents, VIEW_2_MARKET_TRADES);
            screens.show(centrePanel, VIEW_2_MARKET_TRADES);
        };
    }
}
