package es.ucm.tp1.supercars.control;

import java.util.Scanner;

import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GamePrinter;

public class Controller {
	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private String input;
	
	private boolean display;
	
	private boolean firstLoop;
	
	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;
	
	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
		firstLoop = true;
		display = true;
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
	
	public long newSeed() {
		System.out.println("Introduce new seed: ");
		return Long.parseLong(scanner.nextLine().toLowerCase());
	}
	
	public Level newLevel() {
		System.out.println("Introduce new difficulty: ");
		return Level.valueOfIgnoreCase(scanner.nextLine());
	}
	
	public void reset() {
		game.resetContainer();
		Level level = game.getLevel();
		Long seed = game.getSeed();
		game.resetGame(level, seed);
		printer.reset();
		firstLoop = true;
	}
	
	public void run() {        //Utilizamos game.update()?
		while (!game.isFinished() && !game.getExit() && game.playerIsAlive()){
			
			if (display) {
				printGame();
			}
			
			if (game.getReset()) {
				game.setReset(false, game.getLevel(), game.getSeed());
			}
			display = false;
			System.out.println(PROMPT);
			String s = scanner.nextLine();
			String[] parameters = s.trim().split(" ");
			System.out.println("[DEBUG] Executing: " + s);
			for (int i = 0; i < parameters.length; i++) {
				parameters[i] = parameters[i].toLowerCase();
			}
			
			Command command = Command.getCommand(parameters, game.getLevel());
			
			game.doCollision();
			if (command != null && game.playerIsAlive()) {
				
				display = command.execute(game);
				if (!game.getReset() && command.updateGame()) {
					game.doCollision();
					game.deleteObjects();
					game.updateObjects();
					
				} else if (game.getReset()) {
					reset();
				}
				
			} else {
				System.out.println("[ERROR]: "+ UNKNOWN_COMMAND_MSG + "\n");
			}
			if (command.updateGame() && !firstLoop) {
				game.generateRuntimeObjects();
			}
			if (firstLoop) {
				firstLoop = false;
			}
		}
		if (game.isFinished() || !game.playerIsAlive()) {
			//game.incrementCycle();
			printGame();
		}
		System.out.println(printer.endMessage());
	}
}