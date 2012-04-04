

/**
 * Class to represent a restaraunt receipt
 * @author Adam Fowler
 *
 */
public class Receipt extends ItemList{
	private double totalCost;

	public Receipt(){
		super();
		this.totalCost = 0;
	}

	/**
	 * Add MenuItem m to receipt
	 */
	public void addItem(MenuItem m){
		super.addItem(m);
		this.totalCost += m.getCost();
	}

	/**
	 * 
	 * @return Sum of cost of each item in receipt
	 */
	public double getTotalCost(){
		return this.totalCost;
	}
	
	/**
	 * Clear receipt of items
	 */
	public void clear(){
		super.clear();
		this.totalCost = 0;
	}
	
	public String toString(){
		String totalCostString = String.format("$%.2f", totalCost);
		return super.toString() + "\n\t Total: " + totalCostString;
	}

}
