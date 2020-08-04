/**
 * 
 */
package iSales;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * @author Mohammad Usman Qazi
 * @version Dec 3, 2019
 */
public class Frame extends JFrame 
{
	private static DecimalFormat df = new DecimalFormat("0.00");
	
	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 500;
	
	private JPanel listPane, actionsPane;
	private static JTextArea idArea, modelArea, storageArea, carrierArea, imeiArea, purchasePriceArea, repairPriceArea, salePriceArea;
	private JButton purchaseButton, saleButton;
	private InfoButton infoButton;
	
	private static iPhone x;
	private static Inventory inventory;
	
	public Frame()
	{
		createComponents();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	public void createComponents()
	{
		listPane = new JPanel();
		actionsPane = new JPanel();
		
		idArea = new JTextArea("ID	");
		modelArea = new JTextArea("MODEL	");
		storageArea = new JTextArea("STORAGE	");
		carrierArea = new JTextArea("CARRIER	");
		imeiArea = new JTextArea("IMEI	");
		purchasePriceArea = new JTextArea("PURCHASE	");
		repairPriceArea = new JTextArea("REPAIRS	");
		salePriceArea = new JTextArea("SALE	");
//		infoButton = new InfoButton("i");
		
		idArea.setEditable(false);
		modelArea.setEditable(false);
		storageArea.setEditable(false);
		carrierArea.setEditable(false);
		imeiArea.setEditable(false);
		purchasePriceArea.setEditable(false);
		repairPriceArea.setEditable(false);
		salePriceArea.setEditable(false);
		
		listPane.add(idArea);
		listPane.add(modelArea);
		listPane.add(storageArea);
		listPane.add(carrierArea);
		listPane.add(imeiArea);
		listPane.add(purchasePriceArea);
		listPane.add(repairPriceArea);
		listPane.add(salePriceArea);
//		listPane.add(infoButton);
		
		getCurrentList();
		
		this.add(listPane);
		
		purchaseButton = new JButton("NEW PURCHASE");
		saleButton = new JButton("NEW SALE");
		
		Dimension buttonSize = new Dimension(375, 125);
		purchaseButton.setPreferredSize(buttonSize);
		saleButton.setPreferredSize(buttonSize);
		
		Cursor clickCursor = new Cursor(Cursor.HAND_CURSOR);
		purchaseButton.setCursor(clickCursor);
		saleButton.setCursor(clickCursor);
		
		purchaseButton.setBackground(Color.white);
		purchaseButton.addActionListener(new NewPurchase());
		
		saleButton.setBackground(Color.white);
		saleButton.addActionListener(new NewSale());
		
		actionsPane.add(purchaseButton);
		actionsPane.add(saleButton);
		
		this.add(actionsPane, BorderLayout.SOUTH);
	}
	
	public static void getCurrentList()
	{
		File input = new File("files/purchases.txt");
		Scanner in = null;
		try {
			in = new Scanner(input);
		} catch (FileNotFoundException e) {
			try {
				input.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		File input1 = null;
		Scanner in1 = null;
		
		inventory = new Inventory();
		
		while(in.hasNextLine())
		{
			x = new iPhone();
			
			x.setDateOfPurchase(in.next());
			x.setModel(in.next());
			x.setStorage(in.next());
			x.setCarrier(in.next());
			x.setIMEI(in.next());
			x.setPurchasePrice(in.nextDouble());
			x.generateID();
			
			input1 = new File("files/sales/" + x.getID() + "/sale.txt");
			try {
				in1 = new Scanner(input1);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			while (in1.hasNextLine())
			{
				x.setRepairPrice(in1.nextDouble());
				x.setSalePrice(in1.nextDouble());
			}
			
			inventory.addToInventory(x);
			
			idArea.setText(idArea.getText() + "\n" + x.getID());
       	 	modelArea.setText(modelArea.getText() + "\n" + x.getModel());
			storageArea.setText(storageArea.getText() + "\n" + x.getStorage());
       	 	carrierArea.setText(carrierArea.getText() + "\n" + x.getCarrier());
       	 	imeiArea.setText(imeiArea.getText() + "\n" + x.getIMEI());
       	 	purchasePriceArea.setText(purchasePriceArea.getText() + "\n" + "$" + df.format(x.getPurchasePrice()));
       	 	repairPriceArea.setText(repairPriceArea.getText() + "\n" + "$" + df.format(x.getRepairPrice()));
       	 	salePriceArea.setText(salePriceArea.getText() + "\n" + "$" + df.format(x.getSalePrice()));
		}
		
		in.close();
//		in1.close();
	}
	
	class NewPurchase implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			inputPurchaseInfo(0, null, null, null, null, null, null);
		}
	}
	
	class NewSale implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			inputSaleInfo(0, null, null, null);
		}
	}
	
