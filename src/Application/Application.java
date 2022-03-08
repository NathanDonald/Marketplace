//========//========//========
package Application;

//========//========//========//========//========//========
import static Application.Reference.Constants.*;
import java.security.NoSuchAlgorithmException;
import Application.Appearance.Appearance;
import Application.MenuBar.MenuBar;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========
/**
 * Application:
 * The class used to generate the application GUI.
 */
public class Application
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    // Initiate a JFrame instance that can be accessed by other classes:
    public static JFrame frame = new JFrame(APP_TITLE);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Application.create():
     * The method call to construct the Application appearance and features.
     *
     * @author David Bulyaki
     * @throws ClassNotFoundException Exception
     * @throws UnsupportedLookAndFeelException Exception
     * @throws InstantiationException Exception
     * @throws IllegalAccessException Exception
     */
    public static void create() throws
            ClassNotFoundException,
            UnsupportedLookAndFeelException,
            InstantiationException,
            IllegalAccessException, IOException, NoSuchAlgorithmException
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Set look and feel of application appearance:
        // Mimic that of native system:
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        //UIManager.put("Menu.font", new Font("sans-serif", Font.BOLD, 12));
        //--------//--------//--------//--------//--------//--------//--------
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Create the Application's general appearance:
        Appearance.create();
        // ADD FEATURES HERE: Create features before .pack() method is invoked:
        MenuBar.create();

        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Nicely fills the frame with an appropriate spacing for its given GUI components:
        // .pack() also fills the window to the specified dimensions:
        frame.pack();
        // Make GUI appear in the centre of the screen:
        frame.setLocationRelativeTo(null);
        // Allow window to be seen:
        frame.setVisible(true);
        // Set a different application icon, instead of default Java icon:
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(APP_ICON));
    }
}
