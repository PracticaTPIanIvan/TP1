package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.view.GameSerializer;
import es.ucm.tp1.supercars.logic.Game;

public class SerializeCommand extends Command{
	private static final String NAME = "serialize";

	private static final String DETAILS = "seriali[z]e";

	private static final String SHORTCUT = "z";

	private static final String HELP = "Serializes the board";
	
	private static final boolean updateGame = false;
	
	private static GameSerializer serializer;
	
	public SerializeCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	public boolean execute(Game game) {
		serializer = new GameSerializer(game);
		serializer.printSerialInfo();
		return false;
	}
}