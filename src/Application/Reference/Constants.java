//========//========//========//========
package Application.Reference;

//========//========//========//========
import Application.Application;
import java.util.Random;
import java.awt.*;

//========//========//========//========//========//========//========//========
/**
 * Constants:
 * The class used to store commonly used values.
 * The single point of reference for Application variables to be updated.
 *
 * @author David Bulyaki
 * @see Application
 */
public class Constants
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * APP_TITLE:
     * The title of the application.
     * Situated at the top of the GUI.
     */
    public final static String APP_TITLE = "Corp-U-Trade";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * APP_ICON:
     * The artwork's filepath for the application's icon normally located on the
     * top, left-hand corner of the GUI.
     *
     * Also appears as the task-bar icon when the application is running.
     */
    public final static String APP_ICON = "gui\\Artwork\\JavaTeam.png";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * LEFT_FEATURES_WIDTH:
     * The minimum width of the GUI's left column, in pixels.
     */
    public final static Integer LEFT_FEATURES_WIDTH = 240;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * RIGHT_FEATURES_WIDTH:
     * The minimum width of the GUI's right column, in pixels.
     */
    public final static Integer RIGHT_FEATURES_WIDTH = 240;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * SCROLL_BAR_WIDTH:
     * A width allowance to account for the thickness of the scroll bars.
     * Set at absolute minimum value.
     */
    public final static Integer SCROLL_BAR_WIDTH = 30;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * SCROLL_BAR_PACKING:
     * A width allowance to account for practical thickness of the scroll bars.
     * Ensures the GUI's minimum width does not surpass an undesirable appearance.
     */
    public final static Integer SCROLL_BAR_PACKING = 18;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ASSET_ICON_WIDTH:
     * The width of the asset icon as it appears throughout the Application.
     */
    public final static Integer ASSET_ICON_WIDTH = 250;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ASSET_ICON_HEIGHT:
     * The height of the asset icon as it appears throughout the Application.
     */
    public final static Integer ASSET_ICON_HEIGHT = 50;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ORDER_ICON_WIDTH:
     * The width of the order icon as it appears throughout the Application.
     */
    public final static Integer ORDER_ICON_WIDTH = 250;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ORDER_ICON_HEIGHT:
     * The height of the order icon as it appears throughout the Application.
     */
    public final static Integer ORDER_ICON_HEIGHT = 70;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ICON_EDGING:
     * The thickness of the edge of an icon.
     */
    public final static Integer ICON_EDGING = 3;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * TRADE_WIDTH:
     * The set width of the ActiveTrade graphics component, in pixels.
     */
    public final static Integer TRADE_WIDTH = 750;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * TRADE_HEIGHT:
     * The set height of the ActiveTrade graphics component, in pixels.
     */
    public final static Integer TRADE_HEIGHT = ASSET_ICON_HEIGHT + 50;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ACTIVE_TRADE_SIZE:
     * The size of the ActiveTrade graphics component, of type Dimension.
     */
    public final static Dimension ACTIVE_TRADE_SIZE = new Dimension(TRADE_WIDTH, TRADE_HEIGHT);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MIN_APP_WIDTH:
     * The minimum width of the application, in pixels.
     * Computed by adding the width of the left column,
     * active trades graphics objects and right column.
     */
    public final static Integer MIN_APP_WIDTH = LEFT_FEATURES_WIDTH + TRADE_WIDTH + SCROLL_BAR_WIDTH + SCROLL_BAR_PACKING + RIGHT_FEATURES_WIDTH;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * TITLE_BAR_PACKING:
     * A height allowance to account for practical thickness of the title bar.
     * Ensures the GUI's minimum height does not surpass an undesirable appearance.
     */
    public final static Integer TITLE_BAR_PACKING = 63;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ONLINE_TRADE_EDGING:
     * The edge thickness of the OnlineTradeComponent.
     */
    public final static Integer ONLINE_TRADE_EDGING = 10;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MIN_NUMBER_OF_TRADES:
     * The minimum number of trades to be displayed by the Application.
     */
    public final static Integer MIN_NUMBER_OF_TRADES = 5;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MIN_APP_HEIGHT:
     * The minimum height of the application, in pixels.
     */
    public final static Integer MIN_APP_HEIGHT = TRADE_HEIGHT * MIN_NUMBER_OF_TRADES + TITLE_BAR_PACKING + ONLINE_TRADE_EDGING;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DEFAULT_SCREEN_SIZE:
     * The screen size of the application, of type Dimension.
     */
    public final static Dimension DEFAULT_SCREEN_SIZE = new Dimension(MIN_APP_WIDTH, MIN_APP_HEIGHT);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * LOGIN_SCREEN_SIZE:
     * The login screen size of the application, of type Dimension.
     */
    public final static Dimension LOGIN_SCREEN_SIZE = new Dimension(600, 300);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_0_LOGIN_SCREEN:
     * The JComponent identifier for the login screen.
     */
    public final static String VIEW_0_LOGIN_SCREEN = "0";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_2_ACTIVE_TRADES:
     * The JComponent identifier for View 2 AKA, the "Active Trades" view.
     */
    public final static String VIEW_2_MARKET_TRADES = "2";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_3_MANAGE_ASSETS:
     * The JComponent identifier for View 3 AKA, the "Manage Assets" view.
     */
    public final static String VIEW_3_MANAGE_ASSETS = "3";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_4_MANAGE_USERS:
     * The JComponent identifier for View 4 AKA, the "Manage Users" view.
     */
    public final static String VIEW_4_MANAGE_USERS = "4";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_5_MANAGE_DEPARTMENTS:
     * The JComponent identifier for View 5 AKA, the "Manage Departments" view.
     */
    public final static String VIEW_5_MANAGE_DEPARTMENTS = "5";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_6_MANAGE_ONLINETRADES:
     * The JComponent identifier for View 6 AKA, the "Manage Online Trades" view.
     */
    public final static String VIEW_6_MANAGE_ONLINETRADES = "6";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_7_MANAGE_INVENTORIES:
     * The JComponent identifier for View 7 AKA, the "Manage Inventories" view.
     */
    public final static String VIEW_7_MANAGE_INVENTORIES = "7";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * BUY_ORDER:
     * The constant that defines if an OnlineTrade object is a BUY_ORDER.
     */
    public final static String BUY_ORDER = "BUY";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * SELL_ORDER:
     * The constant that defines if an OnlineTrade object is a SELL_ORDER.
     */
    public final static String SELL_ORDER = "SELL_ORDER";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * randInt():
     * Returns a random integer between the min and max value specified.
     */
    public static Integer randInt(Integer min, Integer max)
    {
        return (int) (Math.random() * (max - min) + min);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * randBool():
     * Returns a random boolean value either true or false.
     */
    public static boolean randBool()
    {
        Random random = new Random();
        return random.nextBoolean();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * CLEAR_TEXT:
     * The constant that is used to set Strings to being empty.
     */
    public final static String CLEAR_TEXT = "";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getMainFont:
     * The method used for defining the main font, with custom size.
     */
    public static Font getMainFont(Integer size)
    {
        return new Font("Bahnschrift", Font.BOLD, size);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * PERMISSION_LEVEL_STANDARD_USER:
     * The constant used for identifying a standard Applicaiton user, with restricted
     * privileges.
     */
    public final static Integer PERMISSION_LEVEL_STANDARD_USER = 0;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * PERMISSION_LEVEL_ADMIN_USER:
     * The constant used for identifying an admin-level Applicaiton user, with no restrictions.
     */
    public final static Integer PERMISSION_LEVEL_ADMIN_USER = 1;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MANAGE_DEPARTMENTS:
     * The constant used for defining the "Manage Departments" button, for user level, feature-lockout purposes.
     */
    public final static String MANAGE_DEPARTMENTS = "Manage Departments";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MANAGE_ASSETS:
     * The constant used for defining the "Manage Assets" button, for user level, feature-lockout purposes.
     */
    public final static String MANAGE_ASSETS = "Manage Assets";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MANAGE_ONLINE_TRADES:
     * The constant used for defining the "Manage Online Trades" button, for user level, feature-lockout purposes.
     */
    public final static String MANAGE_ONLINE_TRADES = "Manage Online Trades";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MANAGE_USERS:
     * The constant used for defining the "Manage Users" button, for user level, feature-lockout purposes.
     */
    public final static String MANAGE_USERS = "Manage Users";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MANAGE_INVENTORIES:
     * The constant used for defining the "Manage Inventories" button, for user level, feature-lockout purposes.
     */
    public final static String MANAGE_INVENTORIES = "Manage Inventories";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VIEW_ALL_TRADES:
     * The constant used for defining the "Manage Users" button, for user level, feature-lockout purposes.
     */
    public final static String VIEW_ALL_TRADES = "View All Trades";

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * BUTTON_HIGHLIGHT_THICKNESS:
     * The constant used for defining the Button highlight thickness.
     */
    public final static Integer BUTTON_HIGHLIGHT_THICKNESS = 3;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ADMIN_TOKEN:
     * The constant used for defining the Admin-level permission for User login.
     */
    public final static String ADMIN_TOKEN = "mHAahNKa+FFH8T0CqKtqXA==";
}
