import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Competition.Competition;

public class SocketServer {
	int portNumber = 44444;
	ServerSocket serverSocket= null;

	// competition injected as an array list
	public void RunServer(Competition competition) {

			try {
				serverSocket = new ServerSocket(portNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			while(true) {
				//Waiting for Client Connection
				try {
					// server socket accepting connection from clients
					Socket clientSocket = serverSocket.accept();
					// creating a new thread for client connection
					// CompetitionRunnable implements Runnable interface
					new Thread(new CompetitionRunnable(clientSocket, competition)).start();
					//Connection just happened
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
	}
}
