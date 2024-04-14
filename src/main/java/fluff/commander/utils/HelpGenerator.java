package fluff.commander.utils;

import java.util.ArrayList;
import java.util.List;

import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentBuilder;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.command.AbstractCommand;
import fluff.commander.command.ICommand;

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
	
	public static HelpGenerator of(ICommand cmd, String usage, ArgumentRegistry reg) {
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
		
		if (!reg.isEmpty()) {
			help.newLine()
					.append("Arguments:")
					.newLine();
			
			List<IArgument<?>> args = reg.all();
			
			int spaces = 25;
			for (IArgument<?> arg : args) {
				int len = String.join(", ", arg.getNames()).length();
				
				if (len > spaces) spaces = len;
			}
			
			for (IArgument<?> arg : args) {
				help.addTab(arg.isRequired() ? "  * " : "    ");
				
				String names = String.join(", ", arg.getNames());
				help.append(names);
				for (int i = names.length(); i <= spaces; i++) {
					help.append(" ");
				}
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
				help.newLine();
				help.removeTab();
			}
		}
		
		help.removeTab();
		
		return help;
	}
	
	public static HelpGenerator of(AbstractCommand cmd) {
		StringBuilder sb = new StringBuilder();
		
		List<AbstractCommand> cmds = new ArrayList<>();
		
		AbstractCommand cmd0 = cmd;
		while (!(cmd0 instanceof FluffCommander)) {
			cmds.add(cmd0);
			
			cmd0 = cmd0.parent();
		}
		cmds.add(cmd0);
		
		for (int i = cmds.size() - 1; i >= 0; i--) {
			AbstractCommand c = cmds.get(i);
			
			sb.append(c.getName())
					.append(" ");
			
			for (IArgument<?> arg : c.reg.all()) {
				if (!arg.isRequired()) continue;
				
				IArgumentParser parser = cmd.reg.getParsers().get(arg.getParserClass());
				sb.append(arg.getNames()[0])
						.append(" <")
						.append(parser == null ? "value" : String.join("|", parser.getValues()))
						.append("> ");
			}
		}
		
		sb.append("[args|cmd]");
		
		HelpGenerator help = of(cmd, sb.toString(), cmd.reg);
		// commands
		return help;
	}
}
