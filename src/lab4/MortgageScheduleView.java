package lab4;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MortgageScheduleView extends JFrame {
	
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 300;
	private final int LOCATIONX = 450;
	private final int LOCATIONY = 150;
	
	private JPanel myPanel;
	private JScrollPane scrollpane;
	private JPanel tablePanel;
	
	private JLabel title;
	private JTable table;
	private JButton closeButton;
	
	private String[] columnNames = {"Blended Payment Amount",
            "Interest Component",
            "Principal Component",
            "New Balance Owing"};
	
	public MortgageScheduleView()
	{	
		super("Mortgage Schedule");
		myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(LOCATIONX,LOCATIONY);

		myPanel.setLayout(new GridBagLayout());
		
		setUpPanel();
		setUpTable();
	}
	
	public void setUpPanel()
	{
		title = new JLabel("Payment Schedule");
		title.setFont(new Font(Font.SANS_SERIF, 0, 16));
		addItem(myPanel, title, 2, 0, 1, 1, GridBagConstraints.NORTH);

		closeButton = new JButton("Close");
		addItem(myPanel, closeButton, 2, 10, 1, 1, GridBagConstraints.NORTH);
		
		scrollpane = new JScrollPane(myPanel);
		scrollpane.setBorder(null);
	    getContentPane().add(scrollpane);
	}
	
	public void setUpTable()
	{	
		Object[][] data = {{"",""}};
		
		table = new JTable(data, columnNames);
		
		table.getTableHeader().setFont(new Font(Font.SANS_SERIF, 0, 13));
		table.setFont(new Font(Font.SANS_SERIF, 0, 13));
		table.setRowHeight(20);
		table.getColumnModel().setColumnMargin(10);
		
		tablePanel = new JPanel();
		tablePanel.setLayout(new GridBagLayout());

	}
	
	public void updateTable(Object[][] data)
	{
			TableModel model = new DefaultTableModel(data, columnNames);
			myPanel.remove(table);
			table.setModel(model);
			for (int i=0; i<4; i++) {
				table.getColumnModel().getColumn(i).setPreferredWidth(175);
			}
			
			addItem(tablePanel, table.getTableHeader(), 2, 0, 1, 1, GridBagConstraints.NORTH);
			addItem(tablePanel, table, 2, 1, 1, 1, GridBagConstraints.NORTH);
			
			addItem(myPanel, tablePanel, 2, 2, 1, 1, GridBagConstraints.NORTH);
			
			refreshFrame();
	}
	
	public void addScheduleListener(ActionListener scheduleListener)
	{
		closeButton.addActionListener(scheduleListener);
	}
	
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
	
	public void refreshFrame()
	{
		getContentPane().add(scrollpane);
		// refresh JFrame
		revalidate();
		repaint();
	}

}
