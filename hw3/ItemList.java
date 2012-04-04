import java.util.ArrayList;

/**
 * Class to represent list of MenuItems
 * @author Adam Fowler
 *
 */
public class ItemList {

	protected ArrayList<MenuItem> menu;

	public ItemList() {
		super();
		menu = new ArrayList<MenuItem>();
	}

	/**
	 * Add item to menu
	 * @param m
	 */
	public void addItem(MenuItem m) {
		menu.add(m);
	}

	/**
	 * 
	 * @param name of MenuItem to return
	 * @return MenuItem
	 */
	public MenuItem getItem(String name) {
		MenuItem m = null;
		for(int i = 0; i < menu.size(); i++){
			if(menu.get(i).getName().equals(name))
				m = menu.get(i);
		}
		return m;
	
	}
	
	/**
	 * 
	 * @param i index of MenuItem
	 * @return MenuItem at i
	 */
	public MenuItem getItem(int i){
		return menu.get(i);
	}
	
	/**
	 * Clear ItemList of items
	 */
	public void clear(){
		menu.clear();
	}

	/**
	 * 
	 * @param name Name of item to remove
	 * @return true if removed false otherwise
	 */
	public boolean removeItem(String name) {
		for(MenuItem m: menu){
			if(m.getName().equalsIgnoreCase(name))
				menu.remove(m);
				return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Number of items in ItemList
	 */
	public int getSize() {
		return menu.size();
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < this.getSize(); i++){
			MenuItem m = menu.get(i);
			String cost = String.format("%.2f", m.getCost());
			s += m.getName() + "\n\t$" + cost + "\n";
		}
		return s;
	}

}
