//========//========//========
package Application.MenuBar;

//========//========//========//========//========//========
import static Application.Tools.ActionListeners.*;
import static Application.Reference.Constants.*;
import static Application.Application.frame;
import Application.Tools.MenuBarOption;
import Application.Tools.MenuFeature;
import javax.swing.*;

//========//========//========//========//========//========//========//========//========//========//========//========//========
/**
 * MenuBar:
 * The class used to load all content for the JMenu.
 */
public class MenuBar
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    // Initialise instance of a JMenuBar that can be accessed by other classes:
    public static JMenuBar menuBar = new JMenuBar();

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MenuBar.create():
     * The method call used to perform the actions required to generate a
     * JMenuBar, along with all of its' associated JMenuBarItem|s.
     * Use of MenuFeature|s are key to constructing the JMenuBar content.
     *
     * @author David Bulyaki
     * @see MenuFeature
     */
    public static void create()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        // Initialise MenuBarOption for "File":
        MenuBarOption fileMenu = new MenuBarOption( menuBar,"File",
                new MenuFeature[]{  new MenuFeature("Log Out", selectScreen(VIEW_0_LOGIN_SCREEN, true)),
                        new MenuFeature("Exit", fileExit())});
        fileMenu.create();

        //--------//--------//--------//--------
        frame.setJMenuBar(menuBar);
    }
}
