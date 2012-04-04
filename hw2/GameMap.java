/**
 * This is the game's board, it is comprised of MapCells which hold information about the location of ships
 * This class keeps track of validity of shots and number of ships still floating
 * @author Adam
 *
 */
public class GameMap {
	private MapCell[][] map;
	private int numShips;
	private int boardSize;

/**
 * 
 * @param boardSize Size of board to use
 */
	public GameMap(int boardSize){
		map = new MapCell[boardSize][boardSize];
		for(int i = 0; i < boardSize; i++)
			for(int j = 0; j < boardSize; j++){
				map[i][j] = new MapCell(i, j);
			}
		numShips = 0;
		this.boardSize = boardSize;
	}
/**
 * 
 * @param l Location where ship should be added
 * @return Location where ship was added or null if the placement was invalid
 */
	public Location addShip(Location l){
		MapCell c = (MapCell)l;
		int size = c.getShip().getSize();

		if(!isValid(c, size))
			return null;
		// Point MapCell at location of c.x and c.y through either c.x +i or c.y +i depending on ship's orientation to ship referenced in c.ship 
		for(int i = 0; i < size; i++){
			if(c.isShipHorizontal())
				map[c.getX()][c.getY() + i].setShip(c.getShip(), c.isShipHorizontal());
			else
				map[c.getX() + i][c.getY()].setShip(c.getShip(), c.isShipHorizontal());
		}
		numShips++;

		return c;
	}

/**
 * 
 * @param l Location of the top left corner of a ship to be checked for validity
 * @param size Size of the ship being placed at the Location
 * @return true if ship is in valid location
 */
	private boolean isValid(Location l, int size){

		if(l.isShipHorizontal()){
			if(l.getX() < 0 || l.getX() >= boardSize || l.getY() < 0 || l.getY() + size >= boardSize)
				return false;
		}
		else if(l.getY() < 0 || l.getY()>= boardSize || l.getX() < 0 || l.getX()  + size  >= boardSize)
			return false;

		return shipOverlaps(l, size);
	}

	/**
	 * 
	 * @param l Location of the top left corner of a ship to be checked for validity
	 * @param size Size of the ship being placed at the Location
	 * @return true if ship overlaps another ship
	 */
	private boolean shipOverlaps(Location l, int size){
		for(int i = 0; i < size; i++){
			if(l.isShipHorizontal() && !map[l.getX() ][l.getY() + i].isEmpty()){
				return false;
			}
			else if(!map[l.getX() + i][l.getY()].isEmpty())
				return false;
		}
		return true;
	}

	/**
	 * 
	 * @param l Location of shot
	 * @return true if a ship occupies that location and hasn't been hit at that location already
	 */
	public boolean shipHit(Location l){
		// pass location and 0 as size because it is a shot not a ship
		if(!isValid(l, 0))
			return false;
		MapCell c = map[l.getX()][l.getY()];
		if(c.isEmpty() || c.isHit())
			return false;
		else {
			c.getShip().incrementHits();
			c.setHit(true);
		}
		return true;

	}

	/**
	 * 
	 * @param l Location of ship
	 * @return true if ship has number of hits equal to its size
	 */
	public boolean shipSunk(Location l){
		// pass location and 0 as size because it is a shot not a ship
		if(!isValid(l, 0))
			return false;
		MapCell c = map[l.getX()][l.getY()];
		if(!c.isEmpty() && c.getShip().isSunk()){
			numShips--;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return true if board still has ships
	 */
	public boolean hasShips(){
		return numShips > 0;
	}


}
