import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

public class BlogFileWriter {
	private String filename;
	private Blog blog;
	private FileOutputStream fos = null;
	private ObjectOutputStream out = null;

	public BlogFileWriter(){
		filename = null;
		blog = null;
	}

	public void setFileName(String s){
		filename = s;
	}
	
	public void setBlog(Blog b){
		blog = b;
	}
	
	public void writeBlog(){
		try{
		fos = new FileOutputStream(filename);
		out = new ObjectOutputStream(fos);
		out.writeObject(blog);
		out.close();
		}
		catch(IOException e){
			System.out.println("File not found: ");
			e.printStackTrace();
		}
		
	}

}
