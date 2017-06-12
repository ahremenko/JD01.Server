package by.htp.ahremenko.webex.command.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import by.htp.ahremenko.domain.GameCrossesZeros;
import by.htp.ahremenko.webex.command.Command;

public class RegisterPlayerImpl implements Command {

	@Override
	public String execute(String request, GameCrossesZeros theGame) {
		String[] params;
		params = request.split("\\s+");
		StringBuilder msg = new StringBuilder();
		msg.append("Hi, ");
		if (theGame.getResultGame() == -3) { 
			theGame.setPlayer1Name(params[1]);
			msg.append ( theGame.getPlayer1Name()+"." + ( theGame.getWhatPlayerMovement()==1 ? "Make you move, please." : "Please, wait for another player." ) );
		} else if (theGame.getResultGame() == -2) {
			theGame.setPlayer2Name(params[1]);
			msg.append( theGame.getPlayer2Name()+"." + ( theGame.getWhatPlayerMovement()==2 ? "Make you move, please." : "Please, wait for another player." ) );
		}	
     	return msg.toString();
	}	

}
