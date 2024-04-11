package fluff.commander.command;

public class SystemCommandOutput implements ICommandOutput {
	
	@Override
	public ICommandOutput print(String text) {
		System.out.print(text);
		return this;
	}
	
	@Override
	public ICommandOutput newLine() {
		System.out.println();
		return this;
	}
}
