/**
 * 
 */
package iSales;


/**
 * @author Mohammad Usman Qazi
 * @version Jan 20, 2020
 */
public class Inventory 
{
	private iPhone[] inventory;
	private int numOfItems;
	
	public Inventory()
	{
		inventory = new iPhone[9999];
		numOfItems = 0;
	}
	
	public int getNumOfItems()
	{
		return numOfItems;
	}
	
	public void addToInventory(iPhone x)
	{
		inventory[numOfItems] = x;
		numOfItems++;
	}
	
	public iPhone at(int i)
	{
		return inventory[i];
	}
	
	public iPhone search(String id)
	{
		int i = 0;
		iPhone x = new iPhone();
		while (i < numOfItems)
		{
			if (inventory[i].getID() == id)
			{
				x = inventory[i];
			}
			else
			{
				i++;
			}
		}
		return x;
	}
	
	public int getInventoryCount()
	{
		int count = 0;
		for (int i = 0; i < numOfItems; i++)
		{
			if (!inventory[i].isSold())
			{
				count++;
			}
		}
		return count;
	}
}
