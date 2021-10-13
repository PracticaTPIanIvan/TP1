package es.ucm.tp1.logic;

import java.util.Random;

public class Coin {
	private int posX;
	
	private int posY;
	
	private Game game;
	
	private Random random;
	
	public Coin(int posX, int posY, Game game) {
		this.posX = posX;
		this.posY = posY;
		this.game = game;
	}
	
	public int getCoinPositionX() {
		return posX;
	}
	
	public int getCoinPositionY() {
		return posY;
	}
}
