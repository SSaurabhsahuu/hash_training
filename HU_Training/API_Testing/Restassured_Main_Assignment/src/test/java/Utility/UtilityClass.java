package Utility;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class UtilityClass {
    public Logger logger = null;
    public ExtentReports er;
    public ExtentTest test;


    public void setup()
    {   String path=System.getProperty("user.dir");
        String ExtentPath=path+"/test-output/ExtentReports/IntegrateWithExtentReporting.html";
        er=new ExtentReports(ExtentPath);
        test=er.startTest(" Validate /Carts");

        String log4jpath=System.getProperty("user.dir")+"/log4j.properties";
        PropertyConfigurator.configure(log4jpath);

    }

    public void close_setup()
    { er.endTest(test);
        er.flush();
        er.close();
    }
}
