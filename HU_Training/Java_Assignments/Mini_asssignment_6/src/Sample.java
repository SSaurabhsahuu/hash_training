import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;  

public class Sample {

	public static void main(String[] args) {

String line = "";  
 
try   
{  
// for reading data from    StudentData.csv    file
BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\saurabh\\eclipse-workspace\\Mini_asssignment_6\\src\\StudentData.csv"));  
// FileWriter fw=new FileWriter("C:\\Users\\saurabh\\eclipse-workspace\\Mini_asssignment_6\\src\\SelectedStudent.csv");

// for Writing data in   SelectedStudent.csv    file
File file = new File("C:\\\\Users\\\\saurabh\\\\eclipse-workspace\\\\Mini_asssignment_6\\\\src\\\\SelectedStudent.csv");
FileWriter outputfile = new FileWriter(file);
CSVWriter writer = new CSVWriter(outputfile);
String[] header = { "Registration_no", "Name", "Gender", "SSC_percent","HSC_percent","Btech_percentage" };
writer.writeNext(header);

int c=0;
while ((line = br.readLine()) != null)   
{  String str;
 c++;
String[] student = line.split(",");    // use comma as separator  
 if(c!=1 && Double.parseDouble(student[3])>75.0 && Double.parseDouble(student[4])>75.0 && Double.parseDouble(student[5])>75.0)
 { 
	    writer.writeNext(student);
	 
 }
// System.out.println("Registration_no=" + student[0] + ", Name=" + student[1] + ", gender=" + student[2] + ", Contact=" + student[3] + ", Salary= " + student[4] + ", City= " + student[5] +"]");  
}
writer.close();
FileReader fr=null;

	fr=new FileReader("C:\\Users\\saurabh\\eclipse-workspace\\Mini_asssignment_6\\src\\SelectedStudent.csv");
	
	int ch;
	while((ch=fr.read())!=-1)
		System.out.print((char)ch);
	
	fr.close();	
}   
catch (IOException e)   
{  
e.printStackTrace();  
} 

	}

}
