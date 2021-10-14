package es.ucm.tp1.logic;

import java.util.Random;

import es.ucm.tp1.control.Level;

public class Game {
	
	private Player player;
	
	private Level level;
	
	private Obstacle [] obstacleList;
	
	private Coin [] coinList;
	
	private Random random;
	
	private static final String PLAYER_ICON = ">";
	
	private static final String OBSTACLE_ICON = "░";
	
	private static final String COIN_ICON = "¢";
	
	private int coinCount;
	
	private int obstacleCount;

	public Game(long seed, Level level) {
		this.level = level;
		this.player = new Player();
		this.random = new Random(seed);
		this.obstacleCount = 0;
		this.obstacleList = new Obstacle [100];
		this.coinCount = 0;
		this.coinList = new Coin [100];
		addElements();
	}
	
	public int getPlayerPositionX() {
		return player.getPositionX();
	}
	
	public int getPlayerPositionY() {
		return player.getPositionY();
	}
	
	public int getCoinCounter() {
		return coinCount;
	}
	
	public int getObstacleCounter() {
		return obstacleCount;
	}
	
	public int getPlayerCoins() {
		return player.getCoins();
	}
	
	public void advance() {
		player.advance();
	}
	
	public void moveUp() {
		player.positionUp();	
	}
	
	public void moveDown() {
		player.positionDown();
	}
	
	public int getRandomLane() {
		return random.nextInt(getRoadWidth());
	}
	
	public void toggleTest() {
		 
	}
	
	public int getVisibility() {
		return 8;
	}
	
	public int getRoadWidth() {
		return level.getWidth();
	}
	
	public int getRoadLength() {
		return level.getLength();
	}
	
	public double getObstacleFrequency() {
		return level.getObstacleFrequency();
	}
	
	public double getCoinFrequency() {
		return level.getCoinFrequency();
	}
	
	public boolean tryToAddObstacle(Obstacle obstacle, double frequency) {
		if(random.nextDouble() < frequency) {
			obstacleList[obstacleCount] = obstacle;
			obstacleCount++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean tryToAddCoin(Coin coin, double frequency) {
		if(random.nextDouble() < frequency) {
			coinList[coinCount] = coin;
			coinCount++;
			return true;
		}
		else {
			return false;
		}
	}
	
	public void addElements() {
		for (int x = getVisibility() / 2; x < level.getLength(); x++) {
			tryToAddObstacle(new Obstacle(x, getRandomLane(), this),
			getObstacleFrequency());
			
			tryToAddCoin(new Coin(x, getRandomLane(), this),
			getCoinFrequency());
		}

	}
	
	public String getGameStatus() {
		return "";
	}

	public String positionToString(int j, int i) { 
		if(j == player.getPositionX() && i == player.getPositionY()) {
			return PLAYER_ICON;
		}
		else if(obstaclePosition(j, i) != -1) {
			return OBSTACLE_ICON;
		}
		else if(coinPosition(j, i) != -1) {
			return COIN_ICON;
		}
		else {
			return "";
		}
	}
	
	public int obstaclePosition(int j, int i) {
		int pos = -1;
		for(int k = 0; k < obstacleCount; k++) {
			if(obstacleList[k].getObstaclePositionX() == j && obstacleList[k].getObstaclePositionY() == i) {
				pos = k;
			}
		}
		return pos;
	}
	public int coinPosition(int j, int i) {
		int position = -1;
		for(int l = 0; l < coinCount; l++) {
			if(coinList[l].getCoinPositionX() == j && coinList[l].getCoinPositionY() == i) {
				position = l;
			}
		}
		return position;
	}
}

