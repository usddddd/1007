

public class BattleShipGame implements Game {
	private GameMap p1Board, p2Board;
	private Player p1, p2;

	private final int[] ships = {Game.CARRIER, Game.BATTLESHIP, Game.CRUISER, Game.DESTROYER, Game.SUBMARINE};

	public BattleShipGame(){

		p1Board = new GameMap(SIZE);
		p2Board = new GameMap(SIZE);
	}

	@Override
	public void initialize(Player p1, Player p2) {

		boolean retry1 = false;
		boolean retry2 = false;
		Location l = null;
		this.p1 = p1;
		this.p2 = p2;

		for(int i: ships){
			{
				l = p1.placeShip(i, retry1);
				while(p1Board.addShip(l) == null || !isValid(l, i)){
					retry1 = true;
					l = p1.placeShip(i, retry1);
				}
				l = p2.placeShip(i, retry2);
				while(p2Board.addShip(l) == null || !isValid(l, i)){
					retry2 = true;
					l = p2.placeShip(i, retry2);
				}
			}
		}

	}

	@Override
	public Player playGame() {

		Location l = null;

		int count = 1;
		/**
		 * while there are ships left in play prompt players for their shots, check validity of shots, and inform players of result 
		 */
		while(p1Board.hasShips() && p2Board.hasShips()){
			while(!isValid(l, 0)){
				l = p1.getTarget();
			}
			Location p1Shot = l;
			l= null;
			while(!isValid(l, 0)){
				l = p2.getTarget();
			}
			Location p2Shot = l;
			p2.setResult(p1Board.shipHit(p2Shot), p1Board.shipSunk(p2Shot));
			p1.setResult(p2Board.shipHit(p1Shot), p2Board.shipSunk(p1Shot));
			count++;
		}
		System.out.println("count is " + count);
		if(p1Board.hasShips()){
			return p1;
		}
		else
			return p2;
	}

	/**
	 * Checks for valid ship placement
	 * @param l Location of top left corner of ship to be placed
	 * @param size size of ship to be placed
	 * @return true if placement is valid
	 */
	private boolean isValid(Location l, int size){
		if(l == null)
			return false;
		if(l.isShipHorizontal()){
			if(l.getX() < 0 || l.getX() >= SIZE || l.getY() < 0 || l.getY() + size >= SIZE)
				return false;
		}
		else if(l.getY() < 0 || l.getY()>= SIZE || l.getX() < 0 || l.getX()  + size  >= SIZE)
			return false;

		return true;
	}



}
