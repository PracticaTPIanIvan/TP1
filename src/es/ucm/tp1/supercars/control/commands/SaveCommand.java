package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.view.GameSerializer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.logic.Game;

public class SaveCommand extends Command{
	private static final String NAME = "save";

	private static final String DETAILS = "sa[v]e";

	private static final String SHORTCUT = "v";

	private static final String HELP = "Saves the state of the game to a file";
	
	private static final boolean updateGame = false;
	
	private static String fileName;
	
	private static GameSerializer serializer;
	
	public SaveCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP, updateGame);
	}
	
	protected Command parse(String[] words) throws CommandParseException{
		if (matchCommandName(words[0])) {
			if (words.length != 2) {
				throw new CommandParseException(String.format("[ERROR]:Command %s: %s", NAME, INCORRECT_NUMBER_OF_ARGS_MSG));
			} else {
				fileName = words[1] + ".txt";
				return this;
			}
		}
		return null;
	}
	
	public boolean execute(Game game) {
		try (Writer writer = new BufferedWriter(new FileWriter(fileName))) {
			serializer = new GameSerializer(game);
			serializer.saveSerialInfo(writer);
			System.out.println("Game succesfully saved in file" + fileName);
		} catch (IOException e) {
		      System.out.println("An error occurred.");
		}
		return false;
	}
}