import java.util.Scanner;

public class Binomial_coefficient extends Exception
{   Binomial_coefficient(String s1)
	{ super(s1);
	}
	static int bi_cof(int n,int k)
	{
		try {
		if (k > n)
		{ Binomial_coefficient b= new Binomial_coefficient("k > n");
		  
		  throw b;		
		}
		}
		catch(Binomial_coefficient bi)
		{ bi.printStackTrace();
			return 0;
			
		}
			if (k == 0 || k == n)
			return 1;
		
		
		return bi_cof(n-1,k-1)+bi_cof(n-1,k);
		
	}
	public static void main(String[] args) {
		
		int n,k;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter n & k");
		n=sc.nextInt();
		k=sc.nextInt();
		
		System.out.println("Binomial Coefficient (1+x)^"+n+" = "+bi_cof(n,k));
		
	}

}
