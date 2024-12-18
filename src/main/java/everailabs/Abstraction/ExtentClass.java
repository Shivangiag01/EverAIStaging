package everailabs.Abstraction;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentClass {
	
	public ExtentReports getextentReportObject() {
	
	String filepath= System.getProperty("user.dir")+"//Reports//index.html";
	ExtentSparkReporter sp= new ExtentSparkReporter(filepath);
	
	ExtentReports ep= new ExtentReports();
	ep.attachReporter(sp);
	return ep;

}
}