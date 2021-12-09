package es.ucm.tp1.supercars.control;

import java.util.Locale;
import java.util.Scanner;

import es.ucm.tp1.supercars.control.commands.Command;
import es.ucm.tp1.supercars.control.exceptions.GameException;
import es.ucm.tp1.supercars.logic.Game;
import es.ucm.tp1.supercars.view.GamePrinter;

public class Controller {
	private static final String PROMPT = "Command > ";

	private boolean display;
	
	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;
	
	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		printer = new GamePrinter(game);
		display = true;
	}
	
	public void printGame() {
		System.out.println(printer);
	}
	

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	} 
	
	/*public String userInput() {
		System.out.println(PROMPT);
		return scanner.nextLine().toLowerCase();
	}*/
	
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
		game.setFirstLoop(false);
	}
	
	
	
	public void run() { 
		//System.out.format(Locale.FRANCE, "%10.2f", piVal);
		//new Locale("es", "ES");
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
			
			try {
				Command command = Command.getCommand(parameters, game.getLevel());
				display = command.execute(game);
			}
			catch(GameException ex) {
				System.out.format(ex.getMessage() + "%n%n");
			}
			
			if (command != null && game.playerIsAlive()) {
			game.doCollision();
			game.removeDead();
			if (!game.getReset() && command.mustUpdateGame()) {
				game.doCollision();
				game.update();
				game.deleteObjects();
			} else if (game.getReset()) {
				reset();
			}
			
			if (command.mustUpdateGame() && !game.getFirstLoop()) {
				game.generateRuntimeObjects();
			}
			
		} 

		if (game.getFirstLoop()) {
			game.setFirstLoop(false);
		}
			
		}
		if (game.isFinished() || !game.playerIsAlive()) {
			game.update();
			printGame();
		}
		System.out.println(printer.endMessage());
	}
}
