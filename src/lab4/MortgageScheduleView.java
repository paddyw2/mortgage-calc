package lab4;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
* <h1>Mortgage Payment Schedule View</h1>
* This class creates the view for the separate mortgage schedule window
* that displays the users payment schedule by month or by year
*
* @author  Patrick Withams
* @version 1.0
* @since   2016-03-26
*/

public class MortgageScheduleView extends JFrame {
	
	// frame constants
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 300;
	private final int LOCATIONX = 450;
	private final int LOCATIONY = 150;
	
	// JPanels and JScrollPane
	private JPanel myPanel;
	private JPanel tablePanel;
	private JScrollPane scrollpane;
	
	// components
	private JLabel title;
	private JTable table;
	private JButton closeButton;
	private JButton toggleView;
	
	// duration String, set to default "Year"
	private String duration = "Year";
	
	/**
	 * <h1>Constructor</h1>
	 * 
	 * sets up the payment schedule window with the default
	 * yearly view
	 * 
	 */
	public MortgageScheduleView()
	{	
		super("Mortgage Schedule");
		myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(LOCATIONX,LOCATIONY);
		myPanel.setLayout(new GridBagLayout());
		
		// set up panel and table
		setUpPanel();
		setUpTable();
	}

	/**
	 * <h1>Update Duration in Table Header</h1>
	 * 
	 * toggle the duration value in the table header to
	 * month or year
	 * 
	 */
	public void updateDuration()
	{
		if (duration.equals("Year"))
			duration = "Month";
		else
			duration = "Year";
	}

	/**
	 * <h1>Return Current Table Header</h1>
	 * 
	 * return a String array with the current duration variable
	 * used when updating the table
	 * 
	 * @return columnNames
	 */
	public String[] getHeader()
	{
		String[] columnNames = {duration, "Blended Payment Amount",
            "Interest Component",
            "Principal Component",
            "New Balance Owing"};
		
		return columnNames;
	}
	
	/**
	 * <h1>Update Change View Button</h1>
	 * 
	 * toggle the change view button to reflect the
	 * current schedule state after action
	 * 
	 */
	public void updateButton()
	{
		if(toggleView.getText().equals("View by Month"))
			toggleView.setText("View by Year");
		else
			toggleView.setText("View by Month");
	}
	
	// Constructor Helper Methods
	/**
	 * <h1>Set Up Panel With Components</h1>
	 * 
	 * set up main panel and add components
	 * 
	 */
	public void setUpPanel()
	{
		title = new JLabel("Mortgage Payment Schedule");
		title.setFont(new Font(Font.SANS_SERIF, 0, 16));
		addItem(myPanel, title, 2, 0, 1, 1, GridBagConstraints.NORTH);

		closeButton = new JButton("Close");
		addItem(myPanel, closeButton, 2, 10, 1, 1, GridBagConstraints.NORTH);
		
		toggleView = new JButton("View by Month");
		addItem(myPanel, toggleView, 2, 3, 1, 1, GridBagConstraints.NORTH);
		
		scrollpane = new JScrollPane(myPanel);
		scrollpane.setBorder(null);
	    getContentPane().add(scrollpane);
	}

	/**
	 * <h1>Set Up Table With Components</h1>
	 * 
	 * set up table and add components
	 * 
	 */
	public void setUpTable()
	{	
		Object[][] data = {{"",""}};
		
		table = new JTable(data, getHeader());
		
		table.getTableHeader().setFont(new Font(Font.SANS_SERIF, 0, 13));
		table.setFont(new Font(Font.SANS_SERIF, 0, 13));
		table.setRowHeight(20);
		table.getColumnModel().setColumnMargin(10);
		
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridBagLayout());

	}
	
	/**
	 * <h1>Update Table View With Latest Schedule Data</h1>
	 * 
	 * update table when either the view by month/year button is clicked, or
	 * when the calculator view schedule button is clicked
	 * new data is collected from the model, along with the current header
	 * values and these are updated in the view
	 * 
	 */
	public void updateTable(Object[][] data)
	{
			TableModel model = new DefaultTableModel(data, getHeader());
			myPanel.remove(table);
			table.setModel(model);
			for (int i=0; i<5; i++) {
				if (i==0)
					table.getColumnModel().getColumn(i).setPreferredWidth(50);
				else
					table.getColumnModel().getColumn(i).setPreferredWidth(175);
			}
			
			addItem(tablePanel, table.getTableHeader(), 2, 4, 1, 1, GridBagConstraints.NORTH);
			addItem(tablePanel, table, 2, 5, 1, 1, GridBagConstraints.NORTH);
			
			addItem(myPanel, tablePanel, 2, 5, 1, 1, GridBagConstraints.NORTH);
			
			refreshFrame();
	}
	
	// Listener Add Methods
	/**
	 * <h1>Add Schedule Listener</h1>
	 * 
	 * adds listener to close button
	 * 
	 * @param scheduleListener
	 */
	public void addScheduleListener(ActionListener scheduleListener)
	{
		closeButton.addActionListener(scheduleListener);
	}
	
	/**
	 * <h1>Add Schedule View Listener</h1>
	 * 
	 * adds listener to toggleView button
	 * 
	 * @param scheduleListener
	 */
	public void addScheduleViewListener(ActionListener scheduleListener)
	{
		toggleView.addActionListener(scheduleListener);
	}

	/**
	 * 
	 * <h1>GridBagLayout Add Method</h1>
	 * 
	 * GridBagLayout add helper method to ensure correct placement of new components
	 * onto the panel
	 * 
	 * @param p
	 * @param c
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param align
	 */
	public void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align) {
	    GridBagConstraints gc = new GridBagConstraints();
	    gc.gridx = x;
	    gc.gridy = y;
	    gc.gridwidth = width;
	    gc.gridheight = height;
	    gc.weightx = 100.0;
	    gc.weighty = 100.0;
	    gc.insets = new Insets(5, 5, 5, 5);
	    gc.anchor = align;
	    gc.fill = GridBagConstraints.NONE;
	    p.add(c, gc);
	}
	
	/**
	 * <h1>Refresh and Update JFrame</h1>
	 * 
	 * method to update JFrame with any
	 * panel adds or removes that may have
	 * taken place
	 * 
	 */
	public void refreshFrame()
	{
		getContentPane().add(scrollpane);
		// refresh JFrame
		revalidate();
		repaint();
	}
	
	/**
	 * <h1>Display Error Message</h1>
	 * 
	 * display a JOptionPane error message
	 * 
	 * @param errorMessage
	 */
	public void displayErrorMessage(String errorMessage)
	{
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}