package com.mymoney.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mymoney.exceptions.InvalidCommandException;


public class CommandInvoker {
    private static final Map<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName,command);
    }

    private Command get(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, List<String> tokens) 
            throws InvalidCommandException {
        Command command = get(commandName);
        if (command == null) {
            // Handle Exception
            throw new InvalidCommandException();
        }
        command.execute(tokens);
    }
}
