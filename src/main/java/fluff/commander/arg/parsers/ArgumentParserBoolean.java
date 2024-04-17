package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for boolean command line arguments.
 */
public class ArgumentParserBoolean implements IArgumentParser<Boolean> {
    
    /**
     * Parses the input argument as a boolean value.
     *
     * @param in the input argument to parse
     * @return the parsed boolean value
     * @throws ArgumentException if an error occurs during parsing
     */
    @Override
    public Boolean parse(IArgumentInput in) throws ArgumentException {
        if ("true".equalsIgnoreCase(in.peek())) {
            in.consume();
            return true;
        }
        if ("false".equalsIgnoreCase(in.peek())) {
            in.consume();
            return false;
        }
        return true; // Default value if no valid boolean is found
    }
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array of strings representing the possible values
     *         parsed by this parser, used for generating help text
     *         or displaying available options
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "true",
            "false"
        };
    }
}
