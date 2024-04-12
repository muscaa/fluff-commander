package fluff.commander.arg;

public class ArgumentBuilder<V> {
	
	private Class<V> parserClass;
	private String[] names;
	private String description;
	private V defaultValue;
	private boolean required;
	
	public ArgumentBuilder<V> parserClass(Class<V> parserClass) {
		this.parserClass = parserClass;
		return this;
	}
	
	public ArgumentBuilder<V> names(String... names) {
		this.names = names;
		return this;
	}
	
	public ArgumentBuilder<V> description(String description) {
		this.description = description;
		return this;
	}
	
	public ArgumentBuilder<V> defaultValue(V defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}
	
	public ArgumentBuilder<V> required() {
		this.required = true;
		return this;
	}
	
	public IArgument<V> build() {
		return new SimpleArgument<V>(parserClass, names, description, defaultValue, required);
	}
	
	public static <V> ArgumentBuilder<V> of(Class<V> parserClass, String... names) {
		return new ArgumentBuilder<V>().parserClass(parserClass).names(names);
	}
	
	public static ArgumentBuilder<Boolean> Boolean(String... names) {
		return of(Boolean.class, names)
				.defaultValue(false)
				;
	}
	
	public static ArgumentBuilder<Byte> Byte(String... names) {
		return of(Byte.class, names)
				;
	}
	
	public static ArgumentBuilder<Character> Char(String... names) {
		return of(Character.class, names)
				;
	}
	
	public static ArgumentBuilder<Short> Short(String... names) {
		return of(Short.class, names)
				;
	}
	
	public static ArgumentBuilder<Integer> Int(String... names) {
		return of(Integer.class, names)
				;
	}
	
	public static ArgumentBuilder<Float> Float(String... names) {
		return of(Float.class, names)
				;
	}
	
	public static ArgumentBuilder<Long> Long(String... names) {
		return of(Long.class, names)
				;
	}
	
	public static ArgumentBuilder<Double> Double(String... names) {
		return of(Double.class, names)
				;
	}
	
	public static ArgumentBuilder<String> String(String... names) {
		return of(String.class, names)
				;
	}
}
