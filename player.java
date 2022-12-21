import java.util.ArrayList;

public class player {
	public String name = "NUll";
	public int id = 0, score = 0;
	public ArrayList<domino> hand = new ArrayList<>();
	
	public player(String ad) {
		this.name = ad;
	}
}
