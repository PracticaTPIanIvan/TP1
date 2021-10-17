package es.ucm.tp1.logic;

public class CoinList {
	private Coin [] list;
	private int count;
	private int totalCoins;
	
	public CoinList(int roadLength) {
		list = new Coin [roadLength];
		count = 0;
	}
	
	public int getCoinCounter() {
		return count;
	}
	
	public Coin getCoin(int ind) {
		return list[ind];
	}
	
	public int getTotalCoins() {
		return totalCoins;
	}
	
	public boolean getCoinState(int ind) {
		return list[ind].getState();
	}
	
	public void setCoinState(boolean state, int ind) {
		list[ind].setState(state);
	}
	
	public void substractTotalCoins() {
		count--;
	}
	
	public void setNewCoin(Coin coin) {
		list[count] = coin;
		count++;
	}
	
	public void setTotalCoins(int num) {
		totalCoins = num;
	}
	
	public void resetCoins() {
		for (int i = 0; i < totalCoins; i++) {
			if (list[i].getState() == false) {
				list[i].setState(true);
			}
		}
		count = totalCoins;
		
		
	}
}
