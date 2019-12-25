import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Competition.Competition;
import Competition.Person;

public class CompetitionRunnable implements Runnable {
	
	protected Socket clientSocket = null;
	private Competition competition;

	public CompetitionRunnable(Socket clientSocket,Competition competition) {
		super();
		this.clientSocket = clientSocket;
		this.competition = competition;
	}
	
	@Override
	public void run() {
       try {
	        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
			//Here connection happened
			// reading client input
			switch(in.readLine()){
			case "register":
				String name = in.readLine();
				String code = "0";
				int score = 0;
				// generating new player, genscore method is synchronized
				Person p1 = new Person(name,code,score);
				out.println(p1.code);
				this.competition.addPerson(p1);
				break;
			case "score":
				String compCode;
				int compScore;
				compCode = in.readLine();
				compScore = Integer.parseInt(in.readLine());
				// setting score, access to competition is synchronized to prevent incosistency
				synchronized(competition) {
					
					competition.setScore(compCode,compScore);

				}
				out.println("Score registered");
				break;
			case "high":
				// retrieving leader, access to competition is synchronized to prevent incosistency
				synchronized(competition) {
					if (competition.size() > 0) {
						Person winner = competition.getWinner();
						out.println("Current Leader is " + winner.name +
									" with a score of "  + winner.score);
					} else {
						out.println("No participants so far in the competition");
					}
				}
				break;
			default:
				// retrieving competition board
				this.clientSocket.getOutputStream().write(competition.getBoard().getBytes("UTF-8"));
				break;
		}	
		this.clientSocket.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

}

