package es.ucm.tp1.supercars.control.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException{
	private static final String MESSAGE = "Not enough coins";
	public NotEnoughCoinsException() {
		super(MESSAGE);
	}
}
