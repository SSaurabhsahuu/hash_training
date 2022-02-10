import java.util.Scanner;

class Pallindrome
{
    String str;

    public void input()
    { System.out.print("Enter a string : ");
	  Scanner sc=new Scanner(System.in);
	  str=sc.next();           // use str="abc"    str="acbba"
    }
    public int check()
    { int n=str.length();
       char ch[]=str.toCharArray();
      for(int i=0;i<=n/2;i++)
      {
          if(ch[i] != ch[n-1-i])
          {
              return 0;
          }
      }
      return 1;

    }
   public char[] swap(char ch[],int a,int b)
    {  
	   char temp=ch[a];
    
        ch[a]=ch[b];
        ch[b]=temp;
     return ch;
    }
    public int convertible()
    {int n=str.length();
     char ch[]=str.toCharArray();
     int[] store=new int[26];
     int ev=0,od=0;
     for(int i=0;i<n;i++)
     { store[ch[i]-'a']++;
     }
     for(int i=0;i<26;i++)
     { if(store[i]%2==0)
         ev+=store[i];
       else
        { od+=store[i]%2;
          ev+=store[i]-od;
        }

     }
     if(ev==0 && od==1)
        return 1;
     if(od<=1)
        return 1;
     else
        return 0;
    }
   public int count_swaps()
    { int n=str.length();
      char ch[]=str.toCharArray();
      int l=0,h=n-1;
      int c=0;
      for(int i=0;i<=n/2;i++)
      { if(ch[l]!=ch[h])
        { c++;
        	System.out.println("inside if "+c);
          int j=l+1;
          while(ch[j]!=ch[h] && j<h)
          { j++;
          }
          if(j==h)           // if str =  ababc                acbba
            ch=swap(ch,j,n/2);
          else
            ch=swap(ch,j,l);

        }
      h--;
      l++;
      }
      return c;

    }
}

public class Sample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pallindrome s1=new Pallindrome();
		
		s1.input();    
		
		if(s1.check()==1)
		    {  System.out.println("Yes Pallindrome");

		    }
		else
		    {
		        if(s1.convertible()==1)
		        { // System.out.println("inside if");
		        	int count=s1.count_swaps();
		            System.out.println("Not a Pallindrome but can be converted to pallindrome by "+count+" swaps");
		        }
		        else
		        { System.out.println("Not a Pallindrome and cannot be converted to Pallindrome");
		        // System.out.println("inside else");
		        }
		    }
	}
}
