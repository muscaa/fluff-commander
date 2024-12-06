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
    public int onAction(Commander c, CommandArguments args) throws CommandException {
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
    public int onAction(Commander c, CommandArguments args) throws CommandException {
        String msg = args.get(ARG_MSG);
        System.out.println(msg);
        
        return SUCCESS;
    }
}
```

### Executing the commands

```java
Commander<?> commander = new Commander<>(false, "example"); // create a new commander

// register tasks and commands
commander.task("task", t -> { // register a new task "task"
    t.command(new NamedCommand("one")); // a command available in task "task"
    t.command(new NamedCommand("two")); // another command
    
    t.task(...); // more sub tasks/commands
});
commander.command(new PrintCommand()); // a print command available directly

// execute using parsed args
commander.execute(new StringArrayArgumentInput(args)); // execute commands from program arguments
commander.execute(new StringArrayArgumentInput("task", "one")); // will print "one"
commander.execute(new StringArrayArgumentInput("task", "two")); // will print "two"

// execute using a command string
commander.execute(new StringArgumentInput("print --msg 'Hello World!'")); // will print "Hello World!"
commander.execute(new StringArgumentInput("print --help")); // will print help about the command
commander.execute(new StringArgumentInput("print")); // will throw an error with missing arguments
```

## Usage

<details>
<summary>Gradle</summary>
    
```gradle
repositories {
    maven { url "https://jitpack.io" }
}

dependencies {
    implementation "com.github.muscaa:fluff-commander:VERSION"
}
```
</details>

<details>
<summary>Maven</summary>
    
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
</details>

Replace `VERSION` with the latest release available on [JitPack](https://jitpack.io/#muscaa/fluff-commander).
