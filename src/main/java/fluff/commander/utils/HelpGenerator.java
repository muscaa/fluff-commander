package fluff.commander.utils;

import java.util.ArrayList;
import java.util.List;

import fluff.commander.Commander;
import fluff.commander.arg.ArgumentBuilder;
import fluff.commander.arg.IArgument;
import fluff.commander.command.AbstractCommand;
import fluff.commander.command.ICommand;
import fluff.commander.command.TaskCommand;

/**
 * Utility class for generating help messages.
 */
public class HelpGenerator {
	
	/**
	 * The argument for displaying help.
	 */
	public static final IArgument<Boolean> ARG_HELP = ArgumentBuilder
			.Boolean("--help")
			.description("Shows help.")
			.build();
	
	private final List<String> lines = new ArrayList<>();
	private final StringBuilder builder = new StringBuilder();
	private String tab = "";
	
	/**
	 * Adds indentation to the help message.
	 *
	 * @param text the text to add as indentation
	 * @return the help generator instance
	 */
	public HelpGenerator addTab(String text) {
		tab += text;
		return this;
	}
	
	/**
	 * Adds default indentation to the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpGenerator addTab() {
		addTab("    ");
		return this;
	}
	
	/**
	 * Removes the last added indentation from the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpGenerator removeTab() {
		tab = tab.substring(0, tab.length() - 4);
		return this;
	}
	
	/**
	 * Appends an object to the help message.
	 *
	 * @param obj the object to append
	 * @return the help generator instance
	 */
	public HelpGenerator append(Object obj) {
		builder.append(obj);
		return this;
	}
	
	/**
	 * Adds a new line to the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpGenerator newLine() {
		lines.add(tab + builder.toString());
		builder.setLength(0);
		return this;
	}
	
	/**
	 * Retrieves the lines of the help message.
	 *
	 * @return the lines of the help message
	 */
	public List<String> getLines() {
		return lines;
	}
	
	/**
	 * Generates a help message for the given command.
	 *
	 * @param cmd   the command for which to generate help
	 * @param usage the usage string for the command
	 * @return a help generator instance
	 */
	public static HelpGenerator of(ICommand cmd, String usage) {
		HelpGenerator help = new HelpGenerator();
		help.append(cmd instanceof Commander<?> ?
						"Help:" :
						("Command '" + cmd.getNames()[0] + "' help:"))
				.newLine();
		help.addTab();
		
		if (cmd.getNames().length > 1) {
			help.append("Alias: ")
					.append(String.join(" | ", cmd.getNames()))
					.newLine();
		}
		
		help.append("Usage: ")
				.append(usage)
				.newLine();
		
		if (cmd.getDescription() != null) help.append("Description: ")
						.append(cmd.getDescription())
						.newLine();
		
		help.removeTab();
		
		return help;
	}
	
	/**
	 * Generates a help message for the given abstract command.
	 *
	 * @param cmd the abstract command for which to generate help
	 * @return a help generator instance
	 */
	public static HelpGenerator of(AbstractCommand<?> cmd) {
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
