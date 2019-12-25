import Competition.Competition;

public class GolfCompetitionServer {

	public static void main(String[] args) {

		SocketServer s = new SocketServer();
		
		Competition competition =	new Competition();
		// Competition object is injected as a dependency, for dependency inversion principle
		// this will allow usage of different data structures, as long as implementing
		// right interfaces.
		s.RunServer(competition);
	}

}
