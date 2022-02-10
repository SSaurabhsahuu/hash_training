import java.util.Scanner;

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

public class Password_validation {

	public static void main(String[] args) {
		
		String str;
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter Password :");
		str=sc.next();
		
		try {
			if(str.length()<5 || str.length()>10)
			{
				throw new Range_validation("Length not between 5 & 10 ");
			}
		}
		catch(Range_validation rv)
		{
			rv.printStackTrace();
		}
		try {
			char[] ch=str.toCharArray();
			int t=0;
			for(int i=0;i<str.length();i++)
			{if(ch[i]>='A' && ch[i]<='Z')
			  t++; 
			}
			if(t==0)
				throw new Uppercase_validation("No Uppercase letter Found ");
		}
		catch(Uppercase_validation rv)
		{
			rv.printStackTrace();
		}
		try {
			char[] ch=str.toCharArray();
			int t=0;
			for(int i=0;i<str.length();i++)
			{if(ch[i]>='a' && ch[i]<='z')
			  t++;	
			}
			if(t==0)
				throw new Lowercase_validation("No Lowercase letter Found ");
		}
		catch(Lowercase_validation rv)
		{
			rv.printStackTrace();
		}
		try {
			char[] ch=str.toCharArray();
			int t=0;
			for(int i=0;i<str.length();i++)
			{if((ch[i]>=' ' && ch[i]<='/')||(ch[i]>=':' && ch[i]<='@')||(ch[i]>='[' && ch[i]<='`')||(ch[i]>='{' && ch[i]<='~') )
			  t++;	
			}
			if(t==0)
				throw new Special_char_validation("No Special Character Found ");
		}
		catch(Special_char_validation rv)
		{
			rv.printStackTrace();
		}
		try {
		char[] ch=str.toCharArray();
		int t=0;
		for(int i=0;i<str.length();i++)
		{if(ch[i]>='0' && ch[i]<='9')
		  t++;	
		}
		if(t==0)
			throw new Digit_validation("No Digit Found ");
		
		}
		catch(Digit_validation rv)
		{
			rv.printStackTrace();
		}

}
}
