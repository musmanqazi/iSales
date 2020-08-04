/**
 * 
 */
package iSales;

/**
 * @author Mohammad Usman Qazi
 * @version Dec 3, 2019
 */
public class iPhone 
{
	private String id, model, storage, carrier, imei, dateOfPurchase;
	private double purchasePrice, repairPrice, salePrice;
	
	public iPhone()
	{
		purchasePrice = 0;
		repairPrice = 0;
		salePrice = 0;
	}
	
	public String getID()
	{
		return id;
	}
	
	public void generateID()
	{
		id = "" + dateOfPurchase.substring(0, 2) 
				+ imei.substring(11) + dateOfPurchase.substring(2, 4);
	}
	
	public String getModel()
	{
		return model;
	}
	
	public void setModel(String newModel)
	{
		model = newModel;
	}
	
	public String getStorage()
	{
		return storage;
	}
	
	public void setStorage(String newStorage)
	{
		storage = newStorage;
	}
	
	public String getCarrier()
	{
		return carrier;
	}
	
	public void setCarrier(String newCarrier)
	{
		carrier = newCarrier;
	}
	
	public String getIMEI()
	{
		return imei;
	}
	
	public void setIMEI(String newIMEI)
	{
		imei = newIMEI;
	}
	
	public String getDateOfPurchase()
	{
		return dateOfPurchase;
	}
	
	public void setDateOfPurchase(String newDateOfPurchase)
	{
		dateOfPurchase = newDateOfPurchase;
	}
	
	public double getPurchasePrice()
	{
		return purchasePrice;
	}
	
	public void setPurchasePrice(double newPurchasePrice)
	{
		purchasePrice = newPurchasePrice;
	}
	
	public double getRepairPrice()
	{
		return repairPrice;
	}
	
	public void setRepairPrice(double newRepairPrice)
	{
		repairPrice = newRepairPrice;
	}
	
	public double getSalePrice()
	{
		return salePrice;
	}
	
	public void setSalePrice(double newSalePrice)
	{
		salePrice = newSalePrice;
	}
	
	public boolean isSold()
	{
		return (salePrice != 0);
	}
	
	public String getPurchaseInfoAsString()
	{
		return "\n" + dateOfPurchase + " " + model + " " + storage + " " + carrier + " " + imei + " " + purchasePrice;
	}
	
	public String getSaleInfoAsString()
	{
		return repairPrice + " " + salePrice;
	}
}
