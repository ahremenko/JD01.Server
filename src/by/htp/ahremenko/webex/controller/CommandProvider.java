package by.htp.ahremenko.webex.controller;

import java.util.HashMap;
import java.util.Map;
import by.htp.ahremenko.webex.command.Command;
import by.htp.ahremenko.webex.command.impl.AddCalculatorImpl;

public class CommandProvider {
	private static CommandProvider provider = null; 
	private final Map<String, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put("ADD", new AddCalculatorImpl());
	}
	
	public Command getCommand(String cmd) {
		return commands.get(cmd.toUpperCase());
	}
	
	public static CommandProvider getInstance() {
		if (provider == null) {
			provider = new CommandProvider();
			System.out.println("Creating CommandProvider..");
		}
		return provider;
	}
}
