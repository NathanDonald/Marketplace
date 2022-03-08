package Application.Screens.AssetScreen;

/**
 * Represents any exceptions that are thrown by the Asset classes.
 */
public class AssetException extends Exception{

    /**
     * Creates a new instance of AssetException.
     *
     * @param message The message that explains the details on what went wrong.
     */
    public AssetException(String message) {super("Asset Exception: " + message);}
}
