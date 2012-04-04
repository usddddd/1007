import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to represent each post of a blog
 * @author Adam Fowler
 *
 */

public class BlogEntry implements Comparable<BlogEntry> {
	private String title;
	private String content;
	private String author;
	private Date date = new Date();
	private ArrayList<Comment> comments = new ArrayList<Comment>();
	
	BlogEntry(String entryTitle, String entryAuthor, String entryContent)
	{
		title = entryTitle;
		author = entryAuthor;
		content = entryContent;
	}
	
	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getAuthor() {
		return author;
	}



	public void setAuthor(String author) {
		this.author = author;
	}



	public Comment getComment(int i) {
		return comments.get(i);
	}
	
	public ArrayList<Comment> getComments() {
		return comments;
	}



	public void addComment(Comment c) {
		this.comments.add(c);
	}
	
	public void removeComment(Comment c){
		this.comments.remove(c);
	}



	public String getTimeStamp()
	{
		DateFormat df = DateFormat.getDateTimeInstance();
		String timeStamp = df.format(date);
		
		return timeStamp;
	}
	
	public String toString(){
		return this.title + "\n\t" + "by: " + this.author + "\n\t" + "on: " + this.getTimeStamp() + "\n" + this.getContent();
	}
	
	public int compareTo(BlogEntry b){
		return this.date.compareTo(b.getDate());
	}
	
	public Date getDate(){
		return (Date)this.date.clone();
	}
}
