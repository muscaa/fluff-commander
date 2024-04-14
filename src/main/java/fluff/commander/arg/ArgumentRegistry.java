package fluff.commander.arg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgumentRegistry {
	
	private final ArgumentParsers parsers = new ArgumentParsers();
	private final Map<String, IArgument<?>> reg = new HashMap<>();
	
	public <V> IArgument<V> register(IArgument<V> arg) {
		for (String name : arg.getNames()) {
			reg.put(name, arg);
		}
		return arg;
	}
	
	public void register(List<IArgument<?>> argList) {
		for (IArgument<?> arg : argList) {
			register(arg);
		}
	}
	
	public <V> IArgument<V> register(ArgumentBuilder<V> builder) {
		return register(builder.build());
	}
	
	public IArgument<?> get(String name) {
		return reg.get(name);
	}
	
	public List<IArgument<?>> all() {
		List<IArgument<?>> list = new ArrayList<>();
		for (Map.Entry<String, IArgument<?>> e : reg.entrySet()) {
			if (!list.contains(e.getValue())) {
				list.add(e.getValue());
			}
		}
		return list;
	}
	
	public boolean isEmpty() {
		return reg.isEmpty();
	}
	
	public ArgumentParsers getParsers() {
		return parsers;
	}
}
