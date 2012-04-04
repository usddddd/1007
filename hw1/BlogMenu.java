import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Class to manage editing of Blogs and their subcomponents
 * @author Adam Fowler
 *
 */

public class BlogMenu {
	private ArrayList<BlogEntry> entries = new ArrayList<BlogEntry>();
	private Blog currentBlog;
	private BlogEntry currentEntry;
	private Comment currentComment;
	private Scanner in = new Scanner(System.in);

	public BlogMenu(){
		currentBlog = null;
		entries = null;
		currentComment = null;
		currentEntry = null;

	}

	/**
	 * 
	 * @param cBlog the blog the user would like to manipulate
	 */
	public void setBlog(Blog cBlog){
		currentBlog = cBlog;
		entries = cBlog.getEntries();
	}

	/**
	 * displays menu for interacting with a blog and its subcomponents
	 */
	public void displayMenu(){
		String s = "1. List Blog Entries \n2. Add New Entry \n3. Edit Existing Entry \n4. Remove Entry \n5. Add Comment \n6. Browse Entry Comments \n7. Edit Comment \n8. Remove Comment \n9. Exit";
		System.out.println(s);
		System.out.println("Please choose an option(1-9): ");
		String c = in.nextLine();
		if(!c.isEmpty()){
			for(char ch : c.toCharArray()){
				if(!Character.isDigit(ch)){
					System.out.println("Please enter a valid selection");
					displayMenu();
				}
			}
			int choice = Integer.parseInt(c);
			if(choice <= 9 && choice >= 1)
				menuParser(choice);
		}
		else{
			System.out.println("Please enter a valid selection");
			displayMenu();
		}
	}
	
	/**
	 * 
	 * @param userChoice Parses input to displayMenu and calls method based on user input
	 */

	public void menuParser(int userChoice){
		switch(userChoice){
		case(1): listEntries();
		break;
		case(2): addEntry();
		break;
		case(3): editEntry();
		break;
		case(4): removeEntry();
		break;
		case(5): addComment();
		break;
		case(6): listEntryComments();
		displayMenu();
		break;
		case(7): editComment();
		break;
		case(8): removeComment();
		break;
		case(9): return;
		}
	}

	/**
	 * lists all entries in the current blog
	 */
	public void listEntries(){
		Collections.sort(entries);
		for(BlogEntry b: entries)
			System.out.println(b);
				displayMenu();
	}

	/**
	 * Allows user to choose a blog entry by title 
	 */
	private void chooseEntry(){
		System.out.println("Please enter the title of the entry: ");
		String title = getInput().toLowerCase();
		boolean found = false;
		for(BlogEntry b: entries){
			if(b.getTitle().toLowerCase().equals(title)){
				found = true;
				currentEntry = b;
			}
		}
		if(!found){
			System.out.println("Sorry, no such entry");
			displayMenu();
		}


	}

	/**
	 * Allows user to add a new entry to the blog
	 */
	public void addEntry(){
		System.out.println("What is the title of the entry: ");
		String t = getInput();
		System.out.println("Who is the author of the entry: ");
		String a = getInput();
		System.out.println("What is the content of the entry: ");
		String c = getInput();
		currentBlog.addEntry(new BlogEntry(t, a, c));
		displayMenu();
	}

	/**
	 * Allows user to edit an existing blog entry
	 */
	public void editEntry(){
		chooseEntry();
		System.out.println("What is the new title of the entry(blank to leave unchanged): ");
		String t = getInput();
		System.out.println("Who is the new author of the entry(blank to leave unchanged): ");
		String a = getInput();
		System.out.println("What is the new content of the entry(blank to leave unchanged): ");
		String c = getInput();
		if(!t.equals(""))
			currentEntry.setTitle(t);
		if(!a.equals(""))
			currentEntry.setAuthor(a);
		if(!t.equals(""))
			currentEntry.setContent(c);
		displayMenu();
	}

	/**
	 * Allows user to choose an entry to remove from the blog
	 */
	public void removeEntry(){
		chooseEntry();
		currentBlog.removeEntry(currentEntry);
		displayMenu();
	}

	/**
	 * Allows user to add a comment to an entry
	 */
	public void addComment(){
		chooseEntry();
		System.out.println("Who is the author of this comment: ");
		String a = getInput();
		System.out.println("What is the content of this comment: ");
		String c = getInput();
		currentEntry.addComment(new Comment(a, c));
		displayMenu();
	}

	/**
	 *  Internal method to facilitate selection of a comment
	 * @return Comment to be manipulated
	 */
	private Comment chooseComment(){
		listEntryComments();
		System.out.println("Please enter the number of a comment: ");
		String s = in.next();
		for(char ch: s.toCharArray()){
			if(!Character.isDigit(ch)){
				System.out.println("Invalid comment number!");
				displayMenu();
			}
		}
		int i = Integer.parseInt(s);
		i--;
		if(i >= currentEntry.getComments().size() || i < 0){
			System.out.println("Invalid comment number!");
			currentComment = null;
			displayMenu();
		}
		else
			currentComment = currentEntry.getComment(i);
		return currentComment;

	}

	/**
	 * Allows user to remove a comment
	 */
	public void removeComment(){
		chooseComment();
		currentEntry.removeComment(currentComment);
		displayMenu();
	}

	/**
	 * Allows user to edit an existing comment
	 */
	public void editComment(){
		if(currentEntry.equals(null))
			chooseEntry();
		chooseComment();
		System.out.println("Please enter new content for this comment(leave blank to remain unchanged): ");
		String cont = getInput();
		if(!cont.equals(""))
			currentComment.setContent(cont);
		displayMenu();

	}

	/**
	 * Internal method to standardize user interaction
	 * @return String s which is the user's input
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
	 * lists all comments of a given entry
	 */
	public void listEntryComments(){
		chooseEntry();
		int i = 1;
		ArrayList<Comment> comments = currentEntry.getComments();
		Collections.sort(comments);
		for(Comment c: comments){
			System.out.println( String.valueOf(i) + ". " + c);
			i++;
		}
	}

	/**
	 * returns user to main menu
	 */
	public void exitToMain(){
		return;
	}

}
