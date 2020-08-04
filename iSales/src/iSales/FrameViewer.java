/**
 * 
 */
package iSales;

import javax.swing.JFrame;

/**
 * @author Mohammad Usman Qazi
 * @version Jan 10, 2020
 */
public class FrameViewer {

	public static void main(String[] args) 
	{
		Frame frame = new Frame();
		frame.setTitle("iPhone Sales");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
