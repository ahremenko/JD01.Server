package by.htp.ahremenko.webex.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.htp.ahremenko.domain.GameController;

public class ServerController3 {

	private int port;
	private ServerSocket server;
	private static long clientCounter = 0;
	private Map<Long, ClientRequestHandler> clients;
	private static CommandProvider provider = null;

	
	public ServerController3 (int port) throws IOException {
		this.port = port;
		server = new ServerSocket (port);
		this.clients = new ConcurrentHashMap<>();
		this.provider = CommandProvider.getInstance();
	}
	
	public void start() {
		Socket socket = null;
		GameController gameController = new GameController();
		
		try {
			while (true) {
				System.out.println("[Server] is waiting for connection..");
				socket = server.accept();
				System.out.println("[Server] is starting threading process...");
				createHandler(socket, gameController);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	private void createHandler( Socket socket, GameController gameController) {
		clientCounter++;
		ClientRequestHandler clientHandler = new ClientRequestHandler (socket, clientCounter, this, provider, gameController );
		new Thread (clientHandler).start();
		clients.put(clientCounter, clientHandler);
	}
	
	public void deleteHandler (long handlerKey) {
		clients.remove(handlerKey);
	}

}
