//========//========//========//========//========//========
package Application.Screens.DepartmentScreen;

//========//========//========//========//========//========
import static Application.Reference.Constants.CLEAR_TEXT;
import static Application.Reference.Constants.getMainFont;
import Application.Tools.TextField;
import Application.Tools.Button;
import javax.swing.event.ListSelectionListener;
import static Application.Reference.Colours.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========//========//========//========
/**
 * Initiates user interface for the department application. All listeners for
 * the application are included as inner classes of this class.
 */
public class DepartmentUI extends JPanel
{
    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private static DepartmentData CurrentDepartmentData;
    private TextField departmentManager;
    private TextField departmentCredits;
    private TextField departmentActive;
    private TextField departmentName;
    private TextField departmentID;
    private JList departmentIDList;
    public static Button deleteButton;
    public static Button saveButton;
    public static Button newButton;
    private JScrollPane scroller;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Constructor sets up user interface, adds listeners and displays.
     */
    public DepartmentUI()
    {
        CurrentDepartmentData = new DepartmentData();
        initUI();
        checkListSize();
        addDepartmentIDListListener(new DepartmentIDListListener());
        this.setVisible(true);
        this.setBackground(DEFAULT_WINDOW_COLOUR);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void fetchData()
    {
        CurrentDepartmentData = new DepartmentData();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
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

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JPanel consisiting of (1) the list of departmentIDs and (2) the departmentName
     * fields in a box layout with horizontal alignment and puts a 20 pixel gap
     * between the components and the left and right edges of the panel.
     *
     * @return the detail panel.
     */
    private JPanel makeDetailsPanel()
    {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeDepartmentIDListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeDepartmentDetailsFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));

        //--------//--------//--------//--------//--------//--------
        detailsPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return detailsPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshDepartmentIDList()
    {
        departmentIDList = new JList(CurrentDepartmentData.getModel());
        // Ensure text properties are also refreshed:
        departmentIDList.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        departmentIDList.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        departmentIDList.setFont(getMainFont(15));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshDepartmentIDScroller()
    {
        refreshDepartmentIDList();
        scroller.setViewportView(departmentIDList);
        clearFields();
        addDepartmentIDListListener(new DepartmentUI.DepartmentIDListListener());
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JScrollPane that holds a JList for the list of departmentIDs in the
     * table.
     *
     * @return the scrolling departmentID list panel
     */
    private JScrollPane makeDepartmentIDListPane()
    {
        refreshDepartmentIDList();
        departmentIDList.setFixedCellWidth(200);
        scroller = new JScrollPane(departmentIDList);
        scroller.setViewportView(departmentIDList);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));
        return scroller;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JPanel containing labels and textfields for each of the pieces of
     * data that are to be recorded for each department. The labels and fields are
     * layed out using a GroupLayout, with the labels vertically grouped, the
     * fields vertically grouped and each label/group pair horizontally grouped.
     *
     * @return a panel containing the department fields
     */
    private JPanel makeDepartmentDetailsFieldsPanel()
    {
        JPanel departmentPanel = new JPanel();
        GroupLayout layout = new GroupLayout(departmentPanel);
        departmentPanel.setLayout(layout);
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        //--------//--------//--------
        int fontHeight = 15;

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel departmentIDLabel = new JLabel("Department ID");
        departmentIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentIDLabel.setFont(getMainFont(fontHeight));

        JLabel departmentNameLabel = new JLabel("Department Name");
        departmentNameLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentNameLabel.setFont(getMainFont(fontHeight));

        JLabel departmentManagerLabel = new JLabel("Department Manager");
        departmentManagerLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentManagerLabel.setFont(getMainFont(fontHeight));

        JLabel departmentCreditsLabel = new JLabel("Credits");
        departmentCreditsLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentCreditsLabel.setFont(getMainFont(fontHeight));

        JLabel departmentActiveLabel = new JLabel("Is Active");
        departmentActiveLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentActiveLabel.setFont(getMainFont(fontHeight));

        //--------//--------//--------//--------//--------//--------
        departmentID = new TextField(fontHeight);
        departmentName = new TextField(fontHeight);
        departmentManager = new TextField(fontHeight);
        departmentCredits = new TextField(fontHeight);
        departmentActive = new TextField(fontHeight);
        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        hGroup.addGroup(layout.createParallelGroup().addComponent(departmentIDLabel).addComponent(departmentNameLabel)
                .addComponent(departmentManagerLabel).addComponent(departmentCreditsLabel).addComponent(
                        departmentActiveLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(departmentID).addComponent(departmentName)
                .addComponent(departmentManager).addComponent(departmentCredits).addComponent(departmentActive)
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
                .addComponent(departmentIDLabel).addComponent(departmentID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentNameLabel).addComponent(departmentName));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentManagerLabel).addComponent(departmentManager));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentCreditsLabel).addComponent(departmentCredits));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentActiveLabel).addComponent(departmentActive));
        layout.setVerticalGroup(vGroup);

        //--------//--------//--------//--------//--------//--------
        departmentPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return departmentPanel;
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

        //--------//--------//--------//--------//--------//--------//--------
        buttonPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return buttonPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void disableButtons()
    {
        newButton.Button.setVisible(false);
        saveButton.Button.setVisible(false);
        deleteButton.Button.setVisible(false);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void enableButtons()
    {
        newButton.Button.setVisible(true);
        saveButton.Button.setVisible(true);
        deleteButton.Button.setVisible(true);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Adds a listener to the departmentID list
     */
    private void addDepartmentIDListListener(ListSelectionListener listener)
    {
        departmentIDList.addListSelectionListener(listener);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets the text in the department text fields to the empty string.
     */
    private void clearFields()
    {
        departmentID.setText(CLEAR_TEXT);
        departmentName.setText(CLEAR_TEXT);
        departmentManager.setText(CLEAR_TEXT);
        departmentCredits.setText(CLEAR_TEXT);
        departmentActive.setText(CLEAR_TEXT);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets whether or not the department fields are editable.
     */
    private void setFieldsEditable(boolean enable)
    {
        departmentID.enableTextField(enable);
        departmentName.enableTextField(enable);
        departmentManager.enableTextField(enable);
        departmentCredits.enableTextField(enable);
        departmentActive.enableTextField(enable);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Displays the details of a Department in the department fields.
     * @param department the Department to display.
     */
    private void display(Department department) {
        if (department != null) {
            departmentID.setText(String.valueOf(department.getDepartmentID()));
            departmentName.setText(department.getDepartmentName());
            departmentManager.setText(String.valueOf(department.getDepartmentManager()));
            departmentCredits.setText(String.valueOf(department.getDepartmentCredits()));
            departmentActive.setText(String.valueOf(department.getDepartmentActive()));
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Checks size of data/model and enables/disables the delete button
     *
     */
    private void checkListSize() {
        deleteButton.setEnabled(CurrentDepartmentData.getSize() != 0);
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
            clearFields();
            setFieldsEditable(true);
            departmentID.enableTextField(false);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * When the save button is pressed, check that the departmentID field contains
     * something. If it does, create a new Department object and attempt to add it
     * to the data model. Change the fields back to not editable and make the
     * save button inactive.
     *
     * Check the list size to see if the delete button should be enabled.
     */
    private ActionListener savePressed()
    {
        return e ->
        {
            Department d = new Department(CurrentDepartmentData.getSize() + 1, departmentName.getText(), Integer.valueOf(departmentManager.getText()), Integer.valueOf(departmentCredits.getText()), Boolean.valueOf(departmentActive.getText()));
            CurrentDepartmentData.add(d);

            setFieldsEditable(false);
            saveButton.setEnabled(false);
            checkListSize();
            refreshDepartmentIDScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * When the delete button is pressed remove the selected departmentID from the
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
            int index = departmentIDList.getSelectedIndex();
            CurrentDepartmentData.remove(departmentIDList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (CurrentDepartmentData.getSize() != 0) {
                    index = 0;
                }
            }
            departmentIDList.setSelectedIndex(index);
            checkListSize();
            refreshDepartmentIDScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different departmentID is selected from the list.
     */
    private class DepartmentIDListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            if (departmentIDList.getSelectedValue() != null
                    && !departmentIDList.getSelectedValue().equals("")) {
                display(CurrentDepartmentData.get(departmentIDList.getSelectedValue()));
            }
        }
    }
}
