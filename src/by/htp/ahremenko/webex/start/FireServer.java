package by.htp.ahremenko.webex.start;

import java.io.IOException;

import by.htp.ahremenko.webex.controller.ServerController2;

public class FireServer {

	public static void main(String[] args) {
		
		/*ServerController server;
		try {
			server = new ServerController (1234);
			server.start();			
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		ServerController2 server;
		try {
			server = new ServerController2 (1235);
			server.start();			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
