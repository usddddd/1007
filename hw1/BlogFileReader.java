import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class BlogFileReader {
	private String filename;
	private Blog blog;
	private FileInputStream fis = null;
	private ObjectInputStream in = null;

	public BlogFileReader(){
		filename = null;
		blog = null;
	}

	public void setFileName(String s){
		filename = s;
	}
	
	public Blog getBlog(){
		return blog;
	}
	
	public void readBlog(){
		try{
		fis = new FileInputStream(filename);
		in = new ObjectInputStream(fis);
		blog = (Blog)in.readObject();
		in.close();
		}
		catch(IOException e){
			System.out.println("File not found: ");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
			e.printStackTrace();
		}
		
	}

}



