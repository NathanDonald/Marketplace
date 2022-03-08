//========//========//========//========
package Application.Reference;

//========//========//========//========//========//========//========
import static Application.Reference.Constants.randInt;
import java.util.ArrayList;
import java.awt.*;

//========//========//========//========//========//========//========//========
/**
 * Colours:
 * The class used to store commonly used colours, with more options than
 * the default class Color.
 *
 * @author David Bulyaki
 * @see java.awt.Color
 */
public class Colours
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ELEMENT_MAX:
     * The maximum value of any defined, RGB element (255).
     */
    public final static Integer ELEMENT_MAX = 255;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MAX_RGB:
     * The defined, maximum value of any RGB element (225).
     */
    public final static Integer MAX_RGB = 225;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MIN_RGB:
     * The defined, minimum value of any RGB element (50).
     */
    public final static Integer MIN_RGB = 50;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ACCENT1:
     * The value for offsetting the RGB value of a colour.
     */
    public final static Integer ACCENT1 = ELEMENT_MAX - MAX_RGB;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * ACCENT2:
     * The value for offsetting the RGB value of a colour.
     */
    public final static Integer ACCENT2 = ELEMENT_MAX - MAX_RGB - 10;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MAX_ALPHA:
     * The maximum alpha value of the defined, custom Color types.
     * The alpha value refers to opacity.
     * 255 = Max Opacity.
     *   0 = Fully translucent / not visible.
     */
    public final static Integer MAX_ALPHA = 255;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * VERY_DARK_GREY:
     * Custom very dark-grey colour.
     */
    public final static Color VERY_DARK_GREY = new Color(50, 50, 50, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DARK_GREY:
     * Custom dark-grey colour.
     */
    public final static Color DARK_GREY = new Color(100, 100, 100, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DARK_GREY_ACCENT:
     * Custom dark-grey accent colour.
     */
    public final static Color DARK_GREY_ACCENT = new Color(110, 110, 110, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MEDIUM_GREY:
     * Custom medium-grey colour.
     */
    public final static Color MEDIUM_GREY = new Color(200, 200, 200, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MEDIUM_PASTEL_PURPLE:
     * Custom medium-pastel-purple colour.
     */
    public final static Color MEDIUM_PASTEL_PURPLE = new Color(128, 100, 162, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MEDIUM_DARK_PASTEL_PURPLE:
     * Custom medium-pastel-purple colour.
     */
    public final static Color MEDIUM_DARK_PASTEL_PURPLE = new Color(90, 72, 119, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DARK_PASTEL_PURPLE:
     * Custom dark-pastel-purple colour.
     */
    public final static Color DARK_PASTEL_PURPLE = new Color(51, 44, 75, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * GOLD:
     * Custom gold colour.
     */
    public final static Color GOLD = new Color(235, 158, 0, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * NICE_BLUE:
     * Custom blue colour.
     */
    public final static Color NICE_BLUE = new Color(0, 116, 196, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * BOLD_ORANGE:
     * Custom bold orange colour.
     */
    public final static Color BOLD_ORANGE = new Color(245, 108, 0, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * SUBDUED_ORANGE:
     * Custom subdued orange colour.
     */
    public final static Color SUBDUED_ORANGE = new Color(255, 132, 55, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * BLACK:
     * Custom black colour.
     */
    public final static Color BLACK = new Color(0, 0, 0, MAX_ALPHA);

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * accentColour():
     * Returns the new RGB element value, for a given offset.
     * Assists to make lighter colour accents for darker Colors.
     */
    public static int accentColour(Integer rgbElementValue)
    {
        int accentLevel;
        if(rgbElementValue >= MAX_RGB/2 && rgbElementValue <= MAX_RGB)
        {
            accentLevel = rgbElementValue - ACCENT1;
        }
        else
        {
            accentLevel = rgbElementValue + ACCENT1;
        }

        return accentLevel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * lightAccentColour():
     * Returns the new RGB element value, for a given offset.
     * Assists to make lighter colour accents for darker Colors.
     */
    public static int lightAccentColour(Integer rgbElementValue)
    {
        return rgbElementValue + ACCENT2;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * darkAccentColour():
     * Returns the new RGB element value, for a given offset.
     * Assists to make darker colour accents for lighter Colors.
     */
    public static int darkAccentColour(Integer rgbElementValue)
    {
        return rgbElementValue - ACCENT2;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * randomColour():
     * Method to generate a random colour as a String for type Asset.
     * @see Application.Screens.AssetScreen.Asset
     */
    public static String randomColour()
    {
        return randInt(MIN_RGB,MAX_RGB) + "_" + randInt(MIN_RGB,MAX_RGB) + "_" + randInt(MIN_RGB,MAX_RGB);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * QUITE_LIGHT:
     * The constant that defines when a Color is too light for white text foreground.
     */
    public final static Integer QUITE_LIGHT = 300;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     *invertColor():
     * Method for inverting the colour of the Asset.
     * @return Color
     * @see Color
     */
    public static Color invertColor(Color colour)
    {
        return new Color(   ELEMENT_MAX - colour.getRed(),
                ELEMENT_MAX - colour.getGreen(),
                ELEMENT_MAX - colour.getBlue(),
                MAX_ALPHA);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getAccentColor():
     * Returns a complementary colour, based off the original Asset Color.
     * @return Color
     * @see Color
     */
    public static Color getAccentColor(Color colour)
    {
        return new Color(   accentColour(colour.getRed()),
                accentColour(colour.getGreen()),
                accentColour(colour.getBlue()),
                MAX_ALPHA);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getLightAccentColor():
     * Returns a complementary accent colour, based off the original Asset Color.
     * @return Color
     * @see Color
     */
    public static Color getLightAccentColor(Color colour)
    {
        return new Color(   lightAccentColour(colour.getRed()),
                lightAccentColour(colour.getGreen()),
                lightAccentColour(colour.getBlue()),
                MAX_ALPHA);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * getDarkAccentColor():
     * Returns a complementary accent colour, based off the original Asset Color.
     * @return Color
     * @see Color
     */
    public static Color getDarkAccentColor(Color colour)
    {
        return new Color(   darkAccentColour(colour.getRed()),
                darkAccentColour(colour.getGreen()),
                darkAccentColour(colour.getBlue()),
                MAX_ALPHA);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * recommendForeground():
     * Returns the recommended text foreground colour for a given Color.
     * @return Color
     * @see Color
     */
    public static Color recommendedForeground(Color colour)
    {
        ArrayList<Integer> rgb = new ArrayList<>();
        rgb.add(colour.getRed());
        rgb.add(colour.getGreen());
        rgb.add(colour.getBlue());

        int brightness = 0;
        for (Integer element: rgb)
        {
            brightness += element;
        }

        if(brightness >= QUITE_LIGHT)
        {
            return Color.BLACK;
        }
        else
        {
            return Color.LIGHT_GRAY;
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * EMPTY_BACKGROUND:
     * Default GUI colour for an empty background.
     */
    public final static Color EMPTY_BACKGROUND = BLACK;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DEFAULT_TEXT_FIELD_FOREGROUND:
     * Default foreground colour for text fields.
     */
    public final static Color DEFAULT_TEXT_FIELD_FOREGROUND = BLACK;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DEFAULT_TEXT_FIELD_BACKGROUND:
     * Default background colour for text fields.
     */
    public final static Color DEFAULT_TEXT_FIELD_BACKGROUND = MEDIUM_GREY;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * MAIN_ACCENT_COLOUR:
     * Default accent colour for Application.
     */
    public final static Color MAIN_ACCENT_COLOUR = SUBDUED_ORANGE;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * BUTTON_HIGHLIGHT_COLOUR:
     * Default highlight colour for Button.
     */
    public final static Color BUTTON_HIGHLIGHT_COLOUR = MEDIUM_GREY;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * DEFAULT_WINDOW_COLOUR:
     * Default GUI colour for windows.
     */
    public final static Color DEFAULT_WINDOW_COLOUR = VERY_DARK_GREY;
}
