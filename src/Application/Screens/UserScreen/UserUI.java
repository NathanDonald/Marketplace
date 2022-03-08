//========//========//========//========//========
package Application.Screens.UserScreen;

//========//========//========//========//========//========//========
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
import java.io.Serial;
import javax.swing.*;
import java.awt.*;

//========//========//========//========//========//========//========//========//========
/**
 * Initiates user interface for the user application. All listeners for
 * the application are included as inner classes of this class.
 *
 */
public class UserUI extends JPanel
{

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    @Serial
    private static final long serialVersionUID = -5050538890770582361L;
    private JList userNameList;
    private TextField userID;
    private TextField firstName;
    private TextField surname;
    private TextField email;
    private TextField departmentID;
    private TextField permissionLevel;
    private TextField password;
    private TextField userIsActive;
    private static Button newButton;
    private static Button saveButton;
    private static Button deleteButton;
    private static Button modifyButton;
    private static Button changePasswordButton;
    private JScrollPane scroller;
    private boolean modify = false;
    private boolean changePassword = false;
    private static UserData CurrentUserData;

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Constructor sets up user interface, adds listeners and displays.
     */
    public UserUI() throws NoSuchAlgorithmException
    {
        CurrentUserData = new UserData();
        initUI();
        checkListSize();
        addUserNameListListener(new UserNameListListener());
        this.setVisible(true);
        this.setBackground(DEFAULT_WINDOW_COLOUR);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    public static void fetchData()
    {
        CurrentUserData = new UserData();
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Places the detail panel and the button panel in a box layout with vertical
     * alignment and puts a 20 pixel gap between the components and the top and
     * bottom edges of the frame.
     */
    private void initUI() throws NoSuchAlgorithmException {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(makeDetailsPanel());
        this.add(Box.createVerticalStrut(20));
        this.add(makeButtonsPanel());
        this.add(Box.createVerticalStrut(20));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JPanel consisiting of (1) the list of userNames and (2) the user details
     * fields in a box layout with horizontal alignment and puts a 20 pixel gap
     * between the components and the left and right edges of the panel.
     *
     * @return the detail panel.
     */
    private JPanel makeDetailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeUserNameListPane());
        detailsPanel.add(Box.createHorizontalStrut(20));
        detailsPanel.add(makeUserDetailsFieldsPanel());
        detailsPanel.add(Box.createHorizontalStrut(20));

        //--------//--------//--------//--------//--------//--------
        detailsPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return detailsPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshUserNameList()
    {
        userNameList = new JList(CurrentUserData.getModel());
        // Ensure text properties are also refreshed:
        userNameList.setForeground(DEFAULT_TEXT_FIELD_FOREGROUND);
        userNameList.setBackground(DEFAULT_TEXT_FIELD_BACKGROUND);
        userNameList.setFont(getMainFont(15));
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    private void refreshUserNameScroller()
    {
        refreshUserNameList();
        scroller.setViewportView(userNameList);
        clearFields();
        addUserNameListListener(new UserUI.UserNameListListener());
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JScrollPane that holds a JList for the list of userNames in the
     * table.
     *
     * @return the scrolling UserName list panel
     */
    private JScrollPane makeUserNameListPane()
    {
        refreshUserNameList();
        userNameList.setFixedCellWidth(200);
        scroller = new JScrollPane(userNameList);
        scroller.setViewportView(userNameList);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setMinimumSize(new Dimension(200, 150));
        scroller.setPreferredSize(new Dimension(250, 150));
        scroller.setMaximumSize(new Dimension(250, 200));
        return scroller;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Makes a JPanel containing labels and text fields for each of the pieces of
     * data that are to be recorded for each asset. The labels and fields are
     * laid out using a GroupLayout, with the labels vertically grouped, the
     * fields vertically grouped and each label/group pair horizontally grouped.
     *
     * @return a panel containing the asset fields
     */
    private JPanel makeUserDetailsFieldsPanel()
    {
        //--------//--------//--------//--------//--------//--------//--------//--------
        JPanel userPanel = new JPanel();
        GroupLayout layout = new GroupLayout(userPanel);
        userPanel.setLayout(layout);
        // Turn on automatically adding gaps between components
        layout.setAutoCreateGaps(true);
        // Turn on automatically creating gaps between components that touch
        // the edge of the container and the container.
        layout.setAutoCreateContainerGaps(true);

        //--------//--------//--------
        int fontHeight = 15;

        //--------//--------//--------//--------//--------//--------//--------//--------
        JLabel userIDLabel = new JLabel("User ID");
        userIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        userIDLabel.setFont(getMainFont(fontHeight));

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setForeground(MAIN_ACCENT_COLOUR);
        firstNameLabel.setFont(getMainFont(fontHeight));

        JLabel surnameLabel = new JLabel("Surname");
        surnameLabel.setForeground(MAIN_ACCENT_COLOUR);
        surnameLabel.setFont(getMainFont(fontHeight));

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setForeground(MAIN_ACCENT_COLOUR);
        emailLabel.setFont(getMainFont(fontHeight));

        JLabel departmentIDLabel = new JLabel("Department ID");
        departmentIDLabel.setForeground(MAIN_ACCENT_COLOUR);
        departmentIDLabel.setFont(getMainFont(fontHeight));

        JLabel permissionLevelLabel = new JLabel("Permission Level");
        permissionLevelLabel.setForeground(MAIN_ACCENT_COLOUR);
        permissionLevelLabel.setFont(getMainFont(fontHeight));

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(MAIN_ACCENT_COLOUR);
        passwordLabel.setFont(getMainFont(fontHeight));

        JLabel userIsActiveLabel = new JLabel("Is Active");
        userIsActiveLabel.setForeground(MAIN_ACCENT_COLOUR);
        userIsActiveLabel.setFont(getMainFont(fontHeight));

        //--------//--------//--------//--------//--------//--------//--------//--------
        userID = new TextField(fontHeight);
        firstName = new TextField(fontHeight);
        surname = new TextField(fontHeight);
        email = new TextField(fontHeight);
        departmentID = new TextField(fontHeight);
        permissionLevel = new TextField(fontHeight);
        userIsActive = new TextField(fontHeight);
        password = new TextField(fontHeight);
        setFieldsEditable(false);

        // Create a sequential group for the horizontal axis.
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        // The sequential group in turn contains two parallel groups.
        // One parallel group contains the labels, the other the text fields.
        hGroup.addGroup(layout.createParallelGroup().addComponent(userIDLabel).addComponent(firstNameLabel)
                        .addComponent(surnameLabel).addComponent(emailLabel).addComponent(departmentIDLabel)
                        .addComponent(permissionLevelLabel).addComponent(userIsActiveLabel).addComponent(passwordLabel));
        hGroup.addGroup(layout.createParallelGroup().addComponent(userID).addComponent(firstName).addComponent(surname)
                        .addComponent(email).addComponent(departmentID).addComponent(permissionLevel)
                        .addComponent(userIsActive).addComponent(password));
        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        // The sequential group contains five parallel groups that align
        // the contents along the baseline. The first parallel group contains
        // the first label and text field, and the second parallel group contains
        // the second label and text field etc. By using a sequential group
        // the labels and text fields are positioned vertically after one another.
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(userIDLabel).addComponent(userID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(firstNameLabel).addComponent(firstName));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(surnameLabel).addComponent(surname));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(emailLabel).addComponent(email));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(departmentIDLabel).addComponent(departmentID));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(permissionLevelLabel).addComponent(permissionLevel));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(userIsActiveLabel).addComponent(userIsActive));
        vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(passwordLabel).addComponent(password));
        layout.setVerticalGroup(vGroup);

