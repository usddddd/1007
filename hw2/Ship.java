/**
 * This class represents a ship and contains a ship's state information(ie whether it is sunk, how many hits it has taken)
 * @author Adam
 *
 */
public class Ship {

	private int size;
	private int hits = 0;



	public Ship(int size){
		this.size = size;
	}

	public int getSize(){
		return this.size;
	}

	public boolean isSunk(){
		return hits == size;
	}

	public void incrementHits(){
		hits++;
	}
}

