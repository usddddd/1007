import java.util.ArrayList;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * Class to represent a blog 
 * @author Adam Fowler
 *
 */
public class Blog implements Comparable<Blog>, Serializable{
	private String title;
	private String author;
	private ArrayList<BlogEntry> entries = new ArrayList<BlogEntry>();
	private Date date = new Date();

	Blog(String blogTitle, String blogAuthor){
		title = blogTitle;
		author = blogAuthor;
	}

	/**
	 * 
	 * @return the title of the Blog
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title the title of the Blog
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return name of Blog's author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @param author name of author of Blog
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 
	 * @return a list of all entries
	 */
	public ArrayList<BlogEntry> getEntries() {
		return entries;
	}

	/**
	 * 
	 * @param i index of entry to be returned
	 * @return entry at index i
	 */
	public BlogEntry getEntry(int i){
		return entries.get(i);
	}

	/**
	 * 
	 * @param entry a BlogEntry to be added to the Blog
	 */
	public void addEntry(BlogEntry entry) {
		this.entries.add(entry);
	}

	/**
	 * 
	 * @param BlogEntry to be removed
	 */
	public void removeEntry(BlogEntry b) {
		entries.remove(b);
	}

	/**
	 * 
	 * @return timestamp when Blog was created
	 */
	public String getTimeStamp(){
		DateFormat df = DateFormat.getDateTimeInstance();
		return df.format(date);
	}

	public String toString(){
		String s = this.title + "\n\t" + "by: " + this.author + "\n\t" + "on " + this.getTimeStamp();
		for(BlogEntry e : this.entries)
			s+= "\n" + e.toString();
				return s;
	}
	
	/**
	 * Allow comparison of Blog's by timestamp
	 */
	public int compareTo(Blog b){
		return this.date.compareTo(b.getDate());
	}
	
	public Date getDate(){
		return (Date)this.date.clone();
	}

}
