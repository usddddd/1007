import java.util.Scanner;


public class Driver {
	public static final Scanner in = new Scanner(System.in);
	static String ans;
	static Player p1, p2;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Is Player 1 human or computer:");
		ans = in.next();
		if(ans.equalsIgnoreCase("human")){
			p1 = new HumanPlayer();
		}
		else
			p1 = new ComputerPlayer();

		System.out.println("Is Player 2 human or computer:");
		ans = in.next();
		if(ans.equalsIgnoreCase("human")){
			p2 = new HumanPlayer();
		}
		else
			p2 = new ComputerPlayer();

		BattleShipGame g = new BattleShipGame();
		g.initialize(p1, p2);
		Player winner = g.playGame();
		if(winner.equals(p1))
			System.out.println("Winner is Player 1!");
		else
			System.out.println("Winner is Player 2!");

	}

}
