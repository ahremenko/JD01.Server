package by.htp.ahremenko.webex.command;

import by.htp.ahremenko.domain.GameCrossesZeros;

public interface Command {
	public String execute (String request, GameCrossesZeros theGame);
}
