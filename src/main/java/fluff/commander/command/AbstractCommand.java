package fluff.commander.command;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.utils.HelpGenerator;

public abstract class AbstractCommand implements ICommand {
	
	public final ArgumentRegistry reg = new ArgumentRegistry();
	private final String name;
	
	ICommand parent;
	
	public AbstractCommand(String name) {
		this.name = name;
		
		if (shouldGenerateHelp()) reg.register(HelpGenerator.ARG_HELP);
		initArguments();
	}
	
	public abstract void initArguments();
	
	public abstract boolean onAction(FluffCommander fc, CommandArguments args) throws CommanderException;
	
	@Override
	public boolean onAction(FluffCommander fc, IArgumentInput in) throws CommanderException {
		CommandArguments args = CommandArguments.parse(in, reg, shouldGenerateHelp());
		
		if (shouldGenerateHelp() && (args.missing() || args.Boolean(HelpGenerator.ARG_HELP))) {
			HelpGenerator help = HelpGenerator.of(this);
			help.getLines().forEach(System.out::println);
			return true;
		}
		
		return onAction(fc, args);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	protected boolean shouldGenerateHelp() {
		return true;
	}
	
	public <V extends ICommand> V parent() {
		return (V) parent;
	}
}
