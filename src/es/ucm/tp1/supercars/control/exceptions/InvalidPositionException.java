package es.ucm.tp1.supercars.control.exceptions;

public class InvalidPositionException extends CommandExecuteException{
	private static final String MESSAGE = "Invalid position.";
	public InvalidPositionException() {
		super(MESSAGE);
	}
}
