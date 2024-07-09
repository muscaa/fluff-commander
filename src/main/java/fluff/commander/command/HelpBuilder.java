package fluff.commander.command;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fluff.commander.CommanderConfig;
import fluff.commander.arg.ArgumentBuilder;
import fluff.commander.arg.IArgument;

/**
 * Utility class for generating help messages.
 */
public class HelpBuilder {
	
	/**
	 * The argument for displaying help.
	 */
	public static final IArgument<Boolean> ARG_HELP = ArgumentBuilder
			.Boolean("--help")
			.description("Shows help.")
			.build();
	
	private final List<String> lines = new ArrayList<>();
	private final StringBuilder builder = new StringBuilder();
	private List<String> prefixes = new LinkedList<>();
	
	/**
	 * Adds a prefix to the help message.
	 *
	 * @param prefix the prefix to add
	 * @return the help generator instance
	 */
	public HelpBuilder prefix(String prefix) {
		prefixes.add(prefix);
		return this;
	}
	
	/**
	 * Removes the last added prefix from the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpBuilder unprefix() {
		prefixes.remove(prefixes.size() - 1);
		return this;
	}
	
	/**
	 * Adds a tab to the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpBuilder tab() {
		return prefix(CommanderConfig.TAB);
	}
	
	/**
	 * Removes the last added tab from the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpBuilder untab() {
		return unprefix();
	}
	
	/**
	 * Appends an object to the help message.
	 *
	 * @param obj the object to append
	 * @return the help generator instance
	 */
	public HelpBuilder append(Object obj) {
		builder.append(obj);
		return this;
	}
	
	/**
	 * Adds a new line to the help message.
	 *
	 * @return the help generator instance
	 */
	public HelpBuilder newLine() {
		StringBuilder prefix = new StringBuilder();
		for (String s : prefixes) {
			prefix.append(s);
		}
		
		lines.add(prefix.toString() + builder.toString());
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
}
