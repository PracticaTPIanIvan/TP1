package es.ucm.tp1.supercars.control;

import java.util.Scanner;

import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GamePrinter;

public class Controller {
	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private String input;
	
	private boolean advance;
	
	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;
	
	private Command command;
	
	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
		advance = true;
	}
	
	public void printGame() {
		System.out.println(printer);
	}
	

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	} 
	
	public String userInput() {
		System.out.println(PROMPT);
		return scanner.nextLine().toLowerCase();
	}
	
	public void run() {
		while (!game.isFinished()){
			if (advance) {
			printGame();
			}
			advance = false;
			System.out.println(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.toLowerCase().trim().split(" ");
			System.out.println("[DEBUG] Executing: " + parameters[0]);
			Command command = Command.getCommand(parameters, game.getLevel());
			if (command != null) {
				advance = command.execute(game);
			} else {
				System.out.println("[ERROR]: "+ UNKNOWN_COMMAND_MSG);
			}
		}
		printer.endMessage();
	}
}
