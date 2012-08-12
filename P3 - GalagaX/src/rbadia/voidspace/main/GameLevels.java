package rbadia.voidspace.main;

/**
 * Represents the game level specifications. 
 * @author Daniel Santiago & Nataira Pag‡n 
 *
 */
public class GameLevels {
	
	/**
	 * Constants.
	 */
	public static final long LEVEL_TIME_INTERVALS = 25000;
	public static final long LEVEL_SCORE_INTERVAL = 2000;
	public static final long BIG_ASTEROID_INTERVAL = 10000;
	
	/**
	 * Instance fields for the game components
	 */
	private GameLogic gameLogic;
	private GameStatus gameStatus;
	
	/**
	 * Instance fields for time use.
	 */
	private long levelTimeInterval = LEVEL_TIME_INTERVALS;
	private long levelScoreInterval = LEVEL_SCORE_INTERVAL;
	private int maxAsteroidInLvl;
	private long lastLevelTime;
	private long lastLevelScore;
	private long bigAsteroidInterval = BIG_ASTEROID_INTERVAL;
	
	/**
	 * GameLevels constructor.
	 * @param gamelogic the game logic handler
	 * @param gameStatus the game status handler
	 */
	public GameLevels(GameLogic gamelogic, GameStatus gameStatus){
		this.gameLogic = gamelogic;
		this.gameStatus = gameStatus;
		this.lastLevelTime = System.currentTimeMillis();
		
	}
	
	/**
	 * Resets the game to level one and the specific features for this level. 
	 */
	public void reset(){
		this.gameStatus.setLevel(1);
		this.maxAsteroidInLvl = 3;
		this.lastLevelTime = System.currentTimeMillis();
		this.lastLevelScore = 0;
		this.bigAsteroidInterval = BIG_ASTEROID_INTERVAL;
		this.levelTimeInterval = LEVEL_TIME_INTERVALS;
		this.levelScoreInterval = LEVEL_SCORE_INTERVAL;
	}
	
	/**
	 * Checks if player should advance to the next level
	 * @param score the score accumulated 
	 */
	public void checkLevel(long score){
		if(gameStatus.isGameStarted()){
			if ((score - lastLevelScore) > levelScoreInterval){
				gameStatus.setLevel(gameStatus.getLevel() + 1);
				lastLevelTime = System.currentTimeMillis();
				maxAsteroidInLvl += 3;
				levelTimeInterval += 5000;
				levelScoreInterval += 2000;
				lastLevelScore = score;
				bigAsteroidInterval -= 1000;
				gameLogic.getEnemyShip().setSwampingInterval(gameLogic.getEnemyShip().getSwampingInterval() - 1000);
				gameLogic.getKamikazeShip().setSwampingInterval(gameLogic.getKamikazeShip().getSwampingInterval() - 2000);
				
				gameStatus.setShowLevelUpString(true);
								
				gameLogic.addAsteroids(2);
			} else if ((System.currentTimeMillis() - lastLevelTime) > levelTimeInterval){
				gameLogic.addAsteroids(1);
				lastLevelTime = System.currentTimeMillis();
			}
		}
		
	}

	/**
	 * Getter for the big asteroid time interval.
	 * @return a long representing the time interval
	 */
	public long getBigAsteroidTimeInterval() {
		return this.bigAsteroidInterval;
	}
	
	/**
	 * Getter for the maximum of asteroids displayed in each level
	 * @return a long representing the maximum of asteroid in level
	 */
	public long getMaxAsteroidInLvl(){
		return maxAsteroidInLvl;
	}

}
