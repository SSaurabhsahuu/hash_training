import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

class Array_of_string
{ String[] arr;
  
  public void add(String str)
  { 
	  
  }
	
}
public class GemWords {

	public static void main(String[] args) {
		String s1;
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter string of words : ");
		s1=sc.nextLine();
		
		ArrayList<String> list=new ArrayList<String>() ;
		
		int len=0;
	    char[] ch=s1.toCharArray();
//		  for(int i=0;i<s1.length();i++)
//		  {  
//			  if(ch[i]==' ')	  
//				  len++;
//		  }
		  
		  String t = new String();

		  for(int i=0;i<s1.length();i++)
		  { if(ch[i]!=' ' && i+1 < s1.length())
		    {
			  t+=ch[i]; 
		    }
		  else {
			  if(i+1 == s1.length())
				  t+=ch[i]; 
			  list.add(t);
			  t = new String();
		    }
		  }
		  
//		  java.util.Iterator<String> itr=list.iterator();  
//		  while(itr.hasNext()){
//		  System.out.println(itr.next());  
//		  }  
		  int count=0;
		  for(int i=0;i<list.size();i++)
		  { char[] temp=list.get(i).toCharArray();
		     for(int j=0;j<list.get(i).length();j++)
			  { if( (j==0 || j==list.get(i).length()-1) && (temp[j]=='o' || temp[j]=='O'))
			       count++;
			   
				  
			  }
		  }
		  
		  System.out.println("Number of Gem Words = "+count);
	}

}
