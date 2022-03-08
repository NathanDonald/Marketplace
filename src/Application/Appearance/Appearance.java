//========//========//========//========
package Application.Appearance;

//========//========//========//========//========//========//========//========
import static Application.Screens.LoginScreen.logOutFromMenus;
import Application.Screens.DepartmentScreen.DepartmentUI;
import static Application.Reference.Constants.*;
import Application.Screens.AssetScreen.AssetUI;
import java.security.NoSuchAlgorithmException;

import Application.Screens.InventoryScreen.InventoryUI;
import Application.Screens.OnlineTrade.OnlineTradeUI;
import Application.Screens.UserScreen.UserUI;
import static Application.Application.frame;
import Application.Screens.LoginScreen;
import Application.SideBars.RightBar;
import Application.SideBars.LeftBar;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========//========//========//========//========
/**
 * Appearance:
 * The class used to manage / build the overall appearance of the Application.
 */
public class Appearance
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    // Initialise Graphics objects as static, so they may be accessed by
    // other classes, negating the need for excessive parsing of parameters
    // into other class methods:

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * centrePanel:
     * The JPanel responsible for housing the different Application Views.
     */
    public static JPanel centrePanel = new JPanel();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * onlineTrades:
     * The JPanel responsible for housing the different OnlineTradeComponents.
     */
    public static JScrollPane onlineTradeComponents = new JScrollPane();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * leftPanel:
     * The JPanel responsible for housing the left-sided features in the Application Views.
     */
    public static JPanel leftPanel = LeftBar.create();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * rightPanel:
     * The JPanel responsible for housing right-sided features in the different Application Views.
     */
    public static JPanel rightPanel = RightBar.create();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * screens:
     * The CardLayout responsible for switching the different Application Views.
     */
    public static CardLayout screens = new CardLayout();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Appearance.create():
     * Generates the HomeScreen content.
     *
     * @author David Bulyaki
     * @see Application.Application
     */
    public static void create() throws IOException, NoSuchAlgorithmException {
        //--------//--------//--------//--------//--------//--------
        // Initialise the mainPanel to load all content into:
        JPanel mainPanel = new JPanel(new BorderLayout());

        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Configure the centrePanel to .show() what the CardLayout alternatingView is set to:
        centrePanel.setLayout(screens);

        //--------//--------//--------//--------//--------//--------//--------//--------
        // Add all the different screen options to the centrePanel:
        centrePanel.add(LoginScreen.create(), VIEW_0_LOGIN_SCREEN);
        centrePanel.add(onlineTradeComponents, VIEW_2_MARKET_TRADES);
        centrePanel.add(new AssetUI(), VIEW_3_MANAGE_ASSETS);
        centrePanel.add(new UserUI(), VIEW_4_MANAGE_USERS);
        centrePanel.add(new DepartmentUI(), VIEW_5_MANAGE_DEPARTMENTS);
        centrePanel.add(new OnlineTradeUI(), VIEW_6_MANAGE_ONLINETRADES);
        centrePanel.add(new InventoryUI(), VIEW_7_MANAGE_INVENTORIES);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Ensure menus are initialised as not visible upon first instance of LoginScreen:
        logOutFromMenus();
        // Initialise the desired view for the "Home Screen":
        screens.show(centrePanel, VIEW_0_LOGIN_SCREEN);

        //--------//--------//--------//--------//--------//--------
        // Combine all content to create the Home Screen:
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        frame.add(mainPanel);
    }
}
