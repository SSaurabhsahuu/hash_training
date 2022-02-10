import java.util.Scanner;

public class String_compare {

	public static void main(String[] args) {
		String s1,s2;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter 2 Strings ");
		s1=sc.nextLine();
		s2=sc.nextLine();
		
		char[] c1=s1.toCharArray();
		char[] c2=s2.toCharArray();
		
		int count=0,f=0;
		for(int i=0;i<s1.length();i++)
		{ 
			for(int j=0;j<s2.length();j++)
			{ int l=i,h=j;
				while( l<s1.length() && h<s2.length() && c1[l]==c2[h])
				{ l++;
				  h++;
				  count++;
				  if(count==3)
				  {
					  f=1;
					  break;
				  }
					
				}
				count=0;
				
			}
			if(f==1)
				break;
		}
		
		if(f==1)
			System.out.println("Hurray !! ");
		else
			System.out.println("Oops! ");
	}

}
