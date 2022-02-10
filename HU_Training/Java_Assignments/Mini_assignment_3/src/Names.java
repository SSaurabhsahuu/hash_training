import java.util.Scanner;

public class Names {

	public static void main(String[] args) {
		String s1;
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter a Name : ");
		s1=sc.nextLine();
		
		char[] ch=s1.toCharArray();
		
		int f=1;
		for(int i=0;i<s1.length();i++)
		{
			if(ch[i]=='a' || ch[i]=='e' || ch[i]=='i' || ch[i]=='o' || ch[i]=='u' || ch[i]=='A' || ch[i]=='E' || ch[i]=='I' || ch[i]=='O' || ch[i]=='U')
			{
				f=0;
				break;
			}
		}
		
		if(f==1)
			System.out.println("Eureka!");
		else
			System.out.println("No Luck!");
	}

}