        //--------//--------//--------//--------//--------//--------//--------
        userPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return userPanel;
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
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

        changePasswordButton = new Button(buttonPanel,"Change Password", changePasswordPressed());
        changePasswordButton.setEnabled(false);
        changePasswordButton.setBounds(580, yButton, 200, buttonHeight);

        //--------//--------//--------//--------//--------//--------//--------
        buttonPanel.setBackground(DEFAULT_WINDOW_COLOUR);
        return buttonPanel;
    }

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
            changePassword = false;
            clearFields();
            setFieldsEditable(true);
            userID.enableTextField(false);
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
            //Check if the first name and surname fields aren't null or white space and that modify is either true or false
            if (firstName.getText() != null && surname.getText() != null && !firstName.getText().equals("") &&
                    !surname.getText().equals("") && !firstName.getText().matches(".*\\d.*") && !surname.getText().matches(".*\\d.*") && !modify && !changePassword) {
                //GetSalt
                String[] saltandPass = new String[0];
                try {
                    saltandPass = PasswordEncoder.saltAndHash(password.getText());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                String pass = saltandPass[0];
                String salt = saltandPass[1];
                //modify is false, the code does saving for a new addition.
                User a = new User(CurrentUserData.getSize() + 1, firstName.getText(), surname.getText(),
                        email.getText(), Integer.valueOf(departmentID.getText()), permissionLevel.getText(), pass, salt,  Boolean.valueOf(userIsActive.getText()));
                try {
                    CurrentUserData.add(a);
                } catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }

            } else if (firstName.getText() != null && surname.getText() != null && !firstName.getText().equals("") && !surname.getText().equals("") &&
                    !surname.getText().equals("") && !firstName.getText().matches(".*\\d.*") && !surname.getText().matches(".*\\d.*") && modify) {

                //modify is true, the code does saving for an existing user.
                User a = CurrentUserData.get(Integer.valueOf(userID.getText()));
                int index = userNameList.getSelectedIndex();

                a.setUserID(Integer.valueOf(userID.getText()));
                a.setFirstName(firstName.getText());
                a.setSurname(surname.getText());
                a.setEmail(email.getText());
                a.setDepartmentID(Integer.valueOf(departmentID.getText()));
                a.setPermissionLevel(permissionLevel.getText());
                a.setUserIsActive(Boolean.valueOf(userIsActive.getText()));

                String userName = userID.getText() + " " + firstName.getText() + " " + surname.getText();
                CurrentUserData.modify(a, index, userName);

            } else if (password.getText() != null && !password.getText().equals("") && changePassword){

                //Changes the password of the user
                User a = CurrentUserData.get(Integer.valueOf(userID.getText()));
                int index = userNameList.getSelectedIndex();
                String[] passwordTemp = new String[0];
                try {
                    PasswordEncoder passwordEncoder = new PasswordEncoder();
                    passwordTemp = passwordEncoder.saltAndHash(password.getText());
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                a.setPassword(passwordTemp[0]);

                a.setSalt(passwordTemp[1]);
                String userName = userID.getText() + " " + firstName.getText() + " " + surname.getText();
                CurrentUserData.modify(a, index, userName);
            }
            setFieldsEditable(false);
            saveButton.setEnabled(false);
            checkListSize();
            changePassword = false;
            modify = false;
            password.setText(CLEAR_TEXT);
            refreshUserNameScroller();
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * When the delete button is pressed remove the selected userName from the
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
            changePassword = false;
            int index = userNameList.getSelectedIndex();
            //Getting userID out of userNameList selection

            CurrentUserData.remove(userNameList.getSelectedValue());
            clearFields();
            index--;
            if (index == -1) {
                if (CurrentUserData.getSize() != 0) {
                    index = 0;
                }
            }
            userNameList.setSelectedIndex(index);
            checkListSize();
            refreshUserNameScroller();
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
            changePassword = false;
            setFieldsEditable(true);
            userID.enableTextField(false);
            password.enableTextField(false);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets up the password field and save button for changing the user password.
     */
    private ActionListener changePasswordPressed()
    {
        return e ->
        {
            changePassword = true;
            setFieldsEditable(false);
            password.enableTextField(true);
            saveButton.setEnabled(true);
        };
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Adds a listener to the userName list
     */
    private void addUserNameListListener(ListSelectionListener listener) {
        userNameList.addListSelectionListener(listener);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets the text in the user text fields to the empty string.
     */
    private void clearFields()
    {
        userID.setText(CLEAR_TEXT);
        firstName.setText(CLEAR_TEXT);
        surname.setText(CLEAR_TEXT);
        email.setText(CLEAR_TEXT);
        departmentID.setText(CLEAR_TEXT);
        permissionLevel.setText(CLEAR_TEXT);
        password.setText(CLEAR_TEXT);
        userIsActive.setText(CLEAR_TEXT);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Sets whether or not the user fields are editable.
     */
    private void setFieldsEditable(boolean enabled)
    {
        userID.enableTextField(enabled);
        firstName.enableTextField(enabled);
        surname.enableTextField(enabled);
        email.enableTextField(enabled);
        departmentID.enableTextField(enabled);
        permissionLevel.enableTextField(enabled);
        password.enableTextField(enabled);
        userIsActive.enableTextField(enabled);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Displays the details of a User in the user fields.
     * @param user the User to display.
     */
    private void display(User user) {
        if (user != null) {

            userID.setText(String.valueOf(user.getUserID()));
            firstName.setText(user.getFirstName());
            surname.setText(user.getSurname());
            email.setText(user.getEmail());
            departmentID.setText(String.valueOf(user.getDepartmentID()));
            permissionLevel.setText(user.getPermissionLevel());
            userIsActive.setText(String.valueOf(user.getUserIsActive()));
        }
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Checks size of data/model and enables/disables the delete button
     *
     */
    private void checkListSize() {
        deleteButton.setEnabled(CurrentUserData.getSize() != 0);
    }

    //>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>//>>>>>>>>
    /**
     * Implements a ListSelectionListener for making the UI respond when a
     * different userName is selected from the list.
     */
    private class UserNameListListener implements ListSelectionListener {

        /**
         * @see ListSelectionListener#valueChanged(ListSelectionEvent)
         */
        public void valueChanged(ListSelectionEvent e) {
            if (userNameList.getSelectedValue() != null
                    && !userNameList.getSelectedValue().equals("")) {
                modifyButton.setEnabled(true);
                changePasswordButton.setEnabled(true);
                setFieldsEditable(false);
                saveButton.setEnabled(false);
                modify = false;
                changePassword = false;
                //Getting userID out of userNameList selection
                String userName = userNameList.getSelectedValue().toString();
                Integer userID = Integer.parseInt(userName.replaceAll("\\D+","")) ;

                display(CurrentUserData.get(userID));
            }
        }
    }
}
