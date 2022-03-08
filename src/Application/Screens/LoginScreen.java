//========//========//========//========
package Application.Screens;

//========//========//========//========//========//========//========//========
import static Application.Tools.ActionListeners.authenticateLogin;
import Application.Screens.DepartmentScreen.DepartmentUI;
import static Application.Tools.GUITools.setFrameSize;
import static Application.Appearance.Appearance.*;
import static Application.Reference.Constants.*;
import Application.Screens.AssetScreen.AssetUI;
import static Application.Reference.Colours.*;
import static Application.SideBars.LeftBar.*;
import static Application.MenuBar.MenuBar.*;
import static Application.Tools.Button.*;

import Application.Screens.InventoryScreen.InventoryUI;
import Application.Screens.OnlineTrade.OnlineTradeUI;
import Application.Screens.UserScreen.User;
import Application.Screens.UserScreen.UserUI;
import Application.Tools.TextField;
import Application.Tools.Button;
import java.util.ArrayList;
import java.io.IOException;
import javax.swing.*;
import Server.*;

//========//========//========//========//========//========//========//========//========//========
/**
 * LoginScreen:
 * The class used to generate the application Home Screen.
 * @author David Bulyaki
 */
public class LoginScreen
{

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * loginScreen:
     * The JPanel responsible for housing the LoginScreen content.
     */
    public static JPanel loginScreen = new JPanel();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * usernameEntry:
     * The TextField responsible for inputting the username.
     */
    public static TextField usernameEntry = new TextField(15);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * passwordEntry:
     * The JPasswordField responsible for inputting the password.
     */
    public static JPasswordField passwordEntry = new JPasswordField(20);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * enabledLeftBarButtons:
     * The privately accessible list of buttons that are configured depending on
     * User.PermissionLevel.
     */
    private static ArrayList<String> enabledLeftBarButtons = new ArrayList<>();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * LoginScreen.create():
     * Generates the LoginScreen content.
     * @author David Bulyaki
     */
    public static JPanel create() throws IOException
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Disable layout. Enables you to specify coordinate locations for JComponents:
        loginScreen.setLayout(null);
        loginScreen.setBackground(DEFAULT_WINDOW_COLOUR);

        //--------//--------//--------//--------//--------//--------
        int labelWidth = 80;
        int xLabelSpacing = 100;
        int xFieldSpacing = xLabelSpacing + 80;
        int fieldWidth = 200;
        int fieldHeight = 25;
        int yUsername = 50;
        int yPassword = yUsername + 30;
        int yButton = yPassword + 30;
        int buttonWidth = 80;

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel username = new JLabel("Username:");
        username.setFont(getMainFont(15));
        username.setForeground(MAIN_ACCENT_COLOUR);
        username.setBounds(xLabelSpacing, yUsername, labelWidth, fieldHeight);
        loginScreen.add(username);

        //--------//--------//--------//--------//--------//--------//--------//--------
        usernameEntry.setBounds(xFieldSpacing, yUsername, fieldWidth, fieldHeight);
        loginScreen.add(usernameEntry);

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel password = new JLabel("Password:");
        password.setFont(getMainFont(15));
        password.setForeground(MAIN_ACCENT_COLOUR);
        password.setBounds(xLabelSpacing, yPassword, labelWidth, fieldHeight);
        loginScreen.add(password);

        //--------//--------//--------//--------//--------//--------//--------//--------
        passwordEntry.setBounds(xFieldSpacing, yPassword, fieldWidth, fieldHeight);
        passwordEntry.setFont(getMainFont(12));
        passwordEntry.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        passwordEntry.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        loginScreen.add(passwordEntry);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        //Retrieve host and port from config file
        GetPropertyValues val = new GetPropertyValues();
        String serverAddress = val.getHost();
        Integer portNumber = val.getPort();
        Client client = new Client(serverAddress, portNumber);

        //--------//--------//--------
        if (!client.start())
            client.start();

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        Button loginButton = new Button(loginScreen, "Login", authenticateLogin(client));
        loginButton.setBounds(xFieldSpacing, yButton, buttonWidth, fieldHeight);

