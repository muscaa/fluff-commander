package fluff.commander.utils;

import java.util.ArrayList;
import java.util.List;

import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentBuilder;
import fluff.commander.arg.IArgument;
import fluff.commander.command.AbstractCommand;
import fluff.commander.command.ICommand;
import fluff.commander.command.TaskCommand;

public class HelpGenerator {
	
	public static final IArgument<Boolean> ARG_HELP = ArgumentBuilder
			.Boolean("--help")
			.description("Shows help.")
			.build();
	
	private final List<String> lines = new ArrayList<>();
	private final StringBuilder builder = new StringBuilder();
	private String tab = "";
	
	public HelpGenerator addTab(String text) {
		tab += text;
		return this;
	}
	
	public HelpGenerator addTab() {
		addTab("    ");
		return this;
	}
	
	public HelpGenerator removeTab() {
		tab = tab.substring(0, tab.length() - 4);
		return this;
	}
	
	public HelpGenerator append(Object obj) {
		builder.append(obj);
		return this;
	}
	
	public HelpGenerator newLine() {
		lines.add(tab + builder.toString());
		builder.setLength(0);
		return this;
	}
	
	public List<String> getLines() {
		return lines;
	}
	
	public static HelpGenerator of(ICommand cmd, String usage) {
		HelpGenerator help = new HelpGenerator();
		help.append(cmd instanceof FluffCommander ?
						"Help:" :
						("Command '" + cmd.getName() + "' help:"))
				.newLine();
		help.addTab();
		
		help.append("Usage: ")
				.append(usage)
				.newLine();
		
		if (cmd.getDescription() != null) help.append("Description: ")
						.append(cmd.getDescription())
						.newLine();
		
		help.removeTab();
		
		return help;
	}
	
	public static HelpGenerator of(AbstractCommand cmd) {
		String usage = HelpUtils.getUsage(cmd);
		
		HelpGenerator help = of(cmd, usage);
		
		help.addTab();
		boolean arguments = HelpUtils.appendArguments(help, cmd.getArgumentRegistry());
		help.removeTab();
		
		boolean commands = false;
		if (cmd instanceof TaskCommand task) {
			help.addTab();
			commands = HelpUtils.appendCommands(help, task.getCommandRegistry());
			help.removeTab();
		}
		
		if (arguments || commands) help.newLine();
		
		return help;
	}
}
