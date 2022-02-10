package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import pages.BaseClass;
import testing.DirectFromFieldTest;
import utilities.Utility;


public class Listeners extends BaseClass implements ITestListener {
    public Logger log= LogManager.getLogger(DirectFromFieldTest.class.getName());
    Utility utility=new Utility();
    ExtentReports extentReports=utility.getReportObject();
    ExtentTest extentTest;
    ThreadLocal<ExtentTest> localtest=new ThreadLocal<ExtentTest>();    // for parallel tests

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        Reporter.log("Test Started");
        extentTest=extentReports.createTest(result.getMethod().getMethodName());
        extentReports.flush();
        log.info("Test started for "+result.getName());
        localtest.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        Reporter.log("Success of test cases : "+result.getName());
        localtest.get().log(Status.PASS,"Success of test cases : "+result.getName());
        extentReports.flush();
        log.info("Taking ScreenShot of successful for "+result.getName());
        //utility.takeScreenShot(result.getName()+"success");
        //        driver.close();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        Reporter.log("Failure of test cases: "+result.getName());
        localtest.get().fail(result.getThrowable());             // show full test fail description
        localtest.get().log(Status.FAIL,"Failure of test cases: "+result.getName());
        extentReports.flush();

        try {
            log.info("Taking ScreenShot of failure for "+result.getName());
            // shows screenshot in extent report
            localtest.get().addScreenCaptureFromPath(utility.takeScreenShot(result.getName()+"fail"),result.getName()+"fail");
           // utility.takeScreenShot(result.getName()+"fail");
        } catch (InterruptedException e) {
            log.fatal(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        Reporter.log("Skip of test cases : "+result.getName());
        localtest.get().log(Status.SKIP,"Skip of test cases : "+result.getName());
        extentReports.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        Reporter.log("Failure of test cases: "+result.getName());
        localtest.get().log(Status.FAIL,"Failure of test cases: "+result.getName());
        extentReports.flush();
        try {
            utility.takeScreenShot(result.getName()+"fail");
        } catch (InterruptedException e) {
            log.fatal(e);
        }
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        Reporter.log("Testing is completed");
        localtest.get().log(Status.INFO,"Testing is completed");
        extentReports.flush();
        log.info("Closing the driver");
        driver.close();
        Reporter.log("Driver is closed");
    }
}
