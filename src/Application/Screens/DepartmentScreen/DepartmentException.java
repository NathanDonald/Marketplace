package Application.Screens.DepartmentScreen;

public class DepartmentException extends Exception{

    /**
     * Creates a new instance of DepartmentException.
     *
     * @param message The message that explains the details on what went wrong.
     */
    public DepartmentException(String message) {super("Department Exception: " + message);}
}
