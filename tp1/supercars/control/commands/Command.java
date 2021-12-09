package es.ucm.tp1.supercars.control.commands;

/*import java.util.Arrays;*/

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.logic.Game;

public abstract class Command {

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";

	protected static final String INCORRECT_NUMBER_OF_ARGS_MSG = "Incorrect number of arguments";

	/* @formatter:off */
	private static final Command[] AVAILABLE_COMMANDS = {
		new HelpCommand(),
		new InfoCommand(),
		new UpdateCommand(),
		new GoUpCommand(),
		new GoDownCommand(),
		new ExitCommand(),
		new TestCommand(),
		new ShootCommand(),
		new ClearCommand(),
		new WaveCommand(),
		new GrenadeCommand(),
		new ResetCommand(), 
		new CheatCommand()
	};
	/* @formatter:on */

	public static Command getCommand(String[] commandWords, Level level) {
		int i = 0;
		Command command = null;
		while(command == null && i < AVAILABLE_COMMANDS.length) {
			command = AVAILABLE_COMMANDS[i].parse(commandWords);
			i++;
		}
		
		if (command == null) {
			System.out.println("[ERROR]: "+ UNKNOWN_COMMAND_MSG + "\n");
		}
		
		return command;
	}

	public static Command[] getAvailableCommands() {
		return AVAILABLE_COMMANDS;
	}
	private final String name;

	private final String shortcut;

	private final String details;

	private final String help;
	
	private boolean updateGame;

	public Command(String name, String shortcut, String details, String help, boolean updateGame) {
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
		this.updateGame = updateGame;
	}

	public abstract boolean execute(Game game);
	
	public String getShortcut() {
		return shortcut;
	}
	
	public boolean mustUpdateGame() {
		return this.updateGame;
	}
	
	public String getHelp() {
		return help;
	}

	protected boolean matchCommandName(String name) {
		return this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name);
	}

	protected Command parse(String[] words) {
		if (matchCommandName(words[0])) {
			if (words.length != 1) {
				System.out.format("[ERROR]: Command %s: %s%n%n", name, INCORRECT_NUMBER_OF_ARGS_MSG);
				return null;
			} else {
				return this;
			}
		}
		return null;
	}

}