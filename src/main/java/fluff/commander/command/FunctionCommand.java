package fluff.commander.command;

import java.util.List;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.functions.gen.obj.obj.TVoidFunc2;

public class FunctionCommand extends AbstractCommand {
	
	private final List<IArgument<?>> argList;
	private final TVoidFunc2<FluffCommander, CommandArguments, CommanderException> action;
	
	public FunctionCommand(String name, List<IArgument<?>> argList, TVoidFunc2<FluffCommander, CommandArguments, CommanderException> action) {
		super(name);
		this.argList = argList;
		this.action = action;
	}
	
	@Override
	public void initArguments(ArgumentRegistry reg) {
		reg.register(argList);
	}
	
	@Override
	public void onAction(FluffCommander fc, CommandArguments args) throws CommanderException {
		action.invoke(fc, args);
	}
}
