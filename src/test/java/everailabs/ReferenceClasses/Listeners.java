package everailabs.ReferenceClasses;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import everailabs.Abstraction.ExtentClass;

public class Listeners extends BasicInitialization implements ITestListener{

	ExtentTest test;
	ExtentClass ec= new ExtentClass();
	ExtentReports ep= ec.getextentReportObject();
	
	@Override
	public void onTestStart(ITestResult result) {
		 test= ep.createTest(result.getMethod().getMethodName());
		 System.out.println("Test Started: " + result.getMethod().getMethodName());
	     System.out.println("Driver instance: " + driver);
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
		ITestListener.super.onTestSuccess(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
	  test.fail(result.getThrowable());
	  String screenshotpath=null;
	  try {
		  screenshotpath= getScreenShot(result.getMethod().getMethodName());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if (screenshotpath != null) {
	  test.addScreenCaptureFromPath(screenshotpath, result.getMethod().getMethodName());}
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		ep.flush();
	}
	
	
	

}
