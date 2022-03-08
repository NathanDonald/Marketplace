package Application.Screens.OnlineTrade;

public class OnlineTradeException extends Exception{

    /**
     * Creates a new instance of OnlineTradeException.
     *
     * @param message The message that explains the details on what went wrong.
     */
    public OnlineTradeException(String message) {super("Online Trade Exception: " + message);}
}
