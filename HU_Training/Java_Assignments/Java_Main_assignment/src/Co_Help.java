
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

class Range_validation extends Exception
{
	Range_validation(String s)
	{
		super(s);
	}
}

class Uppercase_validation extends Exception
{
	Uppercase_validation(String s)
	{
		super(s);
	}
}
class Lowercase_validation extends Exception
{
	 Lowercase_validation(String s)
	{
		super(s);
	}
}
class Special_char_validation extends Exception
{
	Special_char_validation(String s)
	{
		super(s);
	}
}
class Digit_validation extends Exception
{
	Digit_validation(String s)
	{
		super(s);
	}
}

class Executive
{
	public static int delivery_person=5;
}

class User  {
	String user_name,password;
	
	public void input()
	{
		System.out.println("Enter User name and password");
		Scanner sc=new Scanner(System.in);
		System.out.print("User Name : ");
		user_name=sc.next();
		System.out.print("Password : ");
		password=sc.next();
		
	}
	public int authenticator()	// checks for valid user name and password
	{
		String line = "";  
		 
		try   
		{  BufferedReader br = new BufferedReader(new FileReader("src\\UserData.csv"));
			int c=0;
			while ((line = br.readLine()) != null)   
			{  
				c++;
				String[] user = line.split(",");    // use comma as separator  
				if(c!=1 && removeQuotes(user[1]).equals(user_name) && removeQuotes(user[2]).equals(password) )
				{ System.out.println("***	Welcome "+user_name+"		***");
				  br.close();
				  return 1;
				}
			}
				
			 		br.close();
					System.out.println("***		Wrong Username or Password		***");
					return 0;
				
			
		  
		}
		catch (IOException e)   
		{  
		  System.out.println(e.getMessage());
		} 
		return 0;
	}
	public String removeQuotes(String str)
	{ String resultStr="";  
	 
		for (int i=0;i<str.length();i++)  
		{  
			if (str.charAt(i)!='"' ) 
				resultStr=resultStr+str.charAt(i);   
		}  
	 return resultStr;	
	}
	public void register()  // Registers New User
	{
		int f=1;
			this.input();    // take input
			
			// password validation start
			try {
				if(password.length()<5 || password.length()>10)
				{
					throw new Range_validation("	Length not between 5 & 10 ");
				}
			}
			catch(Range_validation rv)
			{ f=0;
				System.out.println(rv.getMessage());
				
			}
			try {
				char[] ch=password.toCharArray();
				int t=0;
				for(int i=0;i<password.length();i++)
				{if(ch[i]>='A' && ch[i]<='Z')
				  t++; 
				}
				if(t==0)
					throw new Uppercase_validation("	No Uppercase letter Found ");
			}
			catch(Uppercase_validation rv)
			{f=0;
			System.out.println(rv.getMessage());
			}
			try {
				char[] ch=password.toCharArray();
				int t=0;
				for(int i=0;i<password.length();i++)
				{if(ch[i]>='a' && ch[i]<='z')
				  t++;	
				}
				if(t==0)
					throw new Lowercase_validation("	No Lowercase letter Found ");
			}
			catch(Lowercase_validation rv)
			{f=0;
			System.out.println(rv.getMessage());
			}
			try {
				char[] ch=password.toCharArray();
				int t=0;
				for(int i=0;i<password.length();i++)
				{if((ch[i]>=' ' && ch[i]<='/')||(ch[i]>=':' && ch[i]<='@')||(ch[i]>='[' && ch[i]<='`')||(ch[i]>='{' && ch[i]<='~') )
				  t++;	
				}
				if(t==0)
					throw new Special_char_validation("		No Special Character Found ");
			}
			catch(Special_char_validation rv)
			{f=0;
			System.out.println(rv.getMessage());
			}
			try {
			char[] ch=password.toCharArray();
			int t=0;
			for(int i=0;i<password.length();i++)
			{if(ch[i]>='0' && ch[i]<='9')
			  t++;	
			}
			if(t==0)
				throw new Digit_validation("	No Number Found ");
			
			}
			catch(Digit_validation rv)
			{f=0;
			System.out.println(rv.getMessage());
			}
			
			// password validation end 
			
			//  add new user
			if(f==1) 
			{ String line="";            // to find user id in last row
			  try { 
				BufferedReader br = new BufferedReader(new FileReader("src\\UserData.csv"));
				int id = 0,c=0;
				while ((line = br.readLine()) != null)   
				{  
					
					String[] user = line.split(",");    // use comma as separator 
					c++;
					if(c!=1)		// to get user id of last row	  
						id=Integer.parseInt(removeQuotes(user[0]));
					
					if(removeQuotes(user[1]).compareToIgnoreCase(user_name)==0)
					{  System.out.println(user_name+" User name already exists use different user name");
						br.close();
						return;
					}
							
				}
				
				id++;
				br.close();
			
			// write new user details in UserData.csv  file
			File file = new File("src\\UserData.csv");
			FileWriter outputfile = new FileWriter(file,true); 
			CSVWriter writer = new CSVWriter(outputfile);
			
			String[] newUser = { String.valueOf(id), user_name, password };
			writer.writeNext(newUser);
			writer.close();
			
			System.out.println("***		"+user_name+" Succesfully  Registered	***");
			
			// print all User data
//			FileReader fr=null;
//			fr=new FileReader("src\\UserData.csv");
//
//			int ch;
//			while((ch=fr.read())!=-1)
//				System.out.print((char)ch);
//
//			fr.close();	
			}
			catch (IOException e)   
			{  
				System.out.println(e.getMessage());
			} 
			}
			else
				System.out.println("***		ReEnter Password with given criteria !!		***");
			
			
	}
}

