# Fluff Commander

[![](https://jitpack.io/v/muscaa/fluff-commander.svg)](https://jitpack.io/#muscaa/fluff-commander) [![License: Apache 2.0](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Description

A library that simplifies the creation of command line tools. Define commands effortlessly to build powerful and flexible applications for utilities, scripts, or interactive shell usage.

## Example

### Command Classes

```java
public class NamedCommand extends AbstractCommand {
    
    public NamedCommand(String name) {
        super(name);
    }
    
    @Override
    public int onAction(FluffCommander fc, CommandArguments args) throws CommandException {
        System.out.println(getName());
        
        return SUCCESS;
    }
}

public class PrintCommand extends AbstractCommand {

    // a required argument that accepts a string as the value
    public static final IArgument<String> ARG_MSG = ArgumentBuilder
            .String("--msg")
            .required()
            .build();
    
    public PrintCommand() {
        super("print");
    }
    
    @Override
    public void initArguments() {
        argument(ARG_MSG); // register the argument
    }
    
    @Override
    public int onAction(FluffCommander fc, CommandArguments args) throws CommandException {
        String msg = args.get(ARG_MSG);
        System.out.println(msg);
        
        return SUCCESS;
    }
}
```

### Executing the commands

```java
FluffCore.init(); // must be called before using Fluff libraries

FluffCommander<?> fc = new FluffCommander<>("example"); // create a new commander

fc.task("task", t -> { // register a new task "task"
    t.command(new NamedCommand("one")); // a command available in task "task"
    t.command(new NamedCommand("two")); // another command
    
    t.task(...); // more tasks/commands
});
fc.command(new PrintCommand()); // a print command available directly

fc.execute(new SimpleArgumentReader(args)); // execute commands from program arguments
fc.execute(new SimpleArgumentReader(new String[] { // will print "one"
        "task", "one"
}));
fc.execute(new SimpleArgumentReader(new String[] { // will print "two"
        "task", "two"
}));
fc.execute(new SimpleArgumentReader(new String[] { // will print "Hello World!"
        "print", "--msg", "Hello World!"
}));
fc.execute(new SimpleArgumentReader(new String[] { // will print help about the command
        "print", "--help"
}));
fc.execute(new SimpleArgumentReader(new String[] { // will throw an error with missing arguments
        "print"
}));
```

## Usage

To integrate it into your project, add the following dependency:

**Gradle**
```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.muscaa:fluff-commander:VERSION"
}
```
**Maven**
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.muscaa</groupId>
        <artifactId>fluff-commander</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```
Replace `VERSION` with the latest release available on [JitPack](https://jitpack.io/#muscaa/fluff-commander).
