package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

public class ArgumentParserByte implements IArgumentParser<Byte> {
	
	@Override
	public Byte parse(IArgumentInput in) throws ArgumentException {
		if (in.isNull()) throw new ArgumentException("Specified value is null!");
		
		try {
			return Byte.parseByte(in.consume());
		} catch (NumberFormatException e) {
			throw new ArgumentException("Parse error!", e);
		}
	}
}
