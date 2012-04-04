/**
 * Class to represent a menu item
 * @author Adam Fowler
 *
 */
public class MenuItem {
	private String name;
	private double cost;
	
	public MenuItem(String n, double c){
		this.name = n;
		this.cost = c;
	}
	
	/**
	 * 
	 * @return Name of item
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @return Cost of item
	 */
	public double getCost(){
		return this.cost;
	}
}
