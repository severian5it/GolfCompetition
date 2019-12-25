import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.omg.CORBA.portable.UnknownException;

public class GolfCompetitionClient {

	public static void main(String[] args) {
		// Server will be at address 127.0.0.1:44444
		String hostname = "127.0.0.1";
		int portNumber = 44444;
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		InputStreamReader ir;
		BufferedReader stdIn;
		
		try {
			while (true) {
				//Instantiate client socket
				clientSocket = new Socket(hostname,portNumber);
				out = new PrintWriter(clientSocket.getOutputStream(),true);
				ir = new InputStreamReader(clientSocket.getInputStream());
				in = new BufferedReader(ir);
				stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				System.out.println("1. Register Player"); 
				System.out.println("2. Enter Score"); 
				System.out.println("3. Who is winning"); 
				System.out.println("Enter Choice"); 
				
				// reading initial choice
				int userInput1 = Integer.parseInt(stdIn.readLine()); 
				
				switch(userInput1) {
					// Creating new Player and generating competition code
					case 1:
						System.out.println("Register"); 
						out.println("register");
						System.out.println("Enter your Name:"); 
						String name = stdIn.readLine();
						out.println(name);
						System.out.println("your comp code is: " + in.readLine()); 
						break;
					// Updating score for a given competition code	
					case 2:
						System.out.println("Enter Score"); 
						out.println("score");
						System.out.println("Enter Comp Code:"); 
						String compCode = stdIn.readLine();
						out.println(compCode);
						System.out.println("Enter Score:"); 
						String compScore = stdIn.readLine();
						out.println(compScore);
						System.out.println(in.readLine()); 
						break;
					// Printing current leader board		
					case 3:		
						System.out.println("Highest Score"); 
						out.println("high");
						System.out.println(in.readLine()); 
						break;
				}	
			}
		} catch (UnknownException e) {
			System.exit(1);
		}
		catch (IOException e) {
			System.exit(1);
		}
	}
}
