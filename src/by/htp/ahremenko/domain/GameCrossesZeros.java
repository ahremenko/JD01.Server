package by.htp.ahremenko.domain;

public class GameCrossesZeros {
	private String player1Name;
	private String player2Name;
	private Long idPlayer1;
	private Long idPlayer2;
	private int whatPlayerMovement; // 1 - player1 (set Crosses), 2 - player2 (set Zeros)
	
	private int resultGame;  // 1 - player1 won, 2 - player2 won, 0 - no winner, -1 - game in process, -2 - wait for players registration
	
	private int[] gameField = {0,0,0,0,0,0,0,0,0};
	
	public GameCrossesZeros( Long idP1 ) {
		this.idPlayer1 = idP1;
		this.resultGame = -2;
		this.whatPlayerMovement = 1;
	}
	
	public void joinToGame ( Long idP2) {
		this.idPlayer2 = idP2;
	}
	
	public int getWhatPlayerMovement() {
		return whatPlayerMovement;
	}
	
	public String getPlayer1Name() {
		return player1Name;
	}

	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
		if (this.player2Name != null)
			this.resultGame = -1;
	}

	public String getPlayer2Name() {
		return player2Name;
	}

	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
		if (this.player1Name != null)
			this.resultGame = -1;		
	}

	public void playerMovement( int whatField ) {
	
		if ( gameField[whatField] == 0) {
				gameField[whatField] = whatPlayerMovement;
				whatPlayerMovement = (whatPlayerMovement == 1 ? 2 : 1);
		}
	}
	
	public int getResultGame() {
		return resultGame;
	}

	public void setResultGame(int resultGame) {
		this.resultGame = resultGame;
	}

	public int getWinner() {
		int res = 0;
		if (gameField[0]==gameField[1]&&gameField[1]==gameField[2]&&gameField[0]!=0) {
			res = gameField[0];
		} else if (gameField[3]==gameField[4]&&gameField[4]==gameField[5]&&gameField[3]!=0) {
			res = gameField[3];
		} else if (gameField[6]==gameField[7]&&gameField[7]==gameField[8]&&gameField[6]!=0) {
			res = gameField[6];
		} else if (gameField[0]==gameField[4]&&gameField[4]==gameField[8]&&gameField[0]!=0) {
			res = gameField[0];
		} else if (gameField[0]==gameField[3]&&gameField[3]==gameField[6]&&gameField[0]!=0) {
			res = gameField[0];
		} else if (gameField[1]==gameField[4]&&gameField[4]==gameField[7]&&gameField[1]!=0) {
			res = gameField[1];
		} else if (gameField[2]==gameField[5]&&gameField[5]==gameField[8]&&gameField[2]!=0) {
			res = gameField[2];
		} else if (gameField[2]==gameField[4]&&gameField[4]==gameField[6]&&gameField[2]!=0) {
			res = gameField[2];
		} else {
			for (int i=0; i<gameField.length; i++) {
				if (gameField[i] == 0) {
					res = -1;  /// exists null cells - game in process
					break;
				}
			}
		}
		return res;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<gameField.length; i++) {
			if (i%3==0) {
				sb.append("\n");
			}
			if (gameField[i] == 1) {
			   sb.append(" X ");
			} else if ((gameField[i] == 2)) {
				sb.append(" 0 ");
			} else
				sb.append("   ");
		}
		return sb.toString();
				
		/*
		 *  class GemImpl implements Runnable {
		 * Map<Integer, Socket> clients = ...
		 * 
		 * add (socket s)
		 *    clients(id,s);
		 *    id++;
		 * run()
		 *   Player player = new Player( Socket);
		 *   player.start();
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	}

}
