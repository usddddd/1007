import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Menu Display class
 * @author Adam Fowler
 *
 */
public class Display{

	private Menu menu;
	private JFrame frame;
	private JPanel menuPanel;
	private JPanel receiptPanel;
	private JPanel receiptSubpanel;

	private JTextArea textarea;
	private ReceiptManager receiptManager;

	private JScrollPane receiptScrollPane;
	private JScrollPane menuScrollPane;
	public Display(Menu m){
		this.menu = m;
		receiptManager = new ReceiptManager();

		frame = new JFrame();
		frame.setLayout(new BorderLayout());


		menuPanel = new JPanel();

		menuPanel.setLayout(getLayout(menu.getSize()));

		receiptPanel = new JPanel();
		receiptPanel.setLayout(new BorderLayout());
		receiptSubpanel = new JPanel();
		receiptSubpanel.setLayout(new FlowLayout());
		textarea = new JTextArea(20, 30);
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		createDisplay();


	}

	/**
	 * 
	 * @param numItems Number of items on menu
	 * @return GridLayout for number of buttons
	 */
	public GridLayout getLayout(int numItems){
		if(numItems % 2 == 0 && numItems > 2){
			return new GridLayout(0, 4);
		}
		else if(numItems % 3 == 0 ){
			return new GridLayout(0, 3);
		}
		else if(numItems % 5 != 0){
			return new GridLayout(0, 5);
		}
		else if(numItems % 7 != 0){
			return new GridLayout(0, 7);
		}
		else
			return new GridLayout(0, 1);
	}

	/**
	 * Build Menu Display
	 */
	public void createDisplay(){
		for(int i=0; i < menu.getSize(); i++){
			MenuItem menuItem = menu.getItem(i);


			menuPanel.add(getMenuButton(menuItem.getName()));
		}
		receiptPanel.add(textarea, BorderLayout.NORTH);// replaced textarea with scrollpane
		JButton clearButton = new JButton("Clear Receipt");
		clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				receiptManager.clearReceipt();
				textarea.setText("");
			}
		});
		receiptSubpanel.add(clearButton);
		JButton payButton = new JButton("Place order");
		payButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				receiptManager.placeOrder();
				textarea.setText("");
			}
		});
		receiptSubpanel.add(payButton);

		receiptPanel.add(receiptSubpanel, BorderLayout.SOUTH);
		
		receiptScrollPane = new JScrollPane(receiptPanel);
		menuScrollPane = new JScrollPane(menuPanel);
		frame.add(receiptScrollPane, BorderLayout.WEST);
		frame.add(menuScrollPane,BorderLayout.EAST);

	}

	/**
	 * 
	 * @param name String name of button
	 * @return Button object
	 */
	public JButton getMenuButton(String name){
		JButton button = new JButton(name);
		button.setPreferredSize(new Dimension(200, 50));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JButton b = (JButton)arg0.getSource();
				String name = b.getText();
				MenuItem m = menu.getItem(name);


				receiptManager.addItem(m);
				Receipt r = receiptManager.getReceipt();
				String s = "";
				for(int i = 0; i < r.getSize(); i++){
					MenuItem item = r.getItem(i);
					String itemName = item.getName();
					double itemCost = item.getCost();
					
					String cost = String.format("$%.2f", itemCost);

					s += "Item: " + itemName + " Cost:" + cost + "\n"; 
				}
				
				String totalCost = String.format("$%.2f", r.getTotalCost());


				textarea.setText("");
				textarea.setText(s + "\n" + "\t Total: " + totalCost );

			}
		});

		return button;
	}

	public void displayBox(){


		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.pack();
		frame.setVisible(true);
	}


}
