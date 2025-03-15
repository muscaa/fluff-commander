package fluff.commander.command;

import java.util.LinkedList;
import java.util.List;

import fluff.commander.CommanderConfig;

/**
 * Utility class for generating output messages.
 */
public class OutputBuilder {
	
	private final StringBuilder output = new StringBuilder();
	private final StringBuilder builder = new StringBuilder();
	private List<String> prefixes = new LinkedList<>();
	
	/**
	 * Adds a prefix to the output message.
	 *
	 * @param prefix the prefix to add
	 * @return the output generator instance
	 */
	public OutputBuilder prefix(String prefix) {
		prefixes.add(prefix);
		return this;
	}
	
	/**
	 * Removes the last added prefix from the output message.
	 *
	 * @return the output generator instance
	 */
	public OutputBuilder unprefix() {
		prefixes.remove(prefixes.size() - 1);
		return this;
	}
	
	/**
	 * Adds a tab to the output message.
	 *
	 * @return the output generator instance
	 */
	public OutputBuilder tab() {
		return prefix(CommanderConfig.TAB);
	}
	
	/**
	 * Removes the last added tab from the output message.
	 *
	 * @return the output generator instance
	 */
	public OutputBuilder untab() {
		return unprefix();
	}
	
	/**
	 * Appends an object to the output message.
	 *
	 * @param obj the object to append
	 * @return the output generator instance
	 */
	public OutputBuilder append(Object obj) {
		builder.append(obj);
		return this;
	}
	
	/**
	 * Adds a new line to the output message.
	 *
	 * @return the output generator instance
	 */
	public OutputBuilder newLine() {
		StringBuilder prefix = new StringBuilder();
		for (String s : prefixes) {
			prefix.append(s);
		}
		
		output.append(prefix.toString() + builder.toString() + CommanderConfig.NEW_LINE);
		builder.setLength(0);
		return this;
	}
	
	/**
	 * Retrieves the output message.
	 *
	 * @return the output message
	 */
	public String getOutput() {
		return output.toString();
	}
}
