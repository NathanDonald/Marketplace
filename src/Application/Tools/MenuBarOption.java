//========//========//========
package Application.Tools;

//========//========//========
import javax.swing.*;

//========//========//========//========//========//========//========//========//========
/** MenuBarOption:
 * The class used for the construction of a new JMenuBar option,
 * that would normally appear in the top menu of an application.
 * IE "File", "View" etc.
 *
 * It is also responsible for creating the JMenuBarItem|s such as ...
 * "File/Open", "File/Save", "File/Exit" etc.
 *
 * @author David Bulyaki
 */
public class MenuBarOption
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static JMenuBar MenuBar;
    public static String Name;
    public static MenuFeature[] Options;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MenuBarOption:
     * Class used for handling contents of the JMenuBar.
     * @param menuBar The JMenuBar being referenced for new JMenu JComponents to be loaded into.
     * @param name The name of the specific MenuBarOption as a String.
     * @param options The MenuFeature[] containing all the MenuFeature objects used to create all
     *                the options within the JMenu.
     * @see JMenuBar
     * @see MenuFeature
     */
    public MenuBarOption(JMenuBar menuBar, String name, MenuFeature[] options)
    {
        MenuBar = menuBar;
        Name = name;
        Options = options;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /** MenuBarOption.create():
     * The method call used to perform the actions required to specifically
     * load all the JMenuBarItem|s for a given JMenuBar.
     *
     * @author David Bulyaki
     * @see MenuFeature
     *
     */
    public void create()
    {
        //--------//--------//--------//--------//--------//--------//--------
        // Creates a menuBar, menu option:
        JMenu menu = new JMenu(Name);
        // Adds options within the menu selection:
        for(MenuFeature option : Options)
        {
            JMenuItem jmenuItem = new JMenuItem(option.Name);
            jmenuItem.addActionListener(option.ActionEvent);
            menu.add(jmenuItem);
        }

        //--------//--------//--------//--------//--------//--------
        // Add the File menu option to the top tab, menu bar:
        MenuBar.add(menu);
    }
}
