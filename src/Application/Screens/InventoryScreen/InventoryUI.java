package Application.Screens.InventoryScreen;
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

/**
 * Initiates user interface for the asset application. All listeners for
 * the application are included as inner classes of this class.
 *
 */

public class InventoryUI extends JPanel {

    private JList inventoryIDList;

    private TextField inventoryID;

    private TextField assetID;

    private TextField departmentID;

    private TextField quantity;

    private TextField inventoryActive;

    private static Button newButton;

    private static Button saveButton;

    private static Button deleteButton;

    public static Button modifyButton;

    private JScrollPane scroller;

    private boolean modify = false;

    private static InventoryData CurrentInventoryData;

    /**
     * Constructor sets up user interface, adds listeners and displays.
     */
    public InventoryUI() {
        CurrentInventoryData = new InventoryData();
        initUI();
        checkListSize();
        addInventoryIDListListener(new InventoryIDListListener());
        this.setVisible(true);
        this.setBackground(DEFAULT_WINDOW_COLOUR);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void fetchData()
    {
        CurrentInventoryData = new InventoryData();
    }

    /**
     * Places the detail panel and the button panel in a box layout with vertical
     * alignment and puts a 20 pixel gap between the components and the top and
     * bottom edges of the frame.
     */
    private void initUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(makeDetailsPanel());
        this.add(Box.createVerticalStrut(20));
        this.add(makeButtonsPanel());
        this.add(Box.createVerticalStrut(20));
    }

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
        detailsPanel.add(makeInventoryIDListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeInventoryDetailsFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));

        //--------//--------//--------//--------//--------//--------
        detailsPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return detailsPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshInventoryIDList()
    {
        inventoryIDList = new JList(CurrentInventoryData.getModel());
        // Ensure text properties are also refreshed:
        inventoryIDList.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        inventoryIDList.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        inventoryIDList.setFont(getMainFont(15));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshInventoryIDScroller()
    {
        refreshInventoryIDList();
        scroller.setViewportView(inventoryIDList);
        clearFields();
        addInventoryIDListListener(new InventoryIDListListener());
    }

    /**
     * Makes a JScrollPane that holds a JList for the list of InventoryIDs in the
     * table.
     *
     * @return the scrolling InventoryID list panel
     */
    private JScrollPane makeInventoryIDListPane() {
        refreshInventoryIDList();
        inventoryIDList.setFixedCellWidth(200);
        scroller = new JScrollPane(inventoryIDList);
        scroller.setViewportView(inventoryIDList);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));
        return scroller;
    }

    /**
     * Makes a JPanel containing labels and text fields for each of the pieces of
     * data that are to be recorded for each asset. The labels and fields are
     * laid out using a GroupLayout, with the labels vertically grouped, the
     * fields vertically grouped and each label/group pair horizontally grouped.
     *
     * @return a panel containing the inventory fields
     */
    private JPanel makeInventoryDetailsFieldsPanel() {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel inventoryPanel = new JPanel();
        GroupLayout layout = new GroupLayout(inventoryPanel);
        inventoryPanel.setLayout(layout);
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        //--------//--------//--------
        int fontHeight = 15;

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel inventoryIDLabel = new JLabel("Inventory ID");
        inventoryIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        inventoryIDLabel.setFont(getMainFont(fontHeight));

        JLabel assetIDLabel = new JLabel("Asset ID");
        assetIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetIDLabel.setFont(getMainFont(fontHeight));

        JLabel departmentIDLabel = new JLabel("Department ID");
        departmentIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentIDLabel.setFont(getMainFont(fontHeight));

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setForeground(MAIN_ACCENT_COLOUR);
        quantityLabel.setFont(getMainFont(fontHeight));

        JLabel inventoryActiveLabel = new JLabel("Inventory Active");
        inventoryActiveLabel.setForeground(MAIN_ACCENT_COLOUR);
        inventoryActiveLabel.setFont(getMainFont(fontHeight));

        //--------//--------//--------//--------//--------//--------
        inventoryID = new TextField(fontHeight);
        assetID = new TextField(fontHeight);
        departmentID = new TextField(fontHeight);
        quantity = new TextField(fontHeight);
        inventoryActive = new TextField(fontHeight);
        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        hGroup.addGroup(layout.createParallelGroup().addComponent(inventoryIDLabel).addComponent(assetIDLabel)
                .addComponent(departmentIDLabel).addComponent(quantityLabel).addComponent(
                        inventoryActiveLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(inventoryID).addComponent(assetID)
                .addComponent(departmentID).addComponent(quantity).addComponent(inventoryActive)
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
                .addComponent(inventoryIDLabel).addComponent(inventoryID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetIDLabel).addComponent(assetID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentIDLabel).addComponent(departmentID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(quantityLabel).addComponent(quantity));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(inventoryActiveLabel).addComponent(inventoryActive));
        layout.setVerticalGroup(vGroup);

        //--------//--------//--------//--------//--------//--------
        inventoryPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return inventoryPanel;
    }

    /**
     * Adds the New, Save and Delete buttons to a panel
     */
    private JPanel makeButtonsPanel() {
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
            inventoryID.enableTextField(false);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private ActionListener savePressed()
    {
        return e ->
        {
            if (!modify) {

                Inventory a = new Inventory(CurrentInventoryData.getSize() + 1, Integer.valueOf(assetID.getText()),
                        Integer.valueOf(departmentID.getText()), Integer.valueOf(quantity.getText()),
                        Boolean.valueOf(inventoryActive.getText()));
                CurrentInventoryData.add(a);

            } else if (modify) {
                Inventory a = CurrentInventoryData.get(Integer.valueOf(inventoryID.getText()));
                int index = inventoryIDList.getSelectedIndex();

                a.setAssetID(Integer.valueOf(assetID.getText()));
                a.setDepartmentID(Integer.valueOf(departmentID.getText()));
                a.setQuantity(Integer.valueOf(quantity.getText()));
                a.setInventoryActive(Boolean.valueOf(inventoryActive.getText()));

                CurrentInventoryData.modify(a, index, Integer.valueOf(inventoryID.getText()));
            }
            setFieldsEditable(false);
            saveButton.setEnabled(false);
            checkListSize();
            modify = false;
            refreshInventoryIDScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private ActionListener deletePressed()
    {
        return e ->
        {
            modify = false;
            int index = inventoryIDList.getSelectedIndex();
            CurrentInventoryData.remove(inventoryIDList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (CurrentInventoryData.getSize() != 0) {
                    index = 0;
                }
            }
            inventoryIDList.setSelectedIndex(index);
            checkListSize();
            refreshInventoryIDScroller();
        };
    }

    //>>>>>>>> //>>>>>>>> //>>>>>>>> //>>>>>>>> //>>>>>>>> //>>>>>>>>
    private ActionListener modifyPressed()
    {
        return e ->
        {
            modify = true;
            setFieldsEditable(true);
            inventoryID.enableTextField(false);
            inventoryID.setBackground(VERY_DARK_GREY);
            saveButton.setEnabled(true);
        };
    }

    /**
     * Adds a listener to the inventoryID list
     */
    private void addInventoryIDListListener(ListSelectionListener listener) {
        inventoryIDList.addListSelectionListener(listener);
    }

    /**
     * Sets the text in the asset text fields to the empty string.
     */
    private void clearFields() {
        inventoryID.setText(CLEAR_TEXT);
        assetID.setText(CLEAR_TEXT);
        departmentID.setText(CLEAR_TEXT);
        quantity.setText(CLEAR_TEXT);
        inventoryActive.setText(CLEAR_TEXT);
    }

    /**
     * Sets whether or not the inventory fields are editable.
     */
    private void setFieldsEditable(boolean editable) {
        inventoryID.enableTextField(editable);
        assetID.enableTextField(editable);
        departmentID.enableTextField(editable);
        quantity.enableTextField(editable);
        inventoryActive.enableTextField(editable);
    }

    /**
     * Displays the details of a Inventory in the inventory fields.
     * @param inventory the Inventory to display.
     */
    private void display(Inventory inventory) {
        if (inventory != null) {
            inventoryID.setText(String.valueOf(inventory.getInventoryID()));
            assetID.setText(String.valueOf(inventory.getAssetID()));
            departmentID.setText(String.valueOf(inventory.getDepartmentID()));
            quantity.setText(String.valueOf(inventory.getQuantity()));
            inventoryActive.setText(String.valueOf(inventory.getInventoryActive()));
        }
    }

    /**
     * Checks size of data/model and enables/disables the delete button
     *
     */
    private void checkListSize() {
        deleteButton.setEnabled(CurrentInventoryData.getSize() != 0);
    }

    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different inventoryID is selected from the list.
     */
    private class InventoryIDListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            if (inventoryIDList.getSelectedValue() != null
                    && !inventoryIDList.getSelectedValue().equals("")) {
                modifyButton.setEnabled(true);
                setFieldsEditable(false);
                saveButton.setEnabled(false);
                modify = false;
                display(CurrentInventoryData.get(inventoryIDList.getSelectedValue()));
            }
        }
    }
}
