package es.ucm.tp1.control;

import java.util.Scanner;

import es.ucm.tp1.logic.Game;
import es.ucm.tp1.view.GamePrinter;

public class Controller {

	private static final String PROMPT = "Command > ";

	private static final String UNKNOWN_COMMAND_MSG = "Unknown command";
	
	private String input;
			
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
		return scanner.nextLine();
	}
	
	public void help() {
		System.out.println(HELP);
	}
	
	public void moveUp() {
		Game.player.positionUp(); 
	}
	
	public void inputControl() {
		switch (input) {
		case "h":
		case "H":
		case "help":
		case "Help":
			help();
			break;
		case "i":
		case "I":
		case "info":
		case "Info":
			//info();
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
			moveUp();
			break;
		case "a":
		case "A":
		case "down":
		case "Down":
			//moveDown();
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
		// TODO fill your code
		input = userInput();
		inputControl();
		printGame();
	}

}


