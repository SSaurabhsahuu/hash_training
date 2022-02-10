package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.BaseClass;


import java.io.File;

public class Utility extends BaseClass {
    public static ExtentReports extent;
    public  ExtentReports getReportObject()
    {
        String reportPath=System.getProperty("user.dir")+"/test-output/extentReport/reportExtent.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("UI Testing Results");
        reporter.config().setDocumentTitle("Test Results");
        extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Mohammed Akzar A K and saurabh sahu");
        return extent;
    }
    public String takeScreenShot(String pageName)throws InterruptedException{
        try {
            TakesScreenshot scrnShot =((TakesScreenshot)driver);
            File SrcFile=scrnShot.getScreenshotAs(OutputType.FILE);
            File DestFile=new File("src\\test\\java\\Screenshots\\"+pageName+".png");

            //Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);
            return "src\\test\\java\\Screenshots\\"+pageName+".png";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
