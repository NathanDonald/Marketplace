//========//========//========//========
package Application.Screens;

//========//========//========//========//========//========//========
import static javax.swing.JOptionPane.showMessageDialog;
import static Application.Tools.GUITools.alterScrollBar;
import Application.Screens.OnlineTrade.OnlineTradeData;
import Application.Screens.OnlineTrade.OnlineTrade;
import static Application.Reference.Constants.*;
import static Application.Reference.Colours.*;
import Application.Tools.OnlineTradeComponent;
import Application.Tools.Panel;
import java.util.ArrayList;
import javax.swing.*;

//========//========//========//========//========//========//========//========//========
/**
 * OnlineTrades:
 * Is the class that handles the Application view associated with viewing
 * the available OnlineTrade objects that are presently active.
 */
public class OnlineTrades
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * onlineTrades:
     * The ArrayList<OnlineTrade> to be referenced for GUI content.
     */
    public static ArrayList<OnlineTrade> onlineTrades(){
        OnlineTradeData onlineTradeData = new OnlineTradeData();
        ArrayList<Integer> ids = onlineTradeData.getModelId();
        ArrayList<OnlineTrade> toReturn = new ArrayList<>();
        for (Integer trade : ids) {
            toReturn.add(onlineTradeData.get(trade));
        }
        return toReturn;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ActiveTrades.loadActiveTrades():
     * The method call to construct the GUI appearance and features
     * for an ActiveTrade graphics component.
     * Loads all ActiveTrade|s into a Box.
     *
     * @author David Bulyaki
     * @return The content of all "Active Trades" contained within a Box.
     * @see OnlineTradeComponent
     * @see Box
     */
    public static Box loadOnlineTrades()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Make Active Trades appear in a column, one below another with BoxLayout.Y_AXIS:
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setMinimumSize(ACTIVE_TRADE_SIZE);

        //--------//--------//--------//--------//--------//--------//--------//--------
        // Add a top edge to the JScrollBar content for aesthetic purposes:
        box.add(createEdge());
        // Generate list of OnlineTrade to display in GUI:
        int x = 0;
        for(OnlineTrade onlineTrade: onlineTrades())
        {
            try{
                box.add(OnlineTradeComponent.create(onlineTrade));
            }catch (Exception e){
                x++;
            }

        }
        if(x != 0 ){

            showMessageDialog(null, "There are " + x + " orders with invalid asset types\n"
                    + "These orders will not be displayed");

        }
        // Add a bottom edge to the JScrollBar content for aesthetic purposes:
        box.add(createEdge());

        //--------//--------
        return box;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * createEdge():
     * Simple class to create the top and bottom edges of the JScrollBar containing the
     * OnlineTradeComponents.
     * @return JPanel
     */
    public static JPanel createEdge()
    {
        JPanel edge = Panel.create(TRADE_WIDTH, ONLINE_TRADE_EDGING/2);
        edge.setBackground(EMPTY_BACKGROUND);
        return edge;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * OnlineTrades.create():
     * The method to create the JScrollPane that contains all the ActiveTrade graphics components.
     *
     * @author David Bulyaki
     * @return The JScrollPane containing all the ActiveTrade|s.
     * @see JScrollPane
     */
    public static JScrollPane create()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Load the ActiveTrades into the scroll-bar:
        JScrollPane scrollPane = new JScrollPane(loadOnlineTrades());
        scrollPane.setSize(TRADE_WIDTH + 20 ,MIN_APP_HEIGHT);
        // Use scrollPane.getViewport().setBackground to change background colour
        // within the scroll pane's excess area:
        scrollPane.getViewport().setBackground(EMPTY_BACKGROUND);
        // Alter the scrollBar properties:
        alterScrollBar(scrollPane, MAIN_ACCENT_COLOUR, DARK_GREY, SCROLL_BAR_WIDTH);
        // Alter the increment and decrement button steps:
        scrollPane.getVerticalScrollBar().setUnitIncrement(TRADE_HEIGHT);
        return scrollPane;
    }
}
