package temp.choice;

public class Clothing implements Comparable<Clothing>{
	private String description;
	private double price;
	private String size;
	
	public static final double MIN_PRICE = 10.0;
	public static final double MIN_TAX = 0.2;
	
	public Clothing(String description, double price, String aSize) {
		this.description = description;
		this.price = price;
		size=aSize;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price + MIN_TAX;
	}
	public void setPrice(double price) {
		this.price = (price>MIN_PRICE)?price:MIN_PRICE;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
//  Additional Methods to be carried for different purposes.
	public String toString()
	{
		return getDescription()+","+getPrice()+","+getSize();
	}
	@Override
	public int compareTo(Clothing c)
	{
		return this.description.compareTo(c.description);
	}
	
	
}
