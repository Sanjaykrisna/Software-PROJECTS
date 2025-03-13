package temp.choice;

import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerConfiguration;
import io.helidon.webserver.WebServer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.*;
import java.util.Arrays;

public class ShopApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Welcome to Tempy Choice Shop");
		double tax = 0.2;
		double total = 0.0;
		
		Customer c1 = new Customer("Pinky", 6);
	//	c1.setName("");
	//	c1.setSize("");
		
		System.out.println("Minimum Price"+" "+Clothing.MIN_PRICE);
		
		Clothing item1 = new Clothing("Blue Jacket",20.9,"M");
		Clothing item2 = new Clothing("Green Scarf",5,"S");

		Clothing[] items = {item1,item2,new Clothing("Yellow T-shirt",10.5,"L"),new Clothing("Black Pants",12,"M"),new Clothing("Orange Top",10,"S")};
    //This part of code is completely for running the output in the local server using HelidonFeatures
		try {
			ItemList list = new ItemList(items);
			
			Routing routing = Routing.builder()
			.get("/items",list).build();
			
			ServerConfiguration config = ServerConfiguration.builder()
					.bindAddress(InetAddress.getLocalHost())
					.port(8888).build();
			
			WebServer ws = WebServer.create(config, routing);
			
			ws.start();
		}
		catch (UnknownHostException ex) {
			ex.printStackTrace();
		}
		
      //int measurement;

      //c1.setSize(measurement);
		
		c1.addItems(items);
		
		System.out.println("Customer Details are"+" "+c1.getName()+" , "+c1.getSize()+" , "+c1.getTotalClothingCost());
		
		Arrays.sort(c1.getItems());
		
		double average = 0;
		int count = 0;
		
		for(Clothing i:c1.getItems())
		{
			if(i.getSize()==c1.getSize()) {
			System.out.println("Products for checkout : "+i.getDescription());
			count++;
			average = average + i.getPrice();
			}
		}
		
		System.out.println("  ----*----  ");
		
	//Exception Handling : if the above count value does'nt changed from zero, an exception is thrown.
		try {
			average = average/count;
			average = (count==0)?0:average / count;
			System.out.println("Optional Info: "+"\n"+"Average Price:"+average+"\n"+"No.Of.Products:"+count);
		}
		catch(ArithmeticException e)
		{
			System.out.println("Don't divide by 0");
		}
		
	}

}
