import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Forgotten_numbers {

	public static void main(String[] args) {
		int n;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter n :");
		n=sc.nextInt();
		
		LinkedHashSet<String> set=new LinkedHashSet<String>(); 
		
		System.out.print("Enter "+n+" Numbers that Keethan remembers ");
		for(int i=0;i<n;i++)
		{ String a;
			a=sc.next();
			set.add(a);
		}
		System.out.print("Enter range i & j");
		int i,j;
		i=sc.nextInt();
		j=sc.nextInt();
		
		
		ArrayList<Integer> forgot=new ArrayList<Integer>();
		
		for(int k=i;k<=j;k++)
		{ 
			if(!set.contains(Integer.toString( k ) ) )
			{  forgot.add(k);
			}
		}
		System.out.print("Numbers that Keethan Forgot : ");
		for(int f:forgot)
		{
			System.out.print(f+" ");
		}

	}


}
