//========//========//========//========
package Application.Tools;

//========//========//========//========//========//========//========
import static Application.Reference.Constants.*;
import static Application.Reference.Colours.*;
import Application.Screens.OnlineTrade.OnlineTrade;
import javax.swing.*;

//========//========//========//========//========//========//========//========//========//========
/**
 * OrderIcon:
 * Handles the appearance of the order details as they appear within OnlineTradeComponent.
 */
public class OrderIcon
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * OrderIcon.create():
     * The method used to create the JComponent representation of an Asset object.
     * @param onlineTrade The OnlineTrade being referenced for constructing the OrderIcon.
     * @return JPanel
     * @see OnlineTrade
     */
    public static JPanel create(OnlineTrade onlineTrade)
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JPanel orderIcon = Panel.blockPanel(ORDER_ICON_WIDTH, ORDER_ICON_HEIGHT + 20, ICON_EDGING, VERY_DARK_GREY);
        // Enable JComponent setBounds():
        orderIcon.setLayout(null);

        JPanel unitPricePanel = new JPanel();
        unitPricePanel.setLayout(null);
        unitPricePanel.setBounds(ICON_EDGING, ICON_EDGING, ORDER_ICON_WIDTH/3-ICON_EDGING, ORDER_ICON_HEIGHT-ICON_EDGING*2);
        unitPricePanel.setBackground(MEDIUM_GREY);

        JLabel unitPriceLabel = new JLabel("Unit Price");
        unitPriceLabel.setFont(getMainFont(10));
        unitPriceLabel.setBounds(10, 10, ORDER_ICON_WIDTH, 10);
        unitPricePanel.add(unitPriceLabel);

        JLabel unitPrice = new JLabel("$" + onlineTrade.getUnitPrice());
        unitPrice.setFont(getMainFont(30));
        unitPrice.setBounds(10, 25, ORDER_ICON_WIDTH, 30);
        unitPricePanel.add(unitPrice);

        orderIcon.add(unitPricePanel);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JLabel totalPriceLabel = new JLabel("Total Price");
        totalPriceLabel.setFont(getMainFont(10));
        totalPriceLabel.setBounds(100, 10, ORDER_ICON_WIDTH, 10);
        totalPriceLabel.setForeground(MAIN_ACCENT_COLOUR);
        orderIcon.add(totalPriceLabel);

        JLabel totalPrice = new JLabel("$" + onlineTrade.getUnitPrice() * onlineTrade.getQuantity());
        totalPrice.setFont(getMainFont(30));
        totalPrice.setBounds(100, 25, ORDER_ICON_WIDTH, 30);
        totalPrice.setForeground(MAIN_ACCENT_COLOUR);
        orderIcon.add(totalPrice);

        //--------//--------
        return orderIcon;
    }
}