        //--------//--------//--------
        return loginScreen;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * resetLoginCredentials():
     * Simple method for clearing the TextField and JPasswordField used for the LoginScreen.
     */
    public static void resetLoginCredentials()
    {
        usernameEntry.setText(CLEAR_TEXT);
        passwordEntry.setText(CLEAR_TEXT);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * logOutFromMenus():
     * Simple method used to switch the main JMenuBar to not visible when a user is logged out.
     */
    public static void logOutFromMenus()
    {
        // Remove User.PermissionLevel-defined button arrangement for next User:
        deArrangeButtons(leftBarButtons);
        menuBar.setVisible(false);
        leftPanel.setVisible(false);
        rightPanel.setVisible(false);
        setFrameSize(LOGIN_SCREEN_SIZE);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * logIntoMenus():
     * Simple method used to access, and then enable or disable Application features based on
     * the permission level of the User.
     */
    public static void logIntoMenus(String permissions)
    {
        // Perform User-specific login methods based on User.Permission level:
        if(permissions.equals(ADMIN_TOKEN))
        {
            logInAsAdmin();
        }
        else
        {
            logInAsStandardUser();
        }

        // Then perform login methods common to all Users:
        commonUserLoginProcedures();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * logInAsAdmin():
     * Simple method used to switch the main JMenuBar to visible when an admin user is logged in.
     */
    public static void logInAsAdmin()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Configure the LeftBar as an Admin User:
        enabledLeftBarButtons = new ArrayList<>();
        // Enable buttons for admin User by adding to ArrayList:
        enabledLeftBarButtons.add(MANAGE_DEPARTMENTS);
        enabledLeftBarButtons.add(MANAGE_ASSETS);
        enabledLeftBarButtons.add(MANAGE_ONLINE_TRADES);
        enabledLeftBarButtons.add(MANAGE_USERS);
        enabledLeftBarButtons.add(MANAGE_INVENTORIES);
        enabledLeftBarButtons.add(VIEW_ALL_TRADES);
        // Arrange enabled Button objects according to permission level:
        arrangeButtons(leftBarButtons, enabledLeftBarButtons, 20, 20, 40, 200, 30);

        //--------//--------//--------//--------
        // Enable buttons for Admin:
        AssetUI.enableButtons();
        DepartmentUI.enableButtons();
        InventoryUI.enableButtons();
        OnlineTradeUI.enableButtons();
        UserUI.enableButtons();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * logInAsStandardUser():
     * Simple method used to switch the main JMenuBar to visible when a standard user is logged in.
     */
    public static void logInAsStandardUser()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Configure the LeftBar as a standard User:
        enabledLeftBarButtons = new ArrayList<>();
        // Enable buttons for a standard User by adding to ArrayList:
        enabledLeftBarButtons.add(MANAGE_DEPARTMENTS);
        enabledLeftBarButtons.add(MANAGE_ASSETS);
        enabledLeftBarButtons.add(MANAGE_ONLINE_TRADES);
        enabledLeftBarButtons.add(MANAGE_USERS);
        enabledLeftBarButtons.add(MANAGE_INVENTORIES);
        enabledLeftBarButtons.add(VIEW_ALL_TRADES);
        // Arrange enabled Button objects according to permission level:
        arrangeButtons(leftBarButtons, enabledLeftBarButtons,20, 20, 40, 200, 30);

        //--------//--------//--------//--------//--------
        // Disable buttons for User:
        AssetUI.disableButtons();
        DepartmentUI.disableButtons();
        InventoryUI.disableButtons();
        OnlineTradeUI.disableButtons();
        UserUI.disableButtons();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * commonUserLoginProcedures():
     * Simple method used for logging in Users of all permission levels.
     */
    public static void commonUserLoginProcedures()
    {
        menuBar.setVisible(true);
        leftPanel.setVisible(true);
        rightPanel.setVisible(true);
        setFrameSize(DEFAULT_SCREEN_SIZE);
    }
}
