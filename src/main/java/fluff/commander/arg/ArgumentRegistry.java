package fluff.commander.arg;

import fluff.commander.CommanderRegistry;
import fluff.commander.utils.HelpGenerator;

public class ArgumentRegistry extends CommanderRegistry<IArgument<?>> {
	
	private final ArgumentParsers parsers = new ArgumentParsers();
	
	public ArgumentRegistry() {
		ignore(HelpGenerator.ARG_HELP);
	}
	
	@Override
	protected String[] getKeys(IArgument<?> value) {
		return value.getNames();
	}
	
	public ArgumentParsers getParsers() {
		return parsers;
	}
}
