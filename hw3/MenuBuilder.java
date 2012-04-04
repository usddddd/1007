import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

/**
 * Class to read file and build menu
 * @author Adam Fowler
 *
 */
public class MenuBuilder {
	private String filename;
	private Menu menu;
	private Scanner in;

	public MenuBuilder(String filename){
		this.filename = filename;
	}

	/**
	 * 
	 * @return Menu constructed from file contents
	 */
	public Menu buildMenu(){
		menu = new Menu();
		String name = "";
		int lineNum = 1;
		try{
			File f = new File(filename);
			in = new Scanner(f);
			while(in.hasNext()){
				if(lineNum%2 != 0){
					 name = in.nextLine();
					 lineNum++;
				}
				else{
					double cost = Double.parseDouble(in.nextLine());
					menu.addItem(new MenuItem(name, cost));
					lineNum++;
				}
			}

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		finally{
			in.close();
		}

		return menu;
	}

}
