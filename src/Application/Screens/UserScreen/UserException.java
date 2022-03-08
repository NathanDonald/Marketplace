package Application.Screens.UserScreen;

public class UserException extends Exception{

    /**
     * Creates a new instance of UserException.
     *
     * @param message The message that explains the details on what went wrong.
     */
    public UserException(String message) {super("User Exception: " + message);}
}
