package Application.Screens.OnlineTrade;

import static Application.Reference.Constants.CLEAR_TEXT;
import static Application.Reference.Constants.getMainFont;
import javax.swing.event.ListSelectionListener;
import static Application.Reference.Colours.*;
import java.security.NoSuchAlgorithmException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.GroupLayout.Alignment;
import Application.Tools.TextField;
import Application.Tools.Button;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * Initiates user interface for the asset application. All listeners for
 * the application are included as inner classes of this class.
 *
 */

public class OnlineTradeUI extends JPanel {

    public JList onlineTradeNameList;

    public TextField onlineTradeID;

    public TextField tradeType;

    public TextField userID;

    public TextField assetID;

    public TextField departmentID;

    public TextField quantity;

    public TextField unitPrice;

    public TextField orderDate;

    public TextField orderStatus;

    public static Button newButton;

    public static Button saveButton;

    public static Button deleteButton;

    public static Button modifyButton;

    private JScrollPane scroller;

    private boolean modify = false;

    private static OnlineTradeData CurrentOnlineTradeData;

    /**
     * Constructor sets up user interface, adds listeners and displays.
     *
     */
    public OnlineTradeUI() throws NoSuchAlgorithmException{
        CurrentOnlineTradeData = new OnlineTradeData();
        initUI();
        checkListSize();
        addOnlineTradeNameListListener(new OnlineTradeNameListListener());
        this.setVisible(true);
        this.setBackground(DEFAULT_WINDOW_COLOUR);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void fetchData()
    {
        CurrentOnlineTradeData = new OnlineTradeData();
    }

    /**
     * Places the detail panel and the button panel in a box layout with vertical
     * alignment and puts a 20 pixel gap between the components and the top and
     * bottom edges of the frame.
     */
    private void initUI() throws NoSuchAlgorithmException{
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
        detailsPanel.add(makeOnlineTradeNameListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeOnlineTradeDetailsFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));

        //--------//--------//--------//--------//--------//--------
        detailsPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return detailsPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshOnlineTradeNameList()
    {
        onlineTradeNameList = new JList(CurrentOnlineTradeData.getModel());
        // Ensure text properties are also refreshed:
        onlineTradeNameList.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        onlineTradeNameList.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        onlineTradeNameList.setFont(getMainFont(15));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshOnlineTradeScroller()
    {
        refreshOnlineTradeNameList();
        scroller.setViewportView(onlineTradeNameList);
        clearFields();
        addOnlineTradeNameListListener(new OnlineTradeUI.OnlineTradeNameListListener());
    }

    /**
     * Makes a JScrollPane that holds a JList for the list of assetNames in the
     * table.
     *
     * @return the scrolling AssetName list panel
     */
    private JScrollPane makeOnlineTradeNameListPane() {
        refreshOnlineTradeNameList();
        onlineTradeNameList.setFixedCellWidth(200);
        scroller = new JScrollPane(onlineTradeNameList);
        scroller.setViewportView(onlineTradeNameList);
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
     * @return a panel containing the asset fields
     */
    private JPanel makeOnlineTradeDetailsFieldsPanel() {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel onlineTradePanel = new JPanel();
        GroupLayout layout = new GroupLayout(onlineTradePanel);
        onlineTradePanel.setLayout(layout);
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        //--------//--------//--------
        int fontHeight = 15;

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel onlineTradeIDLabel = new JLabel("Online Trade ID");
        onlineTradeIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        onlineTradeIDLabel.setFont(getMainFont(fontHeight));

        JLabel tradeTypeLabel = new JLabel("Trade Type");
        tradeTypeLabel.setForeground(MAIN_ACCENT_COLOUR);
        tradeTypeLabel.setFont(getMainFont(fontHeight));

        JLabel userIDLabel = new JLabel("User ID");
        userIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        userIDLabel.setFont(getMainFont(fontHeight));

        JLabel assetIDLabel = new JLabel("Asset ID");
        assetIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        assetIDLabel.setFont(getMainFont(fontHeight));

        JLabel departmentIDLabel = new JLabel("Department ID");
        departmentIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentIDLabel.setFont(getMainFont(fontHeight));

        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setForeground(MAIN_ACCENT_COLOUR);
        quantityLabel.setFont(getMainFont(fontHeight));

        JLabel unitPriceLabel = new JLabel("Unit Price");
        unitPriceLabel.setForeground(MAIN_ACCENT_COLOUR);
        unitPriceLabel.setFont(getMainFont(fontHeight));

        JLabel orderDateLabel = new JLabel("Order Date");
        orderDateLabel.setForeground(MAIN_ACCENT_COLOUR);
        orderDateLabel.setFont(getMainFont(fontHeight));

        JLabel orderStatusLabel = new JLabel("Order Status");
        orderStatusLabel.setForeground(MAIN_ACCENT_COLOUR);
        orderStatusLabel.setFont(getMainFont(fontHeight));

        //--------//--------//--------//--------//--------//--------//--------//--------
        onlineTradeID = new TextField(fontHeight);
        tradeType = new TextField(fontHeight);
        userID = new TextField(fontHeight);
        assetID = new TextField(fontHeight);
        departmentID = new TextField(fontHeight);
        quantity = new TextField(fontHeight);
        unitPrice = new TextField(fontHeight);
        orderDate = new TextField(fontHeight);
        orderStatus = new TextField(fontHeight);
        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        hGroup.addGroup(layout.createParallelGroup().addComponent(onlineTradeIDLabel).addComponent(tradeTypeLabel)
                .addComponent(userIDLabel).addComponent(assetIDLabel).addComponent(departmentIDLabel)
                .addComponent(quantityLabel).addComponent(unitPriceLabel).addComponent(orderDateLabel)
                .addComponent(orderStatusLabel));

        hGroup.addGroup(layout.createParallelGroup().addComponent(onlineTradeID).addComponent(tradeType)
                .addComponent(userID).addComponent(assetID).addComponent(departmentID).addComponent(quantity)
                .addComponent(unitPrice).addComponent(orderDate).addComponent(orderStatus));

        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        // The sequential group contains five parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field etc. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(onlineTradeIDLabel).addComponent(onlineTradeID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(tradeTypeLabel).addComponent(tradeType));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(userIDLabel).addComponent(userID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(assetIDLabel).addComponent(assetID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentIDLabel).addComponent(departmentID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(quantityLabel).addComponent(quantity));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(unitPriceLabel).addComponent(unitPrice));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(orderDateLabel).addComponent(orderDate));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(orderStatusLabel).addComponent(orderStatus));
        layout.setVerticalGroup(vGroup);

        //--------//--------//--------//--------//--------//--------//--------
        onlineTradePanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return onlineTradePanel;
    }

    /**
     * Adds the New, Save and Delete buttons to a panel
     */
    private JPanel makeButtonsPanel() throws NoSuchAlgorithmException {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setSize(800, 200);

        //--------//--------//--------//--------//--------//--------//--------//--------//--------
        int yButton = 20;
        int buttonWidth = 100;
        int buttonHeight = 30;

        //--------//--------//--------//--------//--------//--------//--------//--------//--------//--------//--------
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

    public static void disableButtons()
    {
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

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * When the new button is pressed, clear the field display, make them
     * editable and enable the save button.
     */
    private ActionListener newPressed()
    {
        return e ->
        {
            modify = false;
            clearFields();
            setFieldsEditable(true);
            onlineTradeID.enableTextField(false);
            orderDate.enableTextField(false);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * When the save button is pressed, checks if the name fields are not empty and the change password/modify buttons weren't pressed.
     * If this is true, create a new User object and attempt to add it
     * to the data model. Change the fields back to not editable and make the
     * save button inactive.
     *
     * If the change password button was pressed beforehand, attempts to save the changes typed into the fields to the
     * database. If the modify button was pressed beforehand, attempts to save the change to the password to the database.
     *
     * Check the list size to see if the delete button should be enabled.
     */
    private ActionListener savePressed() throws NoSuchAlgorithmException
    {
        return event ->
        {
            if (tradeType.getText() != null && !tradeType.getText().equals("") && assetID.getText() != null &&
                    !assetID.getText().equals("") && quantity.getText() != null && !quantity.getText().equals("") &&
                    unitPrice.getText() != null && !unitPrice.getText().equals("") && !modify) {

                OnlineTrade a = new OnlineTrade(CurrentOnlineTradeData.getSize() + 1, tradeType.getText(),
                        Integer.valueOf(userID.getText()), Integer.valueOf(assetID.getText()),
                        Integer.valueOf(departmentID.getText()), Integer.valueOf(quantity.getText()),
                        Integer.valueOf(unitPrice.getText()), Integer.valueOf(orderStatus.getText()));
                CurrentOnlineTradeData.add(a);

            } else if (tradeType.getText() != null && !tradeType.getText().equals("") && assetID.getText() != null &&
                    !assetID.getText().equals("") && quantity.getText() != null && !quantity.getText().equals("") &&
                    unitPrice.getText() != null && !unitPrice.getText().equals("") && modify) {

                OnlineTrade a = CurrentOnlineTradeData.get(Integer.valueOf(onlineTradeID.getText()));
                int index = onlineTradeNameList.getSelectedIndex();

                a.setTradeType(tradeType.getText());
                a.setUserIDOnlineTrade(Integer.valueOf(userID.getText()));
                a.setAssetIDOnlineTrade(Integer.valueOf(assetID.getText()));
                a.setDepartmentIDOnlineTrade(Integer.valueOf(departmentID.getText()));
                a.setQuantity(Integer.valueOf(quantity.getText()));
                a.setUnitPrice(Integer.valueOf(unitPrice.getText()));
                a.setOrderStatus(Integer.valueOf(orderStatus.getText()));

                String onlineTradeName = onlineTradeID.getText() + " " + tradeType.getText() + " " + assetID.getText() + " " + quantity.getText() + " " + unitPrice.getText();
                CurrentOnlineTradeData.modify(a, index, onlineTradeName);
            }
            setFieldsEditable(false);
            saveButton.setEnabled(false);
            checkListSize();
            modify = false;
            refreshOnlineTradeScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * When the delete button is pressed remove the selected onlineTradeName from the
     * data model.
     *
     * Clear the fields that were displayed and check to see if the delete
     * button should be displayed.
     *
     * The index here handles cases where the first element of the list is
     * deleted.
     */
    private ActionListener deletePressed()
    {
        return e ->
        {
            modify = false;
            int index = onlineTradeNameList.getSelectedIndex();
            //Getting userID out of userNameList selection

            CurrentOnlineTradeData.remove(onlineTradeNameList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (CurrentOnlineTradeData.getSize() != 0) {
                    index = 0;
                }
            }
            onlineTradeNameList.setSelectedIndex(index);
            checkListSize();
            refreshOnlineTradeScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets up all the fields and save button for modifying the user data minus the password and userID fields.
     */
    private ActionListener modifyPressed()
    {
        return e ->
        {
            modify = true;
            setFieldsEditable(true);
            onlineTradeID.enableTextField(false);
            orderDate.enableTextField(false);
            saveButton.setEnabled(true);
        };
    }

    /**
     * Adds a listener to the assetName list
     */
    private void addOnlineTradeNameListListener(ListSelectionListener listener) {
        onlineTradeNameList.addListSelectionListener(listener);
    }

    /**
     * Sets the text in the online trade text fields to the empty string.
     */
    private void clearFields() {
        onlineTradeID.setText(CLEAR_TEXT);
        tradeType.setText(CLEAR_TEXT);
        userID.setText(CLEAR_TEXT);
        assetID.setText(CLEAR_TEXT);
        departmentID.setText(CLEAR_TEXT);
        quantity.setText(CLEAR_TEXT);
        unitPrice.setText(CLEAR_TEXT);
        orderDate.setText(CLEAR_TEXT);
        orderStatus.setText(CLEAR_TEXT);
    }

    /**
     * Sets whether or not the online trade fields are editable.
     */
    private void setFieldsEditable(boolean editable) {
        onlineTradeID.enableTextField(editable);
        tradeType.enableTextField(editable);
        userID.enableTextField(editable);
        assetID.enableTextField(editable);
        departmentID.enableTextField(editable);
        quantity.enableTextField(editable);
        unitPrice.enableTextField(editable);
        orderStatus.enableTextField(editable);
        orderDate.enableTextField(editable);
    }

    /**
     * Displays the details of a OnlineTrade in the online trade fields.
     *
     * @param onlineTrade the OnlineTrade to display.
     */
    private void display(OnlineTrade onlineTrade) {
        if (onlineTrade != null) {
            onlineTradeID.setText(String.valueOf(onlineTrade.getOnlineTradeID()));
            tradeType.setText(onlineTrade.getTradeType());
            userID.setText(String.valueOf(onlineTrade.getUserIDOnlineTrade()));
            assetID.setText(String.valueOf(onlineTrade.getAssetIDOnlineTrade()));
            departmentID.setText(String.valueOf(onlineTrade.getDepartmentIDOnlineTrade()));
            quantity.setText(String.valueOf(onlineTrade.getQuantity()));
            unitPrice.setText(String.valueOf(onlineTrade.getUnitPrice()));
            orderDate.setText(String.valueOf(onlineTrade.getOrderDate()));
            orderStatus.setText(String.valueOf(onlineTrade.getOrderStatus()));
        }
    }

    /**
     * Checks size of data/model and enables/disables the delete button
     */
    private void checkListSize() {
        deleteButton.setEnabled(CurrentOnlineTradeData.getSize() != 0);
    }

    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different onlineTradeName is selected from the list.
     */
    private class OnlineTradeNameListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            if (onlineTradeNameList.getSelectedValue() != null
                    && !onlineTradeNameList.getSelectedValue().equals("")) {
                modifyButton.setEnabled(true);
                setFieldsEditable(false);
                saveButton.setEnabled(false);
                modify = false;

                //Getting onlineTradeID out of onlineTradeNameList selection
                String onlineTradeName = onlineTradeNameList.getSelectedValue().toString();
                Integer onlineTradeID = Integer.parseInt(onlineTradeName.replaceAll(" .*", ""));

                display(CurrentOnlineTradeData.get(onlineTradeID));
            }
        }
    }
}
