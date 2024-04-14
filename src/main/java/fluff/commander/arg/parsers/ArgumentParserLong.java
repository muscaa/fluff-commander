package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

public class ArgumentParserLong implements IArgumentParser<Long> {
	
	@Override
	public Long parse(IArgumentInput in) throws ArgumentException {
		if (in.isNull()) throw new ArgumentException("Specified value is null!");
		
		try {
			return Long.parseLong(in.consume());
		} catch (NumberFormatException e) {
			throw new ArgumentException("Parse error!", e);
		}
	}
	
	@Override
	public String[] getValues() {
		return new String[] {
				"long"
		};
	}
}
