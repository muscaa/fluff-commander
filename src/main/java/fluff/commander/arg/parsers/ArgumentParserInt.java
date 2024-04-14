package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

public class ArgumentParserInt implements IArgumentParser<Integer> {
	
	@Override
	public Integer parse(IArgumentInput in) throws ArgumentException {
		if (in.isNull()) throw new ArgumentException("Specified value is null!");
		
		try {
			return Integer.parseInt(in.consume());
		} catch (NumberFormatException e) {
			throw new ArgumentException("Parse error!", e);
		}
	}
	
	@Override
	public String[] getValues() {
		return new String[] {
				"int"
		};
	}
}
