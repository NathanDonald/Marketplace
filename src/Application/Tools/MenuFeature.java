//========//========//========
package Application.Tools;

//========//========//========//========
import java.awt.event.ActionListener;

//========//========//========//========//========//========//========//========
/**
 * MenuFeature:
 * The class used to conveniently configure the JMenuBar.
 * Part of this process involves an array of MenuFeature[] to be
 * parsed into MenuBarOption.create().
 *
 * @author David Bulyaki
 * @see Application.MenuBar.MenuBar
 * @see MenuBarOption
 */
public class MenuFeature
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Name:
     * The name of the MenuFeature as a String.
     */
    public String Name;

    /**
     * ActionEvent:
     * The ActionListener contained within a MenuFeature.
     */
    public ActionListener ActionEvent;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MenuFeature (Object):
     *
     * @author David Bulyaki
     * @param name The String corresponding to the name of the JMenuItem
     *             to be initialised.
     * @param actionEvent The ActionListener linked to the event associated
     *                    with the JMenuItem.
     *
     * @see Application.MenuBar.MenuBar
     * @see MenuBarOption
     */
    public MenuFeature(String name, ActionListener actionEvent)
    {
        this.Name = name;
        this.ActionEvent = actionEvent;
    }
}