class Product{
	String product_name;
	int quantity;
	int price;

	public String removeQuotes(String str)		// removes quotes from data retrived from .csv file
	{String resultStr="";  
	 
		for (int i=0;i<str.length();i++)  
		{  
			if (str.charAt(i)!='"' ) 
				resultStr=resultStr+str.charAt(i);  
		}  
	  return resultStr;	
	}
	
public int searchProduct()    // search product from Productdata.csv file
{
	System.out.println("Enter Product to be searched ");
	System.out.print("Product : ");
	Scanner sc=new Scanner(System.in);
	
	product_name=sc.nextLine();
	
	String line = "";  
	 
	try   
	{  BufferedReader br = new BufferedReader(new FileReader("src\\ProductData.csv"));
		int c=0;
		while ((line = br.readLine()) != null)   
		{  
			c++;
			String[] product = line.split(",");    // use comma as separator 
			//System.out.println(product[1]+" "+product[1].getClass().getSimpleName());
			
			// store found product details in class variables
			if(c!=1 && removeQuotes(product[1]).equalsIgnoreCase(product_name) )   
			{ 
			  System.out.println(product[1]+" Found !!");
			  System.out.println("Quantity "+product[3]);
			  quantity=Integer.parseInt(removeQuotes(product[3]));
			  price=Integer.parseInt(removeQuotes(product[2]));
			  br.close();
			  return 1;
			}
			
		}
		   // if product not found
			System.out.println(product_name+"***	 Not Found !!	***");
			br.close();
			return 0;
		
		
	}
	catch (IOException e)   
	{  
	e.printStackTrace();  
	} 
	return 0;
 }
public void make_order()   // Purchase a product
{	
	if(this.searchProduct()==1)  	 // search product 
	{ System.out.print("Enter Product Quantity ");
	  System.out.print("Quantity : ");
	  Scanner sc=new Scanner(System.in);
	  
	  int need=sc.nextInt();
	  
	  if(quantity == 0)
		  System.out.println("***	Product not Available :(	***");
	  else if(quantity >= need && quantity != 0)
	  { try{    
		  	// to get date from system
		  	Date d = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String date= formatter.format(d);  
	      
		  // write order details in invoice.txt   file
          FileWriter fw=new FileWriter("src\\Invoice.txt");
          fw.write(String.format("%20s %20s %20s %20s \r\n", "Product_name", "Quantity","Price","Date of Purchase"));
          fw.write(String.format("%20s %20s %20s %20s \r\n", product_name , need,need+"*"+price+" = "+need*price+" only",date));
          
          fw.close(); 
          
          // read from invoice.txt
          FileReader fr=null;
          
          fr=new FileReader("src\\Invoice.txt");
          
          int ch;
          while((ch=fr.read())!=-1)   	 // print invoice details
        	  System.out.print((char)ch);
          
          fr.close();	
          		
         }
	  	catch(Exception e){System.out.println(e.getMessage());}   
	  
         System.out.println("	Order Successfull....");    
        
         
         // decrease quantity in ProductData.csv  file
         try {
        	 String remain_quantity=String.valueOf(quantity-need); 
        	 this.updateCSV(remain_quantity);
        	 
        	 
         }
         catch(Exception e){System.out.println(e.getMessage());}   
     		
         if(Executive.delivery_person>0)
         { Executive.delivery_person--;
        	 System.out.println("****   Delivery Executive Available we will deliver your order within 2 Hours :)   ***");
         }
         else
        	 System.out.println("***  No Delivery Executive present at this moment :( ***\n***  We will deliver your order within 24 Hours ***\n"
        	 		+ "**  Incase of Emergency Call : 9123456789  ***");
         
	  }
	  else
		  System.out.println("***	Cannot Order More than Available Quantity 	***\n");
    }
	 
		 
	  // to store  all orders data
//	  try {
//			File file = new File("src\\OrderData.csv");
//			FileWriter outputfile = new FileWriter(file); 
//			CSVWriter writer = new CSVWriter(outputfile);
//			
//			String[] header = { "Product_name", "Quantity", "Price","Date of Purchase"};
//			writer.writeNext(header);
//			
//			String[] newOrder = {  product_name, String.valueOf(quantity), String.valueOf(price),"not implemented" };
//			writer.writeNext(newOrder);
//			writer.close();
//			
//			System.out.println("Product Name : "+product_name+"\nQuantity : "+need+"\nSuccesfully  Ordered");
//			
//			FileReader fr=null;
//
//			fr=new FileReader("src\\OrderData.csv");
//
//			int ch;
//			while((ch=fr.read())!=-1)
//				System.out.print((char)ch);
//
//			fr.close();	
//			}
//			catch (IOException e)   
//			{  
//				System.out.println(e.getMessage());
//			} 
//			}
//		  
//	  }
		
	}
public void updateCSV(String remain_quantity) throws CsvException    // decrease Quantity of purchased product
{	
	
	String line = "";  
	 
	try   
	{  BufferedReader br = new BufferedReader(new FileReader("src\\ProductData.csv"));
		
			
			//System.out.println(" got row ");
			File file = new File("src\\temp.csv");
			FileWriter outputfile = new FileWriter(file,true);  // for append mode use  FileWriter outputfile = new FileWriter(file,true);
			CSVWriter writer = new CSVWriter(outputfile);

			int row=0;
			while ((line = br.readLine()) != null)   
			{  
				row++;
				String[] product = line.split(",");    // use comma as separator 
				
				for(int i=0;i<product.length;i++)
					product[i]=removeQuotes(product[i]);
				
				if(row!=1 && product[1].equalsIgnoreCase(product_name) )
				{  String[] change=product;
				   change[3]=remain_quantity;

				   writer.writeNext(change);  
				}
				else
				{ 
					 writer.writeNext(product);
				}
			}
			br.close();
			writer.close();
			
			
			File f2 = new File("src\\ProductData.csv");    // delete outdated  Productdata.csv file 
			f2.delete();
			
			
        	 file.renameTo(new File("src\\ProductData.csv"));  // rename temp.csv to Productdata.csv
		
	}
	catch (IOException e)   
	{  
		System.out.println(e.getMessage());
	} 
}
}

