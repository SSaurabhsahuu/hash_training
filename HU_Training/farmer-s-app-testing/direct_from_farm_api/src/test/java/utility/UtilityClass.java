package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class UtilityClass {
    public static ExtentReports extent;
    //for consolidated reports
    public  ExtentReports getReportObject()
    {
        String reportPath=System.getProperty("user.dir")+"/test-output/extentReport/reportExtent.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("API Testing Results");
        reporter.config().setDocumentTitle("Test Results");
        extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Mohammed Akzar A K and saurabh sahu");
        return extent;
    }
}
