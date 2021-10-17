package es.ucm.tp1.logic;

import java.text.DecimalFormat;
import java.util.Random;

import es.ucm.tp1.control.Level;

public class Game {
	
	private Player player;
	
	private Level level;
	
	private Random random;
	
	private static final String PLAYER_ICON = ">";
	
	private static final String OBSTACLE_ICON = "░";
	
	private static final String COIN_ICON = "¢";
	
	private static final String DEAD_PLAYER = "@";
	
	private static final String FINISH_LINE = "¦";
	
	private ObstacleList obstacleList;
	
	private CoinList coinList;
	
	private boolean win;
	
	private boolean exit;
	
	private boolean test;
	
	private char currentCommand;
	
	private long initialTime;
	
	private boolean firstLoop;
	
	private boolean advance;
	
	private int cycle;

	private DecimalFormat df;

	public Game(long seed, Level level) {
		this.level = level;
		this.player = new Player(this);
		this.random = new Random(seed);
		test = false;
		win = false;
		exit = false;
		firstLoop = true;
		obstacleList = new ObstacleList();
		this.coinList = new CoinList(getRoadLength());
		df = new DecimalFormat("#.##");
		cycle = 0;
		addElements();
	}
	
	public int getPlayerPositionX() {
		return player.getPositionX();
	}
	
	public int getPlayerPositionY() {
		return player.getPositionY();
	}
	
	public int getCoinCounter() {
		return coinList.getCoinCounter();
	}
	
	public int getObstacleCounter() {
		return obstacleList.getObstacleCounter();
	}
	
	public int getPlayerCoins() {
		return player.getCoins();
	}
	
	public void addNumCoins() {
		player.addNumCoins();
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
	
	public double getRandomNumber() {
		return random.nextDouble();
	}
	
	public int getRandomLane() {
		return (int) (getRandomNumber() * getRoadWidth());
	}
	
	public void toggleTest() {
		 test = true;
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
	
	public boolean getCrash() {
		return player.getCrash();
	}
	
	public boolean getWin() {
		return win;
	}
	
	public boolean getExit() {
		return exit;
	}
	
	public boolean getTest() {
		return test;
	}
	
	public char getCurrentCommand() {
		return currentCommand;
	}
	
	public long getInitialTime() {
		return initialTime;
	}
	
	public boolean getFirstLoop() {
		return firstLoop;
	}
	
	public void setWin(boolean res) {
		win = res;
	}
	
	public void setExit(boolean res) {
		exit = res;
	}
	
	
	public void setCrashTrue() {
		player.setCrashTrue();
	}
	
	public void setCurrentCommand(char command) {
		currentCommand = command;
	}
	
	public void setInitialTime(long iniTime) {
		initialTime = iniTime;
	}
	
	public void incrementCycle() {
		cycle++;
	}
	
	public void setFirstLoop(boolean val) {
		firstLoop = val;
	}
	
	public void setState(boolean state, int ind) {
		coinList.setCoinState(state, ind);
	}
	
	public void substractTotalCoins() {
		coinList.substractTotalCoins();
	}
	
	public void update() {
		player.update();
	}
	
	public void reset() {
		player.reset();
		setFirstLoop(true);
		coinList.resetCoins();
	}
	
	
	
	public boolean emptyPos(int x, int y) {
		boolean res = true;
		
		if (obstaclePosition(x, y) != -1 || 
				(getPlayerPositionX() == x && getPlayerPositionY() == y)) {
			res = false;
		} 
		return res;
	}
	
	public boolean tryToAddObstacle(Obstacle obstacle, double frequency) {
		if(random.nextDouble() < frequency && emptyPos(getPlayerPositionY(), 
				getPlayerPositionX())) {
			obstacleList.setNewObstacle(obstacle);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean tryToAddCoin(Coin coin, double frequency) {
		if(random.nextDouble() < frequency && emptyPos(getPlayerPositionY(), 
				getPlayerPositionX())) {
			coinList.setNewCoin(coin);
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
		coinList.setTotalCoins(coinList.getCoinCounter());
	}
	
	public String getGameStatus() {
		double seconds;
		String res = "";
		
		if (firstLoop) {
			setInitialTime(System.currentTimeMillis());
			firstLoop = false;
		} else {
			res = "[DEBUG] Executing: " + currentCommand + "\n";
		}
		seconds = (System.currentTimeMillis() - initialTime) / 1000.0;
		
		res += "Distance: " + (level.getLength() - getPlayerPositionX()) + "\n" +
				"Coins: " + getPlayerCoins() + "\n" +
				"Cycle: " + cycle + "\n" +
				"Total Obstacles: " + getObstacleCounter() + "\n" +
				"Total Coins: " + getCoinCounter() + "\n";
		
		if (!test) {
			res += "Ellapsed time: " + df.format(seconds) + " s";
		}
		
		return res;
	}

	public String positionToString(int j, int i) { 
		int coinPos = coinPosition(j, i);
		
		if(j == player.getPositionX() && i == player.getPositionY()) {
			if (player.getCrash()) {
				return DEAD_PLAYER;
			} else {
				return PLAYER_ICON;
			}
		}
		else if(obstaclePosition(j, i) != -1) {
			return OBSTACLE_ICON;
		}
		else if(coinPosition(j, i) != -1 && coinList.getCoin(coinPos).getState()) {
			return COIN_ICON;
		} else if (j == getRoadLength()) {
			return FINISH_LINE;
		}
		else {
			return "";
		}
	}
	
	public int obstaclePosition(int j, int i) {
		int pos = -1;
		for(int k = 0; k < obstacleList.getObstacleCounter(); k++) {
			if(obstacleList.getObstacle(k).getObstaclePositionX() == j 
					&& obstacleList.getObstacle(k).getObstaclePositionY() == i) {
				pos = k;
			}
		}
		return pos;
	}
	public int coinPosition(int j, int i) {
		int position = -1;
		for(int l = coinList.getTotalCoins() - 1; l >= (coinList.getTotalCoins() - coinList.getCoinCounter()) ; l--) {
			if(coinList.getCoin(l).getCoinPositionX() == j
					&& coinList.getCoin(l).getCoinPositionY() == i 
					&& coinList.getCoin(l).getState() == true) {
				position = l;
			}
		}
		return position;
	}
}