public class Co_Help {
	
	public static void main(String[] args) {
	System.out.println("	****************	 	Welcome to Co-Help		 ****************		\n");

	int q=1;
	while(q==1)
	{
	 User u1=new User();
	 Product p1=new Product();
	 
	 System.out.println(" 1. Login \n 2. Register New Account \n 3. Search Product \n 4. Order Product \n 5. Exit");
	 System.out.print("Select an operation : ");
	 Scanner sc=new Scanner(System.in);
	 int choice;
	 try {
		 choice=sc.nextInt();
	 }
	 catch (InputMismatchException e) {
	        System.out.println("Invalid Input : Enter an Integer ");
	        continue;
	    }
	 
	 
	switch (choice) 
	{
     case 1:
    	
    	u1.input();
    	if(u1.authenticator()==1)
    	{ while(q==1)
    		{ 
    		System.out.println(" 1. Search Product \n 2. Order Product\n 3. Exit");
    		System.out.print("Select an operation : ");
    		try {
    		 choice=sc.nextInt();
    		}
    		catch (InputMismatchException e) {
    	        System.out.println("Invalid Input : Enter an Integer ");
    	        continue;
    	    }
    		 switch (choice)
    		 { 
    		 case 1:
    			
    			 p1.searchProduct();
    			 break;
    		 case 2:
    			 p1.make_order();
    			 break;
    		 case 3:
    			 q=0;
    			 break;
    		 default:
    		        System.out.println("Enter choice from 1 - 3");	 
    		 }
    	  }
    	}
    	
        break;
        
    case 2:
    	u1.register();
    	
        break;
        
    case 3:
    	 p1.searchProduct();
    	 
    	 break;
    	 
    case 4:
    	System.out.println("First Login from your Account");
    	u1.input();
    	if(u1.authenticator()==1)
    	{ while(q==1)
    		{ 
    		
    		System.out.println(" 1. Search Product \n 2. Order Product\n 3. Exit");
    		System.out.print("Select One operation : ");
    		 choice=sc.nextInt();
    	
    		 switch (choice)
    		 { 
    		 case 1:
    			 p1.searchProduct();
    			 break;
    		 case 2:
    			 
    			 p1.make_order();			 
    			 break;
    		 case 3:
    			 q=0;
    			 break;
    		 default:
    		        System.out.println("Enter choice from 1 - 3");	 
    		        
    		 }
    		 System.out.println("");
    	  }
    	}
    	System.out.println("");
    	break;
    	
    case 5:
    	q=0;
        break;
    
    default:
        System.out.println("Enter choice from 1 - 4");
        
    }
	System.out.println("");
	}
	System.out.println("***** Logging off ******  \n Thanks for choosing Co-Help ");
  }

}
