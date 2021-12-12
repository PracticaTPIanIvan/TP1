package es.ucm.tp1.supercars.control.commands;

import java.text.DecimalFormat;

import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.logic.Game;

public class RecordCommand extends Command{
	private static final String NAME = "record";

	private static final String DETAILS = "rec[o]rd";

	private static final String SHORTCUT = "o";

	private static final String HELP = "show level record";
	
	private static final String ERROR = "Failed to read record \n";
	
	private static final boolean updateGame = false;
	
	private DecimalFormat df = new DecimalFormat("#.##"); 
	
	public RecordCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	public boolean execute(Game game) throws CommandExecuteException{
		if (game.recordIsValid()) {
			System.out.println(game.getLevel().name() + " record is " 
					+ df.format(game.getRecord(game.getLevel().name()) / 1000.0) + " s\n");
		} else {
			throw new CommandExecuteException(ERROR);
		}
		return false;
	}
	
	
}
