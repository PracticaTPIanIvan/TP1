package es.ucm.tp1.supercars.control.commands;

import es.ucm.tp1.supercars.view.GameSerializer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import es.ucm.tp1.supercars.control.exceptions.CommandParseException;
import es.ucm.tp1.supercars.control.exceptions.CommandExecuteException;
import es.ucm.tp1.supercars.logic.Game;

public class DumpCommand extends Command{
	private static final String NAME = "dump";

	private static final String DETAILS = "[d]ump";

	private static final String SHORTCUT = "d";

	private static final String HELP = "Shows the content of a saved file";
	
	private static final String ERROR = "An error ocurred on reading a file \n";
	
	private static final boolean updateGame = false;
	
	private static String fileName;
	
	private static GameSerializer serializer;
	
	public DumpCommand() {
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
	
	public boolean execute(Game game){
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    } 
		    System.out.print(sb);
		    br.close();
		} catch (FileNotFoundException e) {
			System.out.println(ERROR);
		} catch (IOException e) {
			System.out.println(ERROR);
		}
		return false;
		
	}
}