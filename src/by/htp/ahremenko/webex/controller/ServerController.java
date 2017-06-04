package by.htp.ahremenko.webex.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
	private int port;
	private ServerSocket server;
	
	public ServerController (int port) throws IOException {
		server = new ServerSocket (port);
	}
	
	public void start() {
		Socket socket = null;
		
		try {
			System.out.println("[Server] is waiting for connection..");
			socket = server.accept();
			System.out.println("[Server] Client got a connection.");
			
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
				
			DataInputStream dataIn = new DataInputStream (in);
			DataOutputStream dataOut = new DataOutputStream(out);
						
			String clientRequest = dataIn.readUTF();
						
			System.out.println("[Server] got client's request: " + clientRequest);
			String responseToClient;
			responseToClient = "server answer";
			dataOut.writeUTF(responseToClient);
			dataOut.flush();
			System.out.println("[Server] sent a response to Client: " + responseToClient);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
