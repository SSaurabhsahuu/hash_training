import java.util.Scanner;

public class Pattern {

	public static void main(String[] args) {
		
		int n;
		Scanner sc=new Scanner(System.in);
		n = sc.nextInt();
		
		int t=0;
		for(int i=1;i<=n;i++)
		{
			for(int j=1;j<=n;j++)
			{   if(i==1 || i==n || j==1 || j==n)
					System.out.print("*");
				else
				{ if(j==t+1 || j==n-t )
				  { System.out.print("*");
				  }
				else
					System.out.print(" ");
				}
			}
			System.out.println("");
			
			t++;
			
		}
	}

}
