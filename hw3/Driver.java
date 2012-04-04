import java.util.ArrayList;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args){
		

		if(args.length != 1){
			System.out.println("Usage: java Driver [filename]");
			return;
		}
		String filename = args[0]  ;
		MenuBuilder menuBuilder = new MenuBuilder(filename);
		Menu menu = menuBuilder.buildMenu();
		Display d = new Display(menu);
		d.displayBox();
	}
}
