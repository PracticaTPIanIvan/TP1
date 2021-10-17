package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private String input;
	
	private boolean advance;
	
	
	
	/* @formatter:off */
	private static final String[] HELP = new String[] {
		"Available commands:",
		"[h]elp: show this help",
		"[i]nfo: prints gameobjet info",
		"[n]one | []: update",
		"[q]: go up",
		"[a]: go down",
		"[e]xit: exit game",
		"[r]eset: reset game",
		"[t]est: enables test mode",	
	};
	/* @formatter:off */

	private Game game;

	private Scanner scanner;
	
	private GamePrinter printer;

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.printer = new GamePrinter(game);
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
	
	public void checkEnd() {
		if (game.getPlayerPositionX() >= game.getRoadLength()) {
			game.setWin(true);
		}
	}
	
	public void help() {
		System.out.println(HELP);
	}
	
	public void info() {
		System.out.println("[Car] the racing car\n" +
				"[Coin] gives 1 coin to the player\n" +
				"[Obstacle] hits car\n");
	}
	
	public void exit() {
		game.setExit(true);
	}
	
	public void reset() {
		game.reset();
	}
	
	public void inputControl() {
		advance = true;
		char res = ' ';
		
		switch (input) {
		case "h":
		case "help":
			advance = false;
			help();
			res = 'h';
			break;
		case "i":
		case "info":
			info();
			advance = false;
			res = 'i';
			break;
		case "n":
		case "none":
		case "":
			res = ' ';
			break;
		case "q":
		case "up":
			if(game.getPlayerPositionY() > 0) {
				res = 'q';
				game.moveUp();
			} else {
				res = ' ';
			}
			
			break;
		case "a":
		case "down":
			if(game.getPlayerPositionY() < game.getRoadWidth() - 1) {
				res = 'a';
				game.moveDown();
			} else {
				res = ' ';
			}
			
			break;
		case "e":
		case "exit":
			exit();
			res = 'e';
			break;
		case "r":
		case "reset":
			reset();
			res = 'r';
			advance = false;
			break;
		case "t":
		case "test":
			game.toggleTest();
			advance = false;
			res = 't';
			break;
		}
		
		game.setCurrentCommand(res);
	}

	public void run() {
		while (!game.getCrash() && !game.getExit() && !game.getWin()) {
			printGame();
			input = userInput();
			inputControl();
			if (advance) {
				game.advance();
				game.update();
				checkEnd();
			}
			
			if (game.getCrash() || game.getWin()) {
				printGame();
			}
			game.incrementCycle();
		}
		System.out.println(printer.endMessage());
	}

}
