import java.util.Scanner;

public class HumanPlayer implements Player {



	private static Scanner in = new Scanner(System.in);
	boolean hit;
	boolean sunk;



	@Override
	public Location placeShip(int size, boolean retry) {
		
		int x = -1;
		int y = -1;
		int max = 10 - size;
		boolean isHorizontal = false;

		if(retry)
			System.out.println("Prior ship placement invalid, please try different coordinates!");

		/**Check for valid coordinates and set instantiated Ship to instantiated MapCell to pass back to caller
		 * 
		 */
		System.out.println("Would you like to place this ship horizontally(yes or no): ");
		String ans = in.next();
		if(ans.equalsIgnoreCase("yes"))
			isHorizontal = true;
		
		// Ask for valid coordinates and only pass them if they are valid
		if(isHorizontal){
			while(y < 0 || y >= max){
				System.out.println("Please enter an X coordinate between 0 and 9 and a Y coordinate between 0 and " + max + " (form: x y): ");
				x = Integer.parseInt(in.next());
				y = Integer.parseInt(in.next());
			}			
		}
		else 
			while(x < 0 || x >= max){
				System.out.println("Please enter an X coordinate between 0 and " + max + " and a Y coordinate between 0 and 9(form: x y): ");
				x = Integer.parseInt(in.next());
				y = Integer.parseInt(in.next());
			}

		Ship s = new Ship(size);
		MapCell c = new MapCell(x,y);
		c.setShip(s, isHorizontal);

		return c;
	}



	@Override
	public Location getTarget() {
		if(hit){
			System.out.println("Your last shot was a hit!");
		}
		if(sunk){
			System.out.println("Your last shot sunk a ship!");
		}
		int x = -1;
		int y = -1;
		while(x < 0 || x > 9 || y < 0 || y > 9){
			System.out.println("Please enter coordinates between 0 and 9 in the form (x y): ");
			x = Integer.parseInt(in.next());
			y= Integer.parseInt(in.next());
		}
		return new MapCell(x, y);

	}

	@Override
	public void setResult(boolean hit, boolean sunk) {
		
		//check if location has a boat
		this.hit = hit;
		//check if boat is sunk
		this.sunk = sunk;
	}

}
