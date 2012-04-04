
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Class to represent the main menu the user interacts with to manipulate blogging platform
 * @author Adam Fowler
 *
 */
public class MainMenu {

	private ArrayList <Blog> blogs = new ArrayList<Blog>();
	Scanner in = new Scanner(System.in);
	private Blog currentBlog = null;
	BlogMenu bMenu = new BlogMenu();
	private BlogFileReader bfReader = new BlogFileReader();
	private BlogFileWriter bfWriter = new BlogFileWriter();

/**
 * Displays context menu for user
 */
	public void displayMenu(){
		String s = "1. Add New Blog \n2. Edit Existing Blog \n3. Remove Existing Blog \n4. List Blogs \n5. Browse Blog \n6. Write blog to file \n7. Read blog from file \n8. Exit";
		System.out.println(s);
		System.out.println("Please choose an option(1-8): ");
		String c = in.nextLine();
		if(!c.isEmpty()){
			for(char ch : c.toCharArray()){
				if(!Character.isDigit(ch)){
					System.out.println("Please enter a valid selection");
					displayMenu();
				}
			}
			int choice = Integer.parseInt(c);
			if(choice <= 8 && choice >= 1)
				menuParser(choice);
		}
		else{
			System.out.println("Please enter a valid selection");
			displayMenu();
		}
	}

	/**
	 * Parsers user input to main context menu and maps input to a method call
	 * @param userChoice users input to main menu
	 */
	public void menuParser(int userChoice){
		switch(userChoice){
		case(1): addBlog();
		break;
		case(2): editBlog();
		break;
		case(3): removeBlog();
		break;
		case(4): listBlogs();
		break;
		case(5): browseBlog();
		break;
		case(6): writeBlog();
		break;
		case(7): readBlog();
		break;
		case(8):return;
		}
	}

	/**
	 * Standardized user input method
	 * @return users input as String s
	 */
	private String getInput(){
		String s = "";
		String c;
		System.out.println("Enter text(type quit to stop): ");
		c= in.nextLine();
		while(!c.toLowerCase().contains("quit")){
			s += c;
			c = in.nextLine();
		}
		return s;
	}

	/**
	 * Create an instance of a new blog and add it to ArrayList of blogs
	 */
	public void addBlog(){
		System.out.println("What is the title of the Blog: ");
		String n, a;
		n = getInput();
		System.out.println("Who is the author of the Blog: ");
		a = getInput();
		Blog newBlog = new Blog(n,a);
		blogs.add(newBlog);
		displayMenu();
	}

	/**
	 * Internal method to choose blog based on title
	 */
	private void chooseBlog(){
		System.out.println("Please enter the title of the blog to edit or view: ");
		boolean found = false;
		String title = getInput().toLowerCase();
		for(Blog b: blogs){
			if(title.equals(b.getTitle().toLowerCase())){
				currentBlog = b;
				found = true;
			}
		}
		if(!found){
			System.out.println("Sorry, no such blog exists");
			currentBlog = null;
			displayMenu();
		}

	}

	/**
	 * Method to allow users to edit an existing blog
	 */
	public void editBlog(){	
		chooseBlog();
		System.out.print("What is the new title of the Blog(leave blank to keep old name): ");
		String n = getInput();
		System.out.print("Who is the new author of the Blog(leave blank to keep old author): ");
		String a = getInput();
		if(!n.isEmpty()){
			currentBlog.setTitle(n);
		}
		if(!a.isEmpty()){
			currentBlog.setAuthor(a);
		}
		displayMenu();
	}

	/**
	 * method to allow users to remove an existing blog
	 */
	public void removeBlog(){
		chooseBlog();
		blogs.remove(currentBlog);
		displayMenu();
	}

	/**
	 * Method to enter context menu to manipulate a user choosen blog and its subcomponents
	 */
	public void browseBlog(){
		chooseBlog();
		bMenu.setBlog(currentBlog);
		bMenu.displayMenu();
		displayMenu();
	}



	/**
	 * Method to list all blogs for user sorted by timestamp
	 */
	public void listBlogs(){
		Collections.sort(blogs);
		for(Blog b: blogs){
			System.out.println(b);
		}
		displayMenu();

	}
	
	public void writeBlog(){
		chooseBlog();
		bfWriter.setBlog(currentBlog);
		System.out.println("Please enter the desired filename: ");
		String f = getInput();
		bfWriter.setFileName(f);
		bfWriter.writeBlog();
		displayMenu();
	}
	
	public void readBlog(){
		System.out.println("Please enter the blog's file name: ");
		String f = getInput();
		bfReader.setFileName(f);
		bfReader.readBlog();
		Blog b = bfReader.getBlog();
		blogs.add(b);
		displayMenu();
	}




}

