package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for byte command line arguments.
 */
public class ArgumentParserByte implements IArgumentParser<Byte> {
    
    /**
     * Parses the input argument as a byte value.
     *
     * @param in the input argument to parse
     * @return the parsed byte value
     * @throws ArgumentException if the input value is null or
     *                           if a parsing error occurs
     */
    @Override
    public Byte parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        try {
            return Byte.parseByte(in.consume());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Parse error!", e);
        }
    }
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array containing a single string "byte",
     *         representing the type of value expected by this parser
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "byte"
        };
    }
}
