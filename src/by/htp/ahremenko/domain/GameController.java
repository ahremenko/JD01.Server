package by.htp.ahremenko.domain;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import by.htp.ahremenko.webex.controller.ClientRequestHandler;

public class GameController {
	private Map<Long, GameCrossesZeros> gamepool = new HashMap();
	
	public GameCrossesZeros createGame( Long id1 ) {
		GameCrossesZeros gameHandler = new GameCrossesZeros ( id1 );
		this.gamepool.put(id1, gameHandler); 
		return gameHandler;
	}
	
	public GameCrossesZeros joinTheGame( Long id2 ) {
		GameCrossesZeros gameHandler;
		try {
			gameHandler = gamepool.get(id2-1);
			gameHandler.joinToGame(id2);
			return gameHandler;
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	
	public void deleteGame (long idPlayer1 ) {
		gamepool.remove(idPlayer1);
	}

}
