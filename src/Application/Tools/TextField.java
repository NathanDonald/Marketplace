//========//========//========//========
package Application.Tools;

//========//========//========//========//========//========
import static Application.Reference.Constants.*;
import static Application.Reference.Colours.*;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========
/**
 * TextField:
 * Custom text field class.
 * @see JTextField
 */
public class TextField extends JTextField
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public TextField(Integer fontSize)
    {
        this.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        this.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        this.setFont(getMainFont(fontSize));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public void enableTextField(boolean enabled)
    {
        Color foreground;
        Color background;

        if(enabled)
        {
            foreground = DEFAULT_TEXT_FIELD_FOREGROUND;
            background = DEFAULT_TEXT_FIELD_BACKGROUND;
        }
        else
        {
            foreground = MEDIUM_GREY;
            background = VERY_DARK_GREY;
        }

        setEditable(enabled);
        setForeground(foreground);
        setBackground(background);
    }
}
