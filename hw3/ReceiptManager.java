import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 * Class to construct and manage receipts
 * @author Adam Fowler
 *
 */
public class ReceiptManager {
	private File file;
	private PrintWriter out;
	private Receipt receipt;
	private String filename;
	

	public ReceiptManager(){
		this.filename = "log-" + getDateTime() + ".txt";
		this.receipt = new Receipt();
	}

	public ReceiptManager(String f){
		this.filename = f;
		this.receipt = new Receipt();
	}
	
	 private String getDateTime() {
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        Date date = new Date();
	        return dateFormat.format(date);
	    }


	/**
	 * 
	 * @param m MenuItem to have added to receipt
	 */
	public void addItem(MenuItem m){
		receipt.addItem(m);
	}

	/**
	 * 
	 * @param name Name of item to remove from receipt
	 * @return true if item was in receipt otherwise false
	 */
	public boolean removeItem(String name){
		return receipt.removeItem(name);
	}

	/**
	 * Clear receipt of all items
	 */
	public void clearReceipt(){
		this.receipt.clear();
	}

	/**
	 * 
	 * @return Receipt object we have constructed
	 */
	public Receipt getReceipt(){
		return this.receipt;
	}

	/**
	 * Method to write order to log.txt in current directory
	 * Appends to file if exists, otherwise creates a new file
	 */
	public void placeOrder(){
		this.file = new File(filename);
		try{
			out = new PrintWriter(new FileWriter(file, true));
			out.println(receipt);

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException ie){
			ie.printStackTrace();
		}
		finally{
			out.close();
			receipt.clear();
		}
	}

	/**
	 * 
	 * @return Number of items in receipt
	 */
	public int getReceiptSize(){
		return receipt.getSize();
	}


}
