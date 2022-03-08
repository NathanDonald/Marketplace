//========//========//========//========
package Application.Tools;

//========//========//========//========//========//========
import static Application.Reference.Constants.*;
import static Application.Reference.Colours.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.*;
import javax.swing.*;

//========//========//========//========//========//========//========//========
/**
 * Button:
 * Simple class to assist with button creation within the GUI.
 */
public class Button
{
    public JButton Button;
    public String ButtonName;
    public ActionListener ButtonActionListener;
    public JPanel ButtonHighlight;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Button.create():
     * Method to consolidate methods used to create a JButton.
     * @param component The JComponent referenced for the button.
     * @param buttonName The name of the button, as a String.
     * @param actionListener The ActionListener associated with the button press.
     * @see JButton
     */
    public Button(JComponent component, String buttonName, ActionListener actionListener)
    {
        //--------//--------//--------//--------//--------//--------//--------//--------
        // Define features of custom Button:
        JButton button = new JButton(buttonName);
        button.addActionListener(actionListener);

        JPanel buttonHighlight = new JPanel();
        buttonHighlight.setBackground(BUTTON_HIGHLIGHT_COLOUR);
        buttonHighlight.setVisible(false);

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBackground(MAIN_ACCENT_COLOUR);
        button.setFont(getMainFont(15));
        button.setVisible(true);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Define custom MouseListener:
        button.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {

            }

            @Override
            public void mousePressed(MouseEvent e)
            {
                if(!Button.isEnabled())
                {
                    return;
                }

                button.setBackground(BUTTON_HIGHLIGHT_COLOUR);
            }

            @Override
            public void mouseReleased(MouseEvent e)
            {
                if(!Button.isEnabled())
                {
                    return;
                }

                button.setBackground(MAIN_ACCENT_COLOUR);
            }

            @Override
            public void mouseEntered(MouseEvent e)
            {
                if(!Button.isEnabled())
                {
                    return;
                }

                button.setBackground(BUTTON_HIGHLIGHT_COLOUR);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                if(!Button.isEnabled())
                {
                    return;
                }

                button.setBackground(MAIN_ACCENT_COLOUR);
            }
        });

        //--------//--------//--------//--------//--------//--------
        // Define Custom FocusListener:
        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e)
            {
                if(!Button.isEnabled())
                {
                    return;
                }

                //super.focusGained(e);
                Button.setBackground(BUTTON_HIGHLIGHT_COLOUR);
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if(!Button.isEnabled())
                {
                    return;
                }

                //super.focusGained(e);
                Button.setBackground(MAIN_ACCENT_COLOUR);
            }
        });

        //--------//--------//--------//--------//--------//--------
        // The JComponent associated with the Button:
        component.add(button);
        component.add(buttonHighlight);

        Button = button;
        ButtonName = buttonName;
        ButtonActionListener = actionListener;
        ButtonHighlight = buttonHighlight;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public void setEnabled(boolean enabled)
    {
        if(enabled)
        {
            Button.setBackground(MAIN_ACCENT_COLOUR);
            Button.setEnabled(true);
        }
        else
        {
            Button.setBackground(VERY_DARK_GREY);
            Button.setEnabled(false);
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * setBounds():
     * Custom setBounds() method used to configure the Button class.
     * @param x The x coordinate for the Button.
     * @param y THe y coordinate for the Button.
     * @param width The width of the Button.
     * @param height The height of the Button.
     */
    public void setBounds(Integer x, Integer y, Integer width, Integer height)
    {
        Button.setBounds(x, y, width, height);
        ButtonHighlight.setBounds(x-BUTTON_HIGHLIGHT_THICKNESS,
                Button.getY()-BUTTON_HIGHLIGHT_THICKNESS,
                Button.getWidth() + BUTTON_HIGHLIGHT_THICKNESS*2,
                Button.getHeight() + BUTTON_HIGHLIGHT_THICKNESS*2);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Buttons.arrange():
     * Method that allows for ease of vertical arrangement of buttons as they appear on the application.
     * The ArrayList<Button> allows for simple rearrangement by adding the Button objects in a different
     * order after being initialised.
     * @param buttons Hashtable<String, Button> containing Button objects. Key is the Button.ButtonName.
     * @param enabledButtons The ArrayList<String> containing the Hashtable Keys for the enabled buttons.
     * @param x The x offset for the horizontal arrangement of Button objects.
     * @param yStart The initial y offset for the vertical arrangement of Button objects.
     * @param yOffset The desired offset in height, between each Button object.
     * @param buttonWidth The nominated Button width, for all Button objects.
     * @param buttonHeight The nominated Button height, for all Button objects.
     * @see Button
     * @see Hashtable
     */
    public static void arrangeButtons(Hashtable<String, Button> buttons,
                                      ArrayList<String> enabledButtons,
                                      Integer x,
                                      Integer yStart,
                                      Integer yOffset,
                                      Integer buttonWidth,
                                      Integer buttonHeight)
    {
        int yNextOffset = yStart;
        // Iterate over the ArrayList<String> of User.PermissionLevel enabled buttons:
        for(String enabledButton: enabledButtons)
        {
            // Pick the buttons to use from the ArrayList<String> of enabledButtons:
            Button useButton = buttons.get(enabledButton);

            // Enable and configure useButton if the User.PermissionLevel allows for the button to be used:
            if(enabledButton.equals(useButton.ButtonName))
            {
                useButton.setBounds(x, yNextOffset, buttonWidth, buttonHeight);
                useButton.Button.setVisible(true);
                useButton.ButtonHighlight.setVisible(false);
                yNextOffset += yOffset;
            }
            else
            {
                // Hide from and do not configure the button for the User:
                useButton.Button.setVisible(false);
            }
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * deArrangeButtons():
     * @param buttons The Hashtable of containing the button objects belonging to
     *                an Application arrangement.
     */
    public static void deArrangeButtons(Hashtable<String, Button> buttons)
    {
        for(Button button: buttons.values())
        {
            button.Button.setVisible(false);
            button.ButtonHighlight.setVisible(false);
        }
    }
}
