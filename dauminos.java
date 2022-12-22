import java.util.Random;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class dauminos {
	static Random rand = new Random();
	static ArrayList<domino> dominos = new ArrayList<>();
	static ArrayList<domino> board = new ArrayList<>();
	static ArrayList<player> players = new ArrayList<>(4);
	static Scanner input = new Scanner(System.in);
	static ArrayList<domino> tableHorizontal = new ArrayList<>();
	static ArrayList<domino> tableVertical = new ArrayList<>();
	static domino doub = new domino("doubleChecker", 2, 3);

	
	
	
	public static void main(String[]args) {
		int player_count = 0, store_count = 0;
		int gameChoice = 0;

		
		while (player_count > 4 || player_count < 2) {
			System.out.print("Oyunçu sayını daxil edin: ");
			player_count = input.nextInt();
		}
		
		
		store_count = 28 - 7*player_count;
		
		for (int i = 0; i < player_count; i++) {
			System.out.print("Oyunçu adı daxil edin: ");
			player player = new player(input.next());
			players.add(i, player);
		}
		
		int x = 0;
		for (int i = 0; i < 7; i++) {
			for (int j = i; j < 7; j++) {
				domino domino = new domino("domino" + i +j, i, j);
				dominos.add(domino);
				x++;
			}
		}
		
	//	System.out.println("Players:");
		for (int i = 0; i < players.size(); i++) {
	//		System.out.print(players.get(i).name + " : ");	oyuncu adlarini gosterir
			definingHand(players.get(i));
		}
		
//		System.out.println(dominos.size());
//		System.out.println("Daslar:");
//		for (int i = 0; i < players.size(); i++) {
//			System.out.print(players.get(i).name + " ");
//			showHand(players.get(i));
//			System.out.println();
//		}	daslari gosterir
		
		java.util.Collections.shuffle(players);
		
//		for (int i = 0; i < players.size(); i++) {
//			System.out.print(players.get(i).name); (qarisdiqdan sonra oyuncular listesi)
//		}
		
		
		while (gameChoice != 101 && gameChoice != 365) {
			System.out.println("Oynamaq istədiyiniz oyunu seçin:");
			System.out.print("101/365: ");
			gameChoice = input.nextInt();
		}
		boolean isFinish = true;
		int winner = 0;
		switch (gameChoice) {
		
		case 101:
			game101();
			System.out.println(players.get(winner).name + "Won");
			break;
		case 365:
			game365();
			break;
		default:
			break;
		}
				
		

	}
	
	public static void definingHand(player player) {
		for (int i = 0; i < 7; i++) {
			int b = rand.nextInt(dominos.size());
			player.hand.add(dominos.get(b));
			
			dominos.remove(b);
			dominos.trimToSize();
			
		}
	}
	
	public static void showHand(player player) {
		for (int i = 0; i < player.hand.size(); i++) {
			System.out.print(player.hand.get(i).back + "|" + player.hand.get(i).front);
			System.out.print(" ");
		}
		
	}
	
	public static void game101() {
		boolean notFinished = true;
		int fp = 0;
		
		for (int i = 0; i < players.size(); i++) {
			for (domino domino : players.get(i).hand) {
				if (domino.name.equals("domino11"))
					fp = i;
			}
		}
		
		while (notFinished) {
			if (fp == players.size()) {
				fp = 0;
				continue;
			}
			
			turn(players.get(fp));
			printBoard();
			
			fp++;
		}
		
	}
	
	public static void game365() {
		System.out.println("Preparing...");
	}
	
	public static void turn(player player) {
		int choice = 0;
		
		System.out.println("\nOyunçu " + player.name + ", sıra səndədir:");
		for (int i = 0; i < player.hand.size(); i++) {
			System.out.print(player.hand.get(i).back + "|" + player.hand.get(i).front);
			System.out.print(" ");
		}
		System.out.println(player.hand.size());
		System.out.println();
		while (choice < 1 || choice > player.hand.size()) {
			System.out.print("Atmaq istədiyiniz daşı seçin: ");
			choice = input.nextInt();
		}
		
		choice--;
		
		if (player.hand.get(choice).back == player.hand.get(choice).front && doub.back == 3 && doub.front == 2)
			doub = player.hand.get(choice);
		
	//	System.out.print(doub.back + "|" + doub.front);  qosanin gosterilmesi
		
		System.out.println(player.hand.get(choice).back + "|" + player.hand.get(choice).front);  
		if (isValid(player.hand.get(choice)))
			putTable(player, player.hand.get(choice));
	}
	
	public static void printBoard() {
		System.out.println("-------------------------");
		for (int i = 0; i < tableHorizontal.size(); i++) {
			System.out.println(tableHorizontal.get(i).back + "|" + tableHorizontal.get(i).front);
		}
		System.out.println("-------------------------");

	}
	
	public static boolean isValid(domino domino) {
 		if (tableHorizontal.size() == 0 || tableVertical.size() == 0) {
			System.out.println("Ilk Das");
			return true;
		}
		
		if (tableVertical.size() > 0) {
			System.out.println("4 terefe bax");
		} else {
			if (
				domino.back == tableHorizontal.get(0).back ||
				domino.back == tableHorizontal.get(tableHorizontal.size()-1).front ||
				domino.front == tableHorizontal.get(0).back ||
				domino.front == tableHorizontal.get(tableHorizontal.size()-1).front
					)
				return true;
		}
		
		
		return false;
	}
	
	public static void putTable(player player, domino domino) {
		
		player.hand.remove(domino);
		
		if (tableHorizontal.size() == 0)
			tableHorizontal.add(domino);
		else {
			if (domino.back == domino.front)
				tableVertical.add(domino);
			//if ()
		}
		
		printBoard();
	} 
}
