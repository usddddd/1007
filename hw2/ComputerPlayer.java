import java.util.Random;

public class ComputerPlayer implements Player {

	private int shotX = 0;
	private int shotY = 0;
	private int x, y;
	private boolean hit;
	private Location nextShot;
	private Random randomGenerator = new Random();
	private boolean isHorizontal;



	// random ship placement
	@Override
	public Location placeShip(int size, boolean retry) {
		this.x = randomGenerator.nextInt(10 - size);
		this.y = randomGenerator.nextInt(10 - size);
		if(randomGenerator.nextInt(6) > 2)
			isHorizontal = true;
		else
			isHorizontal = false;
		MapCell c = new MapCell(x, y);
		c.setShip(new Ship(size), isHorizontal);
		return c;
	}

	// Shoot every square, foolproof plan!
	@Override
	public Location getTarget() {

		nextShot = new MapCell(shotX, shotY);
		if(shotY < 9)
			shotY++;
		else if(shotX < 9){
			shotX++;
			shotY = 0;
		}
		else{
			shotX = 0;
			shotY = 0;
		}

		return nextShot;
	}

	@Override
	public void setResult(boolean hit, boolean sunk) {

		if(hit){
			this.hit = true;			
		}
		else
			this.hit = false;


	}



}
