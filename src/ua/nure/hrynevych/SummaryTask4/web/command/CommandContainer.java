package ua.nure.hrynevych.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {

	private static Map<String, Command> commands = new TreeMap<String, Command>();
	
	static {

		// common commands
		commands.put("login", new LoginCommand());
		commands.put("forward", new ForwardCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("noCommand", new NoCommand());
	
//		// client commands
		
		// admin commands
		commands.put("listPatients", new ListPatientsCommand());
		commands.put("listDoctors", new ListDoctorsCommand());
		commands.put("addUser", new AddUserCommand());
		commands.put("appoint", new AppointCommand());
		commands.put("discharge", new DischargeCommand());
	}

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			return commands.get("noCommand"); 
		}
		return commands.get(commandName);
	}
}
