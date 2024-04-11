package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

public class ArgumentParserShort implements IArgumentParser<Short> {
	
	@Override
	public Short parse(IArgumentInput in) throws ArgumentException {
		if (in.isNull()) throw new ArgumentException("Specified value is null!");
		
		try {
			return Short.parseShort(in.consume());
		} catch (NumberFormatException e) {
			throw new ArgumentException("Parse error!", e);
		}
	}
}
