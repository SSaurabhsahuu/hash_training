import java.util.Scanner;

public class LCM {
   public static int gcd(int a,int b)
   { if(b==0)
	   return a;
     if(b==1) 
	   return 1;
   	else
   	{ if(a>=b)
   		return gcd(b,a%b);
   	else
   		return gcd(a,b%a);
   		
   	}
	   
   }
	public static void main(String[] args) {
		int a,b;
		Scanner sc=new Scanner(System.in);
		a = sc.nextInt();
		b = sc.nextInt();
		
		int g=gcd(a,b);
		// System.out.println(g);
		int lcm=a*b/g;
		System.out.println(lcm);

	}

}
