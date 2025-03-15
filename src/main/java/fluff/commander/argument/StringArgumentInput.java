package fluff.commander.argument;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents a string argument input.
 */
public class StringArgumentInput extends StringArrayArgumentInput {
	
	private static final int NORMAL = 0;
	private static final int IN_QUOTE = 1;
	private static final int IN_DOUBLE_QUOTE = 2;
	
    /**
     * Constructs a StringArgumentInput instance with the specified string arguments.
     *
     * @param argsString the string arguments
     */
	public StringArgumentInput(String argsString) throws ArgumentException {
		super(parseArgsFromString(argsString));
	}
	
    /**
     * Parses string arguments to an array of arguments.
     *
     * @param argsString the string arguments
     * @return an array of arguments
     * @throws ArgumentException if an error occurs during parsing
     */
    public static String[] parseArgsFromString(String argsString) throws ArgumentException {
        if (argsString == null || argsString.isEmpty()) {
            return new String[0];
        }
        
        StringTokenizer tok = new StringTokenizer(argsString, "\"' ", true);
        List<String> result = new LinkedList<>();
        StringBuilder current = new StringBuilder();
        
        int state = NORMAL;
        boolean lastTokenHasBeenQuoted = false;
        
        while (tok.hasMoreTokens()) {
            String nextTok = tok.nextToken();
            switch (state) {
	            case IN_QUOTE:
	                if ("'".equals(nextTok)) {
	                    lastTokenHasBeenQuoted = true;
	                    state = NORMAL;
	                } else {
	                    current.append(nextTok);
	                }
	                break;
	            case IN_DOUBLE_QUOTE:
	                if ("\"".equals(nextTok)) {
	                    lastTokenHasBeenQuoted = true;
	                    state = NORMAL;
	                } else {
	                    current.append(nextTok);
	                }
	                break;
	            default:
	                if ("'".equals(nextTok)) {
	                    state = IN_QUOTE;
	                } else if ("\"".equals(nextTok)) {
	                    state = IN_DOUBLE_QUOTE;
	                } else if (" ".equals(nextTok)) {
	                    if (lastTokenHasBeenQuoted || current.length() > 0) {
	                        result.add(current.toString());
	                        current.setLength(0);
	                    }
	                } else {
	                    current.append(nextTok);
	                }
	                lastTokenHasBeenQuoted = false;
	                break;
            }
        }
        
        if (lastTokenHasBeenQuoted || current.length() > 0) {
            result.add(current.toString());
        }
        
        if (state == IN_QUOTE || state == IN_DOUBLE_QUOTE) {
            throw new ArgumentException("Unbalanced quotes in " + argsString);
        }
        
        return result.toArray(new String[0]);
    }
}
