/**
 * 
 */
package iSales;

import javax.swing.JButton;

/**
 * @author Mohammad Usman Qazi
 * @version Feb 4, 2020
 */
public class InfoButton extends JButton
{
	private JButton button;
	int buttonID;
	String iPhoneID;
	
	public InfoButton()
	{
		buttonID = 0;
		button = new JButton();
	}
	
	public InfoButton(String label)
	{
		button = new JButton(label);
	}
}
