package Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ListenerTest implements ITestListener {
    @Override
    public void onFinish(ITestContext arg0) {
    }

    @Override
    public void onStart(ITestContext Result) {
        Reporter.log("Test :"+Result.getName()+" started");
    }
    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult Result)
    {    Reporter.log("The name of the testcase failed is :"+Result.getName());
        String str="Failure "+Result.getName();
        try {
        BufferedImage img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(img, "png", new File("src\\test\\java\\Screenshots\\"+str+".png"));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult Result)
    {
        Reporter.log("The name of the testcase Skipped is :"+Result.getName());
    }

    // When Test case get Started, this method is called.
    @Override
    public void onTestStart(ITestResult Result)
    {
        System.out.println(Result.getName()+" test case started");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult Result)
    {   Reporter.log("The name of the testcase passed is :"+Result.getName());
        String str="Success "+Result.getName();
        try {
            BufferedImage img = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(img, "png", new File("src\\test\\java\\Screenshots\\"+str+".png"));
        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub

    }


}
