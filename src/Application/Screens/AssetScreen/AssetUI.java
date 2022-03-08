//========//========//========//========//========//========
package Application.Screens.AssetScreen;

//========//========//========//========//========//========
import Application.Tools.TextField;
import Application.Tools.Button;
import static Application.Reference.Constants.*;
import javax.swing.event.ListSelectionListener;
import static Application.Reference.Colours.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========//========//========//========
/**
 * Initiates user interface for the asset application. All listeners for
 * the application are included as inner classes of this class.
 */
public class AssetUI extends JPanel
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private JList assetNameList;
    private TextField assetID;
    private TextField assetName;
    private TextField assetDescription;
    private TextField assetCategory;
    private TextField assetActive;
    public static Button newButton;
    public static Button saveButton;
    public static Button deleteButton;
    public static Button modifyButton;
    private JScrollPane scroller;
    private boolean modify = false;
    private static AssetData CurrentAssetData;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Constructor sets up user interface, adds listeners and displays.
     */
    public AssetUI()
    {
        CurrentAssetData = new AssetData();
        initUI();
        checkListSize();
        addAssetNameListListener(new AssetNameListListener());
        this.setVisible(true);
        this.setBackground(DEFAULT_WINDOW_COLOUR);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void fetchData()
    {
        CurrentAssetData = new AssetData();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Places the detail panel and the button panel in a box layout with vertical
     * alignment and puts a 20 pixel gap between the components and the top and
     * bottom edges of the frame.
     */
    private void initUI()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(makeDetailsPanel());
        this.add(Box.createVerticalStrut(20));
        this.add(makeButtonsPanel());
        this.add(Box.createVerticalStrut(20));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JPanel consisiting of (1) the list of assetNames and (2) the asset details
     * fields in a box layout with horizontal alignment and puts a 20 pixel gap
     * between the components and the left and right edges of the panel.
     *
     * @return the detail panel.
     */
    private JPanel makeDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeAssetNameListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeAssetDetailsFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));

        //--------//--------//--------//--------//--------//--------
        detailsPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return detailsPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshAssetNameList()
    {
        assetNameList = new JList(CurrentAssetData.getModel());
        // Ensure text properties are also refreshed:
        assetNameList.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        assetNameList.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        assetNameList.setFont(getMainFont(15));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshAssetNameScroller()
    {
        refreshAssetNameList();
        scroller.setViewportView(assetNameList);
        clearFields();
        addAssetNameListListener(new AssetNameListListener());
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JScrollPane that holds a JList for the list of assetNames in the
     * table.
     *
     * @return the scrolling AssetName list panel
     */
    private JScrollPane makeAssetNameListPane() {
        refreshAssetNameList();
        assetNameList.setFixedCellWidth(200);
        scroller = new JScrollPane(assetNameList);
        scroller.setViewportView(assetNameList);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));
        return scroller;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JPanel containing labels and text fields for each of the pieces of
     * data that are to be recorded for each asset. The labels and fields are
     * laid out using a GroupLayout, with the labels vertically grouped, the
     * fields vertically grouped and each label/group pair horizontally grouped.
     *
     * @return a panel containing the asset fields
     */
    private JPanel makeAssetDetailsFieldsPanel()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel assetPanel = new JPanel();
        GroupLayout layout = new GroupLayout(assetPanel);
        assetPanel.setLayout(layout);
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        //--------//--------//--------
        int fontHeight = 15;

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel assetIDLabel = new JLabel("Asset ID");
        assetIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetIDLabel.setFont(getMainFont(fontHeight));

        JLabel assetNameLabel = new JLabel("Asset Name");
        assetNameLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetNameLabel.setFont(getMainFont(fontHeight));

        JLabel assetDescriptionLabel = new JLabel("Asset Description");
        assetDescriptionLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetDescriptionLabel.setFont(getMainFont(fontHeight));

        JLabel assetCategoryLabel = new JLabel("Asset Category");
        assetCategoryLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetCategoryLabel.setFont(getMainFont(fontHeight));

        JLabel assetActiveLabel = new JLabel("Is Active");
        assetActiveLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetActiveLabel.setFont(getMainFont(fontHeight));

        //--------//--------//--------//--------//--------//--------
        assetID = new TextField(fontHeight);
        assetName = new TextField(fontHeight);
        assetDescription = new TextField(fontHeight);
        assetCategory = new TextField(fontHeight);
        assetActive = new TextField(fontHeight);
        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        hGroup.addGroup(layout.createParallelGroup().addComponent(assetIDLabel).addComponent(assetNameLabel)
                .addComponent(assetDescriptionLabel).addComponent(assetCategoryLabel).addComponent(
                        assetActiveLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(assetID).addComponent(assetName)
                .addComponent(assetDescription).addComponent(assetCategory).addComponent(assetActive)
        );
        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        // The sequential group contains five parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field etc. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetIDLabel).addComponent(assetID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetNameLabel).addComponent(assetName));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetDescriptionLabel).addComponent(assetDescription));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetCategoryLabel).addComponent(assetCategory));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetActiveLabel).addComponent(assetActive));
        layout.setVerticalGroup(vGroup);

        //--------//--------//--------//--------//--------//--------
        assetPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return assetPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Adds the New, Save and Delete buttons to a panel
     */
    private JPanel makeButtonsPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setSize(800, 200);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        int yButton = 20;
        int buttonWidth = 100;
        int buttonHeight = 30;

        newButton = new Button(buttonPanel,"New", newPressed());
        newButton.setBounds(100, yButton, buttonWidth, buttonHeight);

        saveButton = new Button(buttonPanel,"Save", savePressed());
        saveButton.setEnabled(false);
        saveButton.setBounds(220, yButton, buttonWidth, buttonHeight);

        deleteButton = new Button(buttonPanel,"Delete", deletePressed());
        deleteButton.setBounds(340, yButton, buttonWidth, buttonHeight);

        modifyButton = new Button(buttonPanel,"Modify", modifyPressed());
        modifyButton.setBounds(460, yButton, buttonWidth, buttonHeight);

        //--------//--------//--------//--------//--------//--------//--------
        buttonPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return buttonPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void disableButtons()
    {
        newButton.Button.setVisible(false);
        saveButton.Button.setVisible(false);
        modifyButton.Button.setVisible(false);
        deleteButton.Button.setVisible(false);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void enableButtons()
    {
        newButton.Button.setVisible(true);
        saveButton.Button.setVisible(true);
        modifyButton.Button.setVisible(true);
        deleteButton.Button.setVisible(true);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private ActionListener newPressed()
    {
        return e ->
        {
            modify = false;
            clearFields();
            setFieldsEditable(true);
            assetID.enableTextField(false);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private ActionListener savePressed()
    {
        return e ->
        {
            if (assetName.getText() != null && !assetName.getText().equals(CLEAR_TEXT) && !modify) {
                Asset a = new Asset(CurrentAssetData.getSize() + 1, assetName.getText(), assetDescription.getText(), assetCategory.getText(), Boolean.valueOf(assetActive.getText()));
                CurrentAssetData.add(a);
            } else if (assetName.getText() != null && !assetName.getText().equals(CLEAR_TEXT) && modify) {
                Asset a = CurrentAssetData.get(Integer.valueOf(assetID.getText()));
                int index = assetNameList.getSelectedIndex();

                a.setAssetName(assetName.getText());
                a.setAssetDescription(assetDescription.getText());
                a.setAssetCategory(assetCategory.getText());
                a.setAssetActive(Boolean.valueOf(assetActive.getText()));

                CurrentAssetData.modify(a, index, assetName.getText());
            }
            setFieldsEditable(false);
            saveButton.setEnabled(false);
            checkListSize();
            modify = false;
            refreshAssetNameScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private ActionListener deletePressed()
    {
        return e ->
        {
            modify = false;
            int index = assetNameList.getSelectedIndex();
            CurrentAssetData.remove(assetNameList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (CurrentAssetData.getSize() != 0) {
                    index = 0;
                }
            }
            assetNameList.setSelectedIndex(index);
            checkListSize();
            refreshAssetNameScroller();
        };
    }

    //>>>>>>>> //>>>>>>>> //>>>>>>>> //>>>>>>>> //>>>>>>>> //>>>>>>>>
    private ActionListener modifyPressed()
    {
        return e ->
        {
            modify = true;
            setFieldsEditable(true);
            assetID.enableTextField(false);
            assetID.setBackground(VERY_DARK_GREY);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Adds a listener to the assetName list
     */
    private void addAssetNameListListener(ListSelectionListener listener) {
        assetNameList.addListSelectionListener(listener);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets the text in the asset text fields to the empty string.
     */
    private void clearFields()
    {
        assetID.setText(CLEAR_TEXT);
        assetName.setText(CLEAR_TEXT);
        assetDescription.setText(CLEAR_TEXT);
        assetCategory.setText(CLEAR_TEXT);
        assetActive.setText(CLEAR_TEXT);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets whether or not the asset fields are editable.
     */
    private void setFieldsEditable(boolean enabled)
    {
        assetID.enableTextField(enabled);
        assetName.enableTextField(enabled);
        assetDescription.enableTextField(enabled);
        assetCategory.enableTextField(enabled);
        assetActive.enableTextField(enabled);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Displays the details of a Asset in the asset fields.
     * @param asset the Asset to display.
     */
    private void display(Asset asset) {
        if (asset != null) {
            assetID.setText(String.valueOf(asset.getAssetID()));
            assetName.setText(asset.getAssetName());
            assetDescription.setText(asset.getAssetDescription());
            assetCategory.setText(asset.getAssetCategory());
            assetActive.setText(String.valueOf(asset.getAssetActive()));
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Checks size of data/model and enables/disables the delete button
     *
     */
    private void checkListSize() {
        deleteButton.setEnabled(CurrentAssetData.getSize() != 0);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different assetName is selected from the list.
     */
    private class AssetNameListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */

        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            // If statement to prevent SQL exception when an item is deleted:
            if (assetNameList.getSelectedValue() != null
                    && !assetNameList.getSelectedValue().equals(CLEAR_TEXT))
            {
                modifyButton.setEnabled(true);
                setFieldsEditable(false);
                saveButton.setEnabled(false);
                modify = false;
                display(CurrentAssetData.get(assetNameList.getSelectedValue()));
            }
        }
    }
}

