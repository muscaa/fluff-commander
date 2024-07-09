package fluff.commander;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A registry for managing mappings between keys and values.
 *
 * @param <V> the type of values stored in the registry
 */
public abstract class CommanderRegistry<V> {
    
    protected final Map<String, V> reg = new LinkedHashMap<>();
    protected final Set<V> ignored = new HashSet<>();
    
    /**
     * Retrieves the keys associated with a value.
     *
     * @param value the value for which keys are retrieved
     * @return an array of keys associated with the value
     */
    protected abstract String[] getKeys(V value);
    
    /**
     * Registers a value and associates it with its keys.
     *
     * @param value the value to register
     */
    public void register(V value) {
        for (String name : getKeys(value)) {
            reg.put(name, value);
        }
    }
    
    /**
     * Ignores a value, preventing it from being retrieved by key.
     *
     * @param value the value to ignore
     */
    public void ignore(V value) {
        ignored.add(value);
    }
    
    /**
     * Retrieves the value associated with a key.
     *
     * @param name the key to look up
     * @return the value associated with the key, or null if not found
     */
    public V get(String name) {
        return reg.get(name);
    }
    
    /**
     * Retrieves all values stored in the registry.
     *
     * @return a list of all values, including ignored ones
     */
    public List<V> getAll() {
        return getAll(true);
    }
    
    /**
     * Retrieves all values stored in the registry, excluding ignored ones.
     *
     * @return a list of all values, excluding ignored ones
     */
    public List<V> getNotIgnored() {
        return getAll(false);
    }
    
    /**
     * Returns the number of values stored in the registry.
     *
     * @return the number of values stored in the registry
     */
    public int size() {
        return getNotIgnored().size();
    }
    
    /**
     * Checks if the registry is empty.
     *
     * @return true if the registry is empty, false otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Retrieves all values stored in the registry.
     *
     * @param withIgnored true to include ignored values, false to exclude them
     * @return a list of all values
     */
    private List<V> getAll(boolean withIgnored) {
        List<V> list = new LinkedList<>();
        for (Map.Entry<String, V> e : reg.entrySet()) {
            if (list.contains(e.getValue())) continue;
            if (!withIgnored && ignored.contains(e.getValue())) continue;
            
            list.add(e.getValue());
        }
        return list;
    }
}
