package fluff.commander.command;

public interface ICommandOutput {
	
	ICommandOutput print(String text);
	
	ICommandOutput newLine();
}
