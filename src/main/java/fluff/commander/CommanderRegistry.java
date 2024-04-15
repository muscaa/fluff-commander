package fluff.commander;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class CommanderRegistry<V> {
	
	protected final Map<String, V> reg = new LinkedHashMap<>();
	protected final List<V> ignored = new ArrayList<>();
	
	protected abstract String[] getKeys(V value);
	
	public void register(V value) {
		for (String name : getKeys(value)) {
			reg.put(name, value);
		}
	}
	
	public void ignore(V value) {
		ignored.add(value);
	}
	
	public V get(String name) {
		return reg.get(name);
	}
	
	public List<V> getAll() {
		return getAll(true);
	}
	
	public List<V> getNotIgnored() {
		return getAll(false);
	}
	
	public int size() {
		return getNotIgnored().size();
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	private List<V> getAll(boolean withIgnored) {
		List<V> list = new ArrayList<>();
		for (Map.Entry<String, V> e : reg.entrySet()) {
			if (list.contains(e.getValue())) continue;
			if (!withIgnored && ignored.contains(e.getValue())) continue;
			
			list.add(e.getValue());
		}
		return list;
	}
}
