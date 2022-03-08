//========//========//========//========
package Application.SideBars;

//========//========//========//========//========//========//========
import static Application.Reference.Constants.*;
import static Application.Reference.Colours.*;
import Application.Tools.Panel;
import javax.swing.*;

//========//========//========//========//========//========//========
/**
 * RightBar:
 * Class to manage the content for the Application's Right bar.
 * @author David Bulyaki
 */
public class RightBar
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * LeftBar.create():
     * Method to create the loaded content of the RightBar.
     * @return JPanel configured with necessary features.
     */
    public static JPanel create()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel rightBar = Panel.create(LEFT_FEATURES_WIDTH, MIN_APP_HEIGHT);
        // Enable setBounds() of JComponents:
        rightBar.setLayout(null);
        rightBar.setBackground(DEFAULT_WINDOW_COLOUR);

        //--------//--------//--------
        return rightBar;
    }
}
