package temp.choice;

public class Customer {
	private String name;
	private String size;
	
	private Clothing[] items;
	
	public Customer(String name, int measurement)
	{
		this.name = name;
		setSize(measurement);
	}
//  Using the Array to get the Given Clothes for size Matching with the Customer. And Declaring it as "private" info to be hidden for
//	others and provides individuality in selection.
	public void addItems(Clothing[] someItems)
	{
		items=someItems;
	}
	public Clothing[] getItems() {
		return items;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
//  Here receiving the Customer Size in number format and converting into Alphabets to findout the right Dresses
//	which fits based on the standards.
	public void setSize(int measurement)
	{
		switch(measurement) {
		case 1,2,3:
			setSize("S");
		    break;
		case 4,5,6:
			setSize("M");
			break;
		case 7,8,9:
			setSize("L");
			break;
		default:
			setSize("XL");
		}
	}
	
	public double getTotalClothingCost() {
		double total = 0.0;
		double tax = 0.2;
		for(Clothing i: getItems())
		{
			if(getSize()==i.getSize()) //we can also use .equals() method - c1.size.equals(i.size)
				{
				total = total + (i.getPrice()+tax);
	//			System.out.println(i.getDescription()+" "+i.getPrice()+" "+i.getSize());
	  	}
		}
		return total;
	}
	
	
	
}
