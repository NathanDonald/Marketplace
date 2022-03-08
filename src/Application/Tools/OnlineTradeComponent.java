//========//========//========//========
package Application.Tools;

//========//========//========//========//========//========
import static Application.Reference.Colours.*;
import static Application.Reference.Constants.*;
import Application.Screens.AssetScreen.AssetData;
import Application.Screens.OnlineTrade.OnlineTrade;
import Application.Screens.AssetScreen.Asset;
import javax.swing.*;
import java.awt.*;
import java.util.Locale;

//========//========//========//========//========//========//========
/**
 * OnlineTradeComponent:
 * Is the JComponent representation of an OnlineTrade object.
 */
public class OnlineTradeComponent
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ActiveTrade.create():
     * The method call to construct the appearance and features
     * for an ActiveTrade graphics component.
     *
     * @author David Bulyaki
     * @param onlineTrade The OnlineTrade represented within the ActiveTrade JPanel.
     * @return box The fully constructed ActiveTrade graphics, contained within a Box.
     * @see OnlineTrade
     */
    public static Box create(OnlineTrade onlineTrade)
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Create a panel that will be laid underneath the main content, to serve as an edging:
        JPanel onlineTradeEdging = Panel.create(TRADE_WIDTH, TRADE_HEIGHT);
        onlineTradeEdging.setBackground(BLACK);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Create the main JPanel for the "Active Trade" graphics component.
        JPanel onlineTradePanel = Panel.create(TRADE_WIDTH-ONLINE_TRADE_EDGING*2, TRADE_HEIGHT-ONLINE_TRADE_EDGING);
        // Enable JComponents to setBounds():
        onlineTradePanel.setLayout(null);
        // Make the JPanel the opposite colour to the AssetIcon:
        AssetData assetData = new AssetData();
        Asset a = assetData.get(onlineTrade.getAssetIDOnlineTrade());

        onlineTradePanel.setBackground(getAccentColor(a.getAssetColor()));

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Create text label for trade dialogue in the "Active Trade" graphics component:
        JPanel buyOrSellPanel = Panel.blockPanel(140, 70, ICON_EDGING, VERY_DARK_GREY);
        buyOrSellPanel.setLayout(null);
        buyOrSellPanel.setBounds(15, 10, 140, 70);

        String buyOrSell;
        Color buyOrSellColour;
        Color buyOrSellFontColour;
        if(onlineTrade.getTradeType().toUpperCase(Locale.ROOT).equals(BUY_ORDER))
        {
            buyOrSell = "Buying";
            buyOrSellColour = GOLD;
            buyOrSellFontColour = BLACK;
        }
        else
        {
            buyOrSell = "Selling";
            buyOrSellColour = NICE_BLUE;
            buyOrSellFontColour = MEDIUM_GREY;
        }

        buyOrSellPanel.setBackground(buyOrSellColour);

        JLabel tradeType = new JLabel(buyOrSell);
        tradeType.setForeground(buyOrSellFontColour);
        tradeType.setFont(getMainFont(20));
        tradeType.setBounds(30, -5, ASSET_ICON_WIDTH, ASSET_ICON_HEIGHT);
        buyOrSellPanel.add(tradeType);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JLabel tradeQuantity = new JLabel(onlineTrade.getQuantity() + " x");
        tradeQuantity.setForeground(buyOrSellFontColour);
        tradeQuantity.setFont(getMainFont(30));
        tradeQuantity.setBounds(30, 25, ASSET_ICON_WIDTH/4, ASSET_ICON_HEIGHT);
        buyOrSellPanel.add(tradeQuantity);

        onlineTradePanel.add(buyOrSellPanel);

        //--------//--------//--------//--------//--------//--------//--------//--------
        // Create the AssetIcon for the "Active Trade" graphics component.
        JPanel assetIcon = AssetIcon.create(a);
        assetIcon.setBounds(180, 20, ASSET_ICON_WIDTH, ASSET_ICON_HEIGHT);
        onlineTradePanel.add(assetIcon);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Create the credits JPanel for the "Active Trade" graphics component.
        JPanel orderIcon = OrderIcon.create(onlineTrade);
        orderIcon.setBounds(460, 10, ORDER_ICON_WIDTH, ORDER_ICON_HEIGHT);
        onlineTradePanel.add(orderIcon);
        // Then contain it within another panel to create an edging effect:
        onlineTradeEdging.add(onlineTradePanel);

        //--------//--------//--------//--------//--------
        // Prepare the Box to return to the JScrollBar:
        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(onlineTradeEdging);
        return box;
    }
}
