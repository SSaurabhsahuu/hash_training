package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testing.BaseClass;
import utility.UtilityClass;

public class Listeners extends BaseClass implements ITestListener {

    public Logger log= LogManager.getLogger(BaseClass.class.getName());
    UtilityClass utility=new UtilityClass();
    ExtentReports extentReports=utility.getReportObject();;
    ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest=extentReports.createTest(result.getMethod().getMethodName());
        extentReports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
       extentTest.log(Status.PASS,"Success of test cases : "+result.getName());
       extentReports.flush();

//        driver.close();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.log(Status.FAIL,"Failure of test cases: "+result.getName());
        extentReports.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.log(Status.SKIP,"Skip of test cases : "+result.getName());
        extentReports.flush();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
        extentTest.log(Status.FAIL,"Failure of test cases: "+result.getName());
        extentReports.flush();
    }

    @Override
    public void onStart(ITestContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        extentTest.log(Status.INFO,"Testing is completed");
        extentReports.flush();
    }
}
