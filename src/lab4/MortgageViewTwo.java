package lab4;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MortgageViewTwo extends JFrame {
	
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 300;
	private final int LOCATIONX = 450;
	private final int LOCATIONY = 150;
	
	private JPanel myPanel;
	private JPanel closePanel;
	private JScrollPane scrollpane;
	
	private JLabel title;
	private JTable table;
	private JButton closeButton;
	
	public MortgageViewTwo()
	{	
		super("Mortgage Schedule");
		myPanel = new JPanel();
		myPanel.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocation(LOCATIONX,LOCATIONY);

		myPanel.setLayout(new GridBagLayout());
		
		setUpPanel();

		
	}
	
	public void setUpPanel()
	{
		title = new JLabel("Payment Schedule");
		title.setFont(new Font(Font.SANS_SERIF, 0, 16));
		addItem(myPanel, title, 2, 0, 1, 1, GridBagConstraints.NORTH);

		closeButton = new JButton("Close");
		addItem(myPanel, closeButton, 2, 10, 1, 1, GridBagConstraints.NORTH);
		
		scrollpane = new JScrollPane(myPanel);
	    getContentPane().add(scrollpane);
	}
	
	public void setUpTable(Object[][] data)
	{	
		String[] columnNames = {"Blended Payment Amount",
                "Interest Component",
                "Principal Component",
                "New Balance Owing"};
		
		table = new JTable(data, columnNames);
		
		table.getTableHeader().setFont(new Font(Font.SANS_SERIF, 0, 13));
		table.setRowMargin(10);
		table.setRowHeight(40);
		
		for (int i=0; i<4; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(175);
		}
		
		addItem(myPanel, table.getTableHeader(), 2, 2, 1, 1, GridBagConstraints.NORTH);
		addItem(myPanel, table, 2, 3, 1, 1, GridBagConstraints.NORTH);

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
