/**
 * This class represents a Map cell with references to any ships currently located at its location and
 * its state information(ie whether it has a ship, whether it has been previousy shit)
 * @author Adam
 *
 */
public class MapCell implements Location {
	private int x, y;
	private boolean isShipHorizontal = false;
	private boolean isEmpty;
	private boolean isHit;

	private Ship ship;



	public MapCell(int x, int y){
		this.x = x;
		this.y = y;
		this.isEmpty = true;
		this.isHit = false;

	}

	// Doesn't currently hold a ship
	public boolean isEmpty() {
		return isEmpty;
	}



	private void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}


	//Has been hit with as hot
	public boolean isHit() {
		return isHit;
	}



	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}



	public Ship getShip() {
		return ship;
	}


	//Set ship this location should reference
	public void setShip(Ship ship, boolean isHorizontal) {
		setShipHorizontal(isHorizontal);
		this.ship = ship;
		setEmpty(false);
	}



	public void setShipHorizontal(boolean isShipHorizontal) {
		this.isShipHorizontal = isShipHorizontal;
	}



	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public boolean isShipHorizontal() {
		// TODO Auto-generated method stub
		return isShipHorizontal;
	}

}
