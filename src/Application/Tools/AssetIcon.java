//========//========//========//========
package Application.Tools;

//========//========//========//========//========//========
import static Application.Reference.Colours.*;
import static Application.Reference.Constants.*;
import Application.Screens.AssetScreen.Asset;
import javax.swing.*;

//========//========//========//========//========//========//========//========//========
/**
 * AssetIcon:
 * The class used to handle the appearance of an Asset as represented by JComponents.
 *
 * @author David Bulyaki
 */
public class AssetIcon
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * AssetIcon.create():
     * The method used to create the JComponent representation of an Asset object.
     * @param asset The asset being referenced for constructing the AssetIcon.
     * @return JPanel
     * @see Asset
     */
    public static JPanel create(Asset asset)
    {
        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JPanel icon = Panel.blockPanel(ASSET_ICON_WIDTH, ASSET_ICON_HEIGHT, ICON_EDGING, asset.getAssetColor());

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
        JLabel iconLabel = new JLabel(asset.getAssetName());
        int size = 20;
        iconLabel.setFont(getMainFont(size));
        iconLabel.setForeground(recommendedForeground(asset.getAssetColor()));
        iconLabel.setBounds(size, ASSET_ICON_HEIGHT/3, ASSET_ICON_WIDTH, size);
        icon.add(iconLabel);

        //--------//--------
        return  icon;
    }
}
