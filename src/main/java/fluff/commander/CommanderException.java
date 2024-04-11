package fluff.commander;

public class CommanderException extends Exception {
	
	private static final long serialVersionUID = -402320519751824367L;
	
	public CommanderException(String message) {
        super(message);
    }
	
    public CommanderException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public CommanderException(Throwable cause) {
        super(cause);
    }
}
