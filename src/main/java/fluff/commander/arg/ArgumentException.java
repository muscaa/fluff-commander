package fluff.commander.arg;

import fluff.commander.CommanderException;

public class ArgumentException extends CommanderException {
	
	private static final long serialVersionUID = -2303062509504751226L;
	
	public ArgumentException(String message) {
        super(message);
    }
	
    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ArgumentException(Throwable cause) {
        super(cause);
    }
}
