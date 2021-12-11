package es.ucm.tp1.supercars.control.commands;

public class SerializeCommand extends Command{
	private static final String NAME = "shoot";

	private static final String DETAILS = "[s]hoot";

	private static final String SHORTCUT = "s";

	private static final String HELP = "shoot bullet";
	
	protected static final String ERROR = "[ERROR]: Failed to shoot \n";
	
	private static final boolean updateGame = false;
	
	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	
	
}
