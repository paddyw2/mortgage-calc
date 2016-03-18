package lab4;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class TextBoxListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
			JTextField source = (JTextField) e.getSource();
			try {
				Double.parseDouble(source.getText());
			} catch(NumberFormatException ex) {
				source.setText("");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}