package fluff.commander.arg;

import fluff.commander.CommanderRegistry;

public class ArgumentRegistry extends CommanderRegistry<IArgument<?>> {
	
	private final ArgumentParsers parsers = new ArgumentParsers();
	
	@Override
	protected String[] getKeys(IArgument<?> value) {
		return value.getNames();
	}
	
	public ArgumentParsers getParsers() {
		return parsers;
	}
}
