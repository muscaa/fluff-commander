package fluff.commander.utils;

import java.util.ArrayList;
import java.util.List;

import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.command.AbstractCommand;
import fluff.commander.command.CommandRegistry;
import fluff.commander.command.ICommand;
import fluff.commander.command.TaskCommand;
import fluff.functions.gen.obj.Func1;

/**
 * Utility methods for generating command help.
 */
public class HelpUtils {
	
	/**
	 * Generates the usage string for the given command.
	 *
	 * @param cmd the command for which to generate the usage string
	 * @return the usage string for the command
	 */
	public static String getUsage(AbstractCommand<?> cmd) {
		StringBuilder sb = new StringBuilder();
		
		List<AbstractCommand<?>> cmds = new ArrayList<>();
		
		AbstractCommand<?> cmd0 = cmd;
		while (!(cmd0 instanceof FluffCommander<?>)) {
			cmds.add(cmd0);
			
			cmd0 = cmd0.parent();
		}
		cmds.add(cmd0);
		
		for (int i = cmds.size() - 1; i >= 0; i--) {
			AbstractCommand<?> c = cmds.get(i);
			
			sb.append(c.getName())
					.append(" ");
			
			for (IArgument<?> arg : c.getArgumentRegistry().getAll()) {
				if (!arg.isRequired()) continue;
				
				IArgumentParser parser = cmd.getArgumentRegistry().getParsers().get(arg.getParserClass());
				sb.append(arg.getNames()[0])
						.append(" <")
						.append(parser == null ? "value" : String.join("|", parser.getValues()))
						.append("> ");
			}
		}
		
		if (cmd.getArgumentRegistry().isEmpty()) {
			if (cmd instanceof TaskCommand<?> task && !task.getCommandRegistry().isEmpty()) {
				sb.append("[cmd]");
			}
		} else {
			sb.append("[args");
			if (cmd instanceof TaskCommand<?> task && !task.getCommandRegistry().isEmpty()) {
				sb.append("|cmd");
			}
			sb.append("]");
		}
		
		return sb.toString();
	}
	
	/**
	 * Retrieves the number of spaces required for alignment in the output.
	 *
	 * @param list the list of items
	 * @param func the function to retrieve the string representation of an item
	 * @param <V>  the type of items in the list
	 * @return the number of spaces required for alignment
	 */
	public static <V> int getSpaces(List<V> list, Func1<String, V> func) {
		int spaces = 16;
		for (V v : list) {
			int len = func.invoke(v).length();
			
			if (len > spaces) spaces = len;
		}
		return spaces;
	}
	
	/**
	 * Appends spaces to the given help generator.
	 *
	 * @param help   the help generator
	 * @param start  the starting string
	 * @param spaces the number of spaces to append
	 */
	public static void appendSpaces(HelpGenerator help, String start, int spaces) {
		for (int i = start.length(); i <= spaces; i++) {
			help.append(" ");
		}
	}
	
	/**
	 * Appends information about the arguments to the given help generator.
	 *
	 * @param help the help generator
	 * @param reg  the argument registry
	 * @return true if arguments are appended, false otherwise
	 */
	public static boolean appendArguments(HelpGenerator help, ArgumentRegistry reg) {
		if (reg.isEmpty()) return false;
		
		help.newLine()
				.append("Arguments:")
				.newLine();
		
		List<IArgument<?>> args = reg.getNotIgnored();
		//int spaces = getSpaces(args, v -> String.join(", ", v.getNames()));
		
		for (int i = 0; i < args.size(); i++) {
			IArgument<?> arg = args.get(i);
			help.addTab(arg.isRequired() ? "  * " : "    ");
			
			String names = String.join(", ", arg.getNames());
			help.append(names);
			//appendSpaces(help, names, spaces);
			help.append(" ");
			
			IArgumentParser parser = reg.getParsers().get(arg.getParserClass());
			help.append("<")
					.append(parser == null ? "value" : String.join("|", parser.getValues()))
					.append(">");
			if (!arg.isRequired() && arg.getDefaultValue() != null) {
				help.append(" (default: ")
						.append(arg.getDefaultValue())
						.append(")");
			}
			help.newLine();
			
			help.removeTab();
			help.addTab();
			
			help.addTab();
			
			if (arg.getDescription() != null) {
				help.append("Description: ")
						.append(arg.getDescription())
						.newLine();
			}
			
			help.removeTab();
			if (i != args.size() - 1) help.newLine();
			help.removeTab();
		}
		
		return true;
	}
	
	/**
	 * Appends information about the commands to the given help generator.
	 *
	 * @param help the help generator
	 * @param reg  the command registry
	 * @return true if commands are appended, false otherwise
	 */
	public static boolean appendCommands(HelpGenerator help, CommandRegistry reg) {
		if (reg.isEmpty()) return false;
		
		help.newLine()
				.append("Commands:")
				.newLine();
		
		List<ICommand> cmds = reg.getNotIgnored();
		
		for (int i = 0; i < cmds.size(); i++) {
			ICommand cmd = cmds.get(i);
			help.addTab();
			
			help.append(cmd.getName())
					.newLine();
			
			help.addTab();
			if (cmd.getDescription() != null) {
				help.append("Description: ")
						.append(cmd.getDescription())
						.newLine();
			}
			help.removeTab();
			
			if (i != cmds.size() - 1) help.newLine();
			help.removeTab();
		}
		
		return true;
	}
}
