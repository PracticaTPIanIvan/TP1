package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private String input;
	
	boolean advance;
	
	boolean crash;
	
	boolean end;
	
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
		crash = false;
		end = false;
	}

	public void printGame() {
		System.out.println(printer);
	}
	

	public void printEndMessage() {
		System.out.println(printer.endMessage());
	} 
	
	public String userInput() {
		System.out.println(PROMPT);
		return scanner.nextLine();
	}
	
	public void checkCrash() {
		if (game.obstaclePosition(game.getPlayerPositionX(), game.getPlayerPositionY()) != -1) {
			crash = true;
		}
	}
	
	public void checkEnd() {
		if (game.getPlayerPositionX() >= game.getRoadLength()) {
			end = true;
		}
	}
	
	public void help() {
		System.out.println(HELP);
	}
	
	public void moveUp() {
		game.moveUp();
	}
	
	public void moveDown() {
		game.moveDown();
	}
	
	public void inputControl() {
		advance = true;
		
		switch (input) {
		case "h":
		case "H":
		case "help":
		case "Help":
			help();
			advance = false;
			break;
		case "i":
		case "I":
		case "info":
		case "Info":
			//info();
			advance = false;
			break;
		case "n":
		case "N":
		case "none":
		case "None":
		case "":
			
			break;
		case "q":
		case "Q":
		case "up":
		case "Up":
			if(game.getPlayerPositionY() > 0) {
			moveUp();
			}
			break;
		case "a":
		case "A":
		case "down":
		case "Down":
			if(game.getPlayerPositionY() < game.getRoadWidth() - 1) {
			moveDown();
			}
			break;
		case "e":
		case "E":
		case "exit":
		case "Exit":
			//exit();
			break;
		case "r":
		case "R":
		case "reset":
		case "Reset":
			//reset();
			break;
		case "t":
		case "T":
		case "test":
		case "Test":
			game.toggleTest();
			break;
		}
		
		
	}

	public void run() {
		while (!crash && !end) {
			printGame();
			input = userInput();
			inputControl();
			if (advance) {
				game.advance();
				checkCrash();
				checkEnd();
			}
		}
		System.out.println("Game Over");
	}

}
