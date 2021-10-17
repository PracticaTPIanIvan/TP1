package es.ucm.tp1.logic;

public class Player {
	private int positionX;
	private int positionY;
	private int numCoins;
	private Game game;
	private boolean crash;
	
	public Player(Game game) {
		this.positionX = 0;
		if(game.getRoadWidth() == 5) {
			this.positionY = 2;
		}else {
			this.positionY = 1;
		}
		this.numCoins = 5;
		this.game = game;
		crash = false;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}
	
	public int getCoins() {
		return numCoins;
	}
	
	public boolean getCrash() {
		return crash;
	}
	
	public void checkCrash() {
		if (game.obstaclePosition(game.getPlayerPositionX(), game.getPlayerPositionY()) != -1) {
			game.setCrashTrue();
		}
	}
	
	public void checkCoinCollision() {
		int coinPos = game.coinPosition(game.getPlayerPositionX(), game.getPlayerPositionY());
		if (coinPos != -1) {
			game.addNumCoins();
			game.substractTotalCoins();
			game.setState(false, coinPos);
		}
	}
	
	public void setCrashTrue() {
		crash = true;
	}
	
	public void update() {
		checkCrash();
		checkCoinCollision();
	}
	
	public void reset() {
		positionX = 0;
		if(game.getRoadWidth() == 5) {
			positionY = 2;
		} else {
			positionY = 1;
		}
		numCoins = 5;
	}
	
	public void addNumCoins() {
		numCoins++;
	}
	
	public void advance() {
		positionX += 1;
	}
	
	public void positionUp() {
		this.positionY--;
	}

	public void positionDown() {
		this.positionY++;
		
	}
}