	class ViewNotes implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
//			try {
//		    Runtime.getRuntime().exec(new String[] { "C:/windows/notepad.exe", 
//		    		"C:/Users/Usman/Desktop/Development/iSales/files/sales/12132903/notes.txt" });
//			} catch (Exception e1) {
//		   		e1.printStackTrace();
//			}
		}
	}
	
	public static String removeSpaces(String str)
	{
		String newStr = "";
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) != ' ')
			{
				newStr += str.charAt(i);
			}
		}
		return newStr;
	}
	
	public static void inputPurchaseInfo(int a, Object obj, Object obj1, Object obj2, String obj3, String obj4, String obj5)
	{
		String[] modelOptions = {" ", "iPhone 7", "iPhone 7 Plus", "iPhone 8", "iPhone 8 Plus",
				"iPhone X", "iPhone XR", "iPhone XS", "iPhone XS Max", "iPhone 11", "iPhone 11 Pro", "iPhone 11 Pro Max"};
		String[] storageOptions = {" ", "32 GB", "64 GB", "128 GB", "256 GB", "512 GB"};
		String[] carrierOptions = {" ", "Unlocked", "T-Mobile", "Verizon", "Sprint", "AT&T", "Other"};
		JComboBox<String> modelsComboBox = new JComboBox<>(modelOptions);
		JComboBox<String> storageComboBox = new JComboBox<>(storageOptions);
		JComboBox<String> carrierComboBox = new JComboBox<>(carrierOptions);
		JTextField imeiField = new JTextField();
		JTextField dateField = new JTextField();
		JTextField purchasePriceField = new JTextField();
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		if (a == 1)
		{
			modelsComboBox.setSelectedItem(obj);
        	storageComboBox.setSelectedItem(obj1);
        	carrierComboBox.setSelectedItem(obj2);
        	imeiField.setText(obj3);
        	dateField.setText(obj4);
        	purchasePriceField.setText(obj5);
		}
        panel.add(new JLabel("Select Model: "));
        panel.add(modelsComboBox);
        panel.add(new JLabel("Select Storage: "));
        panel.add(storageComboBox);
        panel.add(new JLabel("Select Carrier: "));
        panel.add(carrierComboBox);
        panel.add(new JLabel("Enter IMEI (15 Digits): "));
        panel.add(imeiField);
        panel.add(new JLabel("Enter Date Purchased (MMDDYYYY): "));
        panel.add(dateField);
        panel.add(new JLabel("Enter Purchase Price: "));
        panel.add(purchasePriceField);
        
        
        int result = JOptionPane.showConfirmDialog(null, panel, "iPhone Information",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) 
        {
        	if (modelsComboBox.getSelectedItem() == " " || storageComboBox.getSelectedItem() == " " || carrierComboBox.getSelectedItem() == " " || 
        			imeiField.getText().isEmpty() || dateField.getText().isEmpty() || purchasePriceField.getText().isEmpty())
    		{
        		int res1 = JOptionPane.showConfirmDialog(null, "Error: One or more fields were empty", "Error", JOptionPane.OK_CANCEL_OPTION);
        		if (res1 == JOptionPane.OK_OPTION)
        		{
        			inputPurchaseInfo(1, modelsComboBox.getSelectedItem(), storageComboBox.getSelectedItem(), carrierComboBox.getSelectedItem(),
                			imeiField.getText(), dateField.getText(), purchasePriceField.getText());
        		}
    		}
        	else if (imeiField.getText().length() != 15)
        	{
        		int res1 = JOptionPane.showConfirmDialog(null, "Error: IMEI number must be 15 digits", "Error", JOptionPane.OK_CANCEL_OPTION);
        		if (res1 == JOptionPane.OK_OPTION)
        		{
        			inputPurchaseInfo(1, modelsComboBox.getSelectedItem(), storageComboBox.getSelectedItem(), carrierComboBox.getSelectedItem(),
                			imeiField.getText(), dateField.getText(), purchasePriceField.getText());
        		}
        	}
        	else if (dateField.getText().length() != 8)
        	{
        		int res1 = JOptionPane.showConfirmDialog(null, "Error: Date must be entered as MMDDYYYY", "Error", JOptionPane.OK_CANCEL_OPTION);
        		if (res1 == JOptionPane.OK_OPTION)
        		{
        			inputPurchaseInfo(1, modelsComboBox.getSelectedItem(), storageComboBox.getSelectedItem(), carrierComboBox.getSelectedItem(),
                			imeiField.getText(), dateField.getText(), purchasePriceField.getText());
        		}
        	}
        	else
        	{
        		x = new iPhone();
            	x.setModel(removeSpaces((String)modelsComboBox.getSelectedItem()));
            	x.setStorage(removeSpaces((String)storageComboBox.getSelectedItem()));
            	x.setCarrier(removeSpaces((String)carrierComboBox.getSelectedItem()));
            	x.setIMEI(imeiField.getText());
            	x.setDateOfPurchase(dateField.getText());
            	x.setPurchasePrice(Double.valueOf(purchasePriceField.getText()));
            	x.generateID();
            	
            	FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter("files/purchases.txt", true);
				} catch (IOException e) {
					e.printStackTrace();
				}
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(x.getPurchaseInfoAsString());
                printWriter.close();
                
            	inventory.addToInventory(x);
            	
            	File folder = new File("files/sales/" + x.getID());
            	File folder1 = new File("files/sales/" + x.getID() + "/pics");
            	folder.mkdir();
            	folder1.mkdir();
            	
            	File file = new File("files/sales/" + x.getID() + "/sale.txt");
            	File file1 = new File("files/sales/" + x.getID() + "/notes.txt");
            	try {
					file.createNewFile();
					file1.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
            	
            	idArea.setText("ID	");
        		modelArea.setText("MODEL	");
        		storageArea.setText("STORAGE	");
        		carrierArea.setText("CARRIER	");
        		imeiArea.setText("IMEI	");
        		purchasePriceArea.setText("PURCHASE	");
        		repairPriceArea.setText("REPAIRS	");
        		salePriceArea.setText("SALE	");
            	
            	getCurrentList();
        	}
        } 
        else 
        {
        	if (modelsComboBox.getSelectedItem() != " " || storageComboBox.getSelectedItem() != " " || carrierComboBox.getSelectedItem() != " " || 
        			!imeiField.getText().isEmpty() || !dateField.getText().isEmpty() || !purchasePriceField.getText().isEmpty())
        	{
        		int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.NO_OPTION)
                {
                	inputPurchaseInfo(1, modelsComboBox.getSelectedItem(), storageComboBox.getSelectedItem(), carrierComboBox.getSelectedItem(),
                			imeiField.getText(), dateField.getText(), purchasePriceField.getText());
                }
        	}
        }
	}

	public static void inputSaleInfo(int a, Object obj, String obj1, String obj2)
	{
		String[] iPhoneOptions = new String[inventory.getInventoryCount() + 1];
		JTextField salePriceField = new JTextField();
		JTextField repairPriceField = new JTextField();
		iPhoneOptions[0] = " ";
		int i = 1;
		for (int j = 0; j < inventory.getNumOfItems(); j++)
		{
			if (!inventory.at(j).isSold())
			{
				iPhoneOptions[i] = inventory.at(j).getID() + " - " + inventory.at(j).getModel();
				i++;
			}
		}
		JComboBox<String> idComboBox = new JComboBox<>(iPhoneOptions);
		
		JPanel panel = new JPanel(new GridLayout(0, 1));
		if (a == 1)
		{
			idComboBox.setSelectedItem(obj);
        	repairPriceField.setText(obj1);
        	salePriceField.setText(obj2);
		}
		
		panel.add(new JLabel("Select iPhone: "));
        panel.add(idComboBox);
        panel.add(new JLabel("Enter Repairs/Accessories Price: "));
        panel.add(repairPriceField);
        panel.add(new JLabel("Enter Sale Price: "));
        panel.add(salePriceField);
        
        int result = JOptionPane.showConfirmDialog(null, panel, "iPhone Information",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION)
        {
        	if (idComboBox.getSelectedItem() == " " || idComboBox.getSelectedItem() == null ||  repairPriceField.getText().isEmpty() || salePriceField.getText().isEmpty())
        	{
        		int res1 = JOptionPane.showConfirmDialog(null, "Error: One or more fields were empty", "Error", JOptionPane.OK_CANCEL_OPTION);
        		if (res1 == JOptionPane.OK_OPTION)
        		{
        			inputSaleInfo(1, idComboBox.getSelectedItem(), repairPriceField.getText(), salePriceField.getText());
        		}
        	}
        	else
        	{
        		String id = (String)idComboBox.getSelectedItem();
        		id = id.substring(0, 8);
        		x = inventory.search(id);
        		x.setRepairPrice(Double.valueOf(repairPriceField.getText()));
        		x.setSalePrice(Double.valueOf(salePriceField.getText()));
        		
        		FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter("files/sales/" + id + "/sale.txt", true);
				} catch (IOException e) {
					e.printStackTrace();
				}
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.print(x.getSaleInfoAsString());
                printWriter.close();
                
                idArea.setText("ID	");
        		modelArea.setText("MODEL	");
        		storageArea.setText("STORAGE	");
        		carrierArea.setText("CARRIER	");
        		imeiArea.setText("IMEI	");
        		purchasePriceArea.setText("PURCHASE	");
        		repairPriceArea.setText("REPAIRS	");
        		salePriceArea.setText("SALE	");
            	
            	getCurrentList();
        	}
        }
        else
        {
        	if (idComboBox.getSelectedItem() != " " || !repairPriceField.getText().isEmpty() || !salePriceField.getText().isEmpty())
        	{
        		int res = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel?", "Cancel", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.NO_OPTION)
                {
                	inputSaleInfo(1, idComboBox.getSelectedItem(), repairPriceField.getText(), salePriceField.getText());
                }
        	}
        }
	}
}
