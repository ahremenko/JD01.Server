package by.htp.ahremenko.webex.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import by.htp.ahremenko.webex.command.Command;

public class ClientRequestHandler implements Runnable{
	private Socket socket;
	private final long clientNumber;
	private ServerController2 myServer;
	private volatile boolean isRun = true;
	private CommandProvider provider;
	
	public ClientRequestHandler (Socket s, final long cn, ServerController2 srv, CommandProvider prvdr) {
		this.socket = s;
		this.clientNumber = cn;
		this.myServer = srv;
		this.provider = prvdr;
	}
	
	@Override
	public void run() {
		InputStream in;
		OutputStream out;
		DataInputStream dataIn;
		DataOutputStream dataOut;
		String responseToClient;
		String clientRequest;
		
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();
			dataIn = new DataInputStream (in);
			dataOut = new DataOutputStream(out);
			
			Command command;

			while (isRun) {			
				try {
					clientRequest = dataIn.readUTF();
					System.out.println("[Server] got client's request: " + clientRequest);
					
					String[] params = clientRequest.split("\\s+", 2);
					//System.out.println("[Server] recognize command: " + params[0]);
					command = provider.getCommand(params[0]);

					responseToClient = command.execute(clientRequest);
					dataOut.writeUTF(responseToClient);
					dataOut.flush();
					System.out.println("[Server] sent a response to Client: " + responseToClient);
				} catch (IOException e1) {
					stop();
				}
				// todo - process commands here
				// String[] params = clientRequest.split("\\s+",2);   // get two string splitted by " " 
				// commant = provider.GetCommand(params[0]);
				// responseToClient = command.execute(params[1])
				
				// reflection
				// Class exeClass = Class.forName()
				// Method method = exeClass.getMethod( "add", int.class, int.class)
				// Object[] 
				//responseToClient = "server answer";
			}	
		} catch (IOException e) {
			e.printStackTrace();	
		} finally {
			myServer.deleteHandler(clientNumber);
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
	}
	
	public void stop() throws IOException  {
		isRun = false;
		System.out.println("[Server] was stopped.");		
	}
	
}
