//========//========//========//========
package Application.SideBars;

//========//========//========//========//========//========
import static Application.Tools.ActionListeners.*;
import static Application.Reference.Constants.*;
import static Application.Reference.Colours.*;
import Application.Tools.Button;
import Application.Tools.Panel;
import java.util.Hashtable;
import javax.swing.*;

//========//========//========//========//========//========//========//========//========
/**
 * LeftBar:
 * Class to manage the content for the Application's Left bar.
 * @author David Bulyaki
 */
public class LeftBar
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * leftBarButtons:
     * The Hashtable<String Button> containing the Button objects that are
     * implemented in the Application's LeftBar.
     * The Button objects are stored within a Hashtable for convenient lookup
     * when using Button.arrange().
     * @see Button
     * @see Hashtable
     */
    public static Hashtable<String, Button> leftBarButtons = new Hashtable<>();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * LeftBar.create():
     * Method to create the loaded content of the LeftBar.
     * @return JPanel configured with necessary features.
     */
    public static JPanel create()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel leftBar = Panel.create(LEFT_FEATURES_WIDTH, MIN_APP_HEIGHT);
        // Enable setBounds() of JComponents:
        leftBar.setLayout(null);
        leftBar.setBackground(DEFAULT_WINDOW_COLOUR);
        loadLeftBarButtons(leftBar);

        //--------//--------//--------
        return leftBar;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * LeftBar.loadLeftBarButtons():
     * Method to load the leftBar buttons. Some users do not get
     * access to the full list of features.
     * @param leftBar the JPanel of the Application's LeftBar.
     */
    public static void loadLeftBarButtons(JPanel leftBar)
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // ArrayList<Button> buttons = new ArrayList<>();
        // Arrange the vertical presentation of the buttons by rearranging the order of their addition into the ArrayList<Button> buttons:
        leftBarButtons.put(MANAGE_DEPARTMENTS, new Button(leftBar, MANAGE_DEPARTMENTS, manageDepartments()));
        leftBarButtons.put(MANAGE_ASSETS, new Button(leftBar, MANAGE_ASSETS, manageAssets()));
        leftBarButtons.put(MANAGE_ONLINE_TRADES, new Button(leftBar, MANAGE_ONLINE_TRADES, manageOnlineTrades()));
        leftBarButtons.put(MANAGE_USERS, new Button(leftBar, MANAGE_USERS, manageUsers()));
        leftBarButtons.put(MANAGE_INVENTORIES, new Button(leftBar, MANAGE_INVENTORIES, manageInventories()));
        leftBarButtons.put(VIEW_ALL_TRADES, new Button(leftBar, VIEW_ALL_TRADES, viewAllTrades()));
    }
}
