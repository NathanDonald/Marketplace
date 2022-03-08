//========//========//========
package Application.Tools;

//========//========//========//========//========//========
import javax.swing.plaf.basic.BasicScrollBarUI;
import static Application.Application.frame;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========//========
/**
 * GUITools:
 * Is a consolidation of convenient methods used to manipulate
 * aspects of the GUI.
 */
public class GUITools
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * GUITools.setFrameSize():
     * A simple, self-contained method to consolidate the JFrame methods
     * necessary for setting the JFrame size, a single, more intuitively-named
     * method call.
     *
     * @param dimension The desired size of the JFrame, of type Dimension.
     */
    public static void setFrameSize(Dimension dimension)
    {
        // Need to perform both of these actions to properly
        // set the desired screen size of the frame:
        frame.setPreferredSize(dimension);
        frame.setMinimumSize(dimension);
        frame.setSize(dimension);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * GUITools.alterScrollBar():
     * Method for overriding the default scroll bar properties of an
     * initialised JScrollPane.
     *
     * @param scrollPane The JScrollPane referenced for alteration.
     * @param thumbColour The designated Color selected for the scroll-bar's thumb.
     * @param trackColour The designated Color selected for the scroll-bar's track.
     * @param width The designated width of the scroll-bar (int).
     * @see JScrollPane
     * @see Color
     */
    public static void alterScrollBar(JScrollPane scrollPane, Color thumbColour, Color trackColour, int width)
    {
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI()
        {
            @Override
            protected void configureScrollBarColors()
            {
                this.thumbColor = thumbColour;
                this.trackColor = trackColour;
                this.scrollBarWidth = width;
            }
        });
    }
}
