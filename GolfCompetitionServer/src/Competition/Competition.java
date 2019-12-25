package Competition;
import java.util.ArrayList;

// Competition implements 3 interfaces to provide needed functionalities
public class Competition implements IcompetitionAddable, IcompetitionScorable,IcompetitionHttpReturnable
{
   private ArrayList<Person> List1 = new ArrayList<Person>();
	
   public void addPerson(Person p1 ) {
	   List1.add(p1);
   }

   public Person getPerson(int n ) {
	   Person ret = List1.get(n);
	   return ret;
   }

   public int size() {
	   return List1.size();
   }
   // method providing current winner
   public Person getWinner() {
		Person winner =  List1.get(0);
		int bestScore = winner.score;
		for (int i =0; i < List1.size(); i++) {
			Person tmp1 = List1.get(i);
			if (tmp1.score < bestScore){
				winner = tmp1;
			}
		}
		return winner;
   }
   // method returning string for web response
   public String getBoard() {
		String httpResponse = "HTTP/1.1 200 Ok\r\n\r\n" + "<h1>Competition Leaderboard</h1>\n";
		for (int i =0; i < this.List1.size(); i++) {
			Person p3 = List1.get(i);
			httpResponse += "<h2><b>Participant</b> "+ p3.name + " <b>Code</b> "+ p3.code
					+ " <b>Score</b> "+ p3.score +"<h2>\n";
		}
	   return httpResponse;
   }
   // method to set the score
   public void setScore(String compCode,int compScore) {
		for (int i =0; i < this.List1.size(); i++) {
			Person tmp1 = this.List1.get(i);
			if (tmp1.code.equals(compCode)){
				tmp1.score = compScore;
				int position = this.List1.indexOf(tmp1);
				this.List1.set(position,tmp1);
			}
		}
   }  
}
