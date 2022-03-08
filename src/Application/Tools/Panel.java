//========//========//========//========
package Application.Tools;

//========//========//========//========//========
import static Application.Reference.Colours.*;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========//========//========
/**
 * Applications.Tools.Panel():
 * Class for consolidating common methods used for initialising
 * and manipulating JPanel Component|s.
 */
public class Panel
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Panel.create():
     * Creates a JPanel Component.
     *
     * @param panelWidth The width of the JPanel in pixels.
     * @param panelHeight The height of the JPanel in pixels.
     * @return JPanel
     */
    public static JPanel create(Integer panelWidth, Integer panelHeight)
    {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        panel.setMinimumSize(new Dimension(panelWidth, panelHeight));
        panel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        return panel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Panel.blockPanel():
     * Creates a JPanel Component that resembles a block.
     *
     * @param panelWidth The width of the JPanel in pixels.
     * @param panelHeight The height of the JPanel in pixels.
     * @return JPanel
     */
    public static JPanel blockPanel(Integer panelWidth, Integer panelHeight, Integer edgeThickness, Color colour)
    {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel panel = new JPanel();
        // Enable JComponent setBounds():
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        panel.setMinimumSize(new Dimension(panelWidth, panelHeight));
        panel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        panel.setBackground(colour);

        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel iconTrimLeftBorder = Panel.create(edgeThickness, panelHeight);
        iconTrimLeftBorder.setBackground(getDarkAccentColor(colour));
        iconTrimLeftBorder.setBounds(0, 0, edgeThickness, panelHeight);
        panel.add(iconTrimLeftBorder);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JPanel iconTrimBottomBorder = Panel.create(edgeThickness, panelHeight);
        iconTrimBottomBorder.setBackground(getDarkAccentColor(colour));
        iconTrimBottomBorder.setBounds(0, panelHeight-edgeThickness, panelWidth, edgeThickness);
        panel.add(iconTrimBottomBorder);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JPanel iconTrimRightBorder = Panel.create(edgeThickness, panelHeight);
        iconTrimRightBorder.setBackground(getLightAccentColor(colour));
        iconTrimRightBorder.setBounds(panelWidth-edgeThickness, 0, edgeThickness, panelHeight);
        panel.add(iconTrimRightBorder);

        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel iconTrimTopBorder = Panel.create(edgeThickness, panelHeight);
        iconTrimTopBorder.setBackground(getLightAccentColor(colour));
        iconTrimTopBorder.setBounds(0, 0, panelWidth, edgeThickness);
        panel.add(iconTrimTopBorder);

        //--------//--------
        return panel;
    }
}
