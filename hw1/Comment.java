import java.text.DateFormat;
import java.util.Date;

/**
 * Class to represent comments on a blog
 * @author Adam Fowler
 *
 */
public class Comment implements Comparable<Comment> {
	private String author;
	private String content;
	private Date date = new Date();
	
	Comment(String commentAuthor, String commentContent)
	{
		author = commentAuthor;
		content = commentContent;
	}
	
	public  String getTimeStamp()
	{
		DateFormat df = DateFormat.getDateTimeInstance();
		return df.format(date);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString(){
		String s = "by: " + this.author + "\n\t" + "on " + this.getTimeStamp() + "\n\t" + this.content;
		return s;
	}
	/**
	 * Allow comparisons based on time stamp
	 */
	public int compareTo(Comment c){
		return this.date.compareTo(c.getDate());
	}

	private Date getDate() {
		// TODO Auto-generated method stub
		return (Date)this.date.clone();
	}

}
