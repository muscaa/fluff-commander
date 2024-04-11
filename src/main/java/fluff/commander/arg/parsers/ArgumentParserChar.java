package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

public class ArgumentParserChar implements IArgumentParser<Character> {
	
	@Override
	public Character parse(IArgumentInput in) throws ArgumentException {
		if (in.isNull()) throw new ArgumentException("Specified value is null!");
		if (in.peek().length() > 1) throw new ArgumentException("Not a character!");
		
		return in.consume().charAt(0);
	}
}
