package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
    static ExtentSparkReporter htmlReporter;
    static ExtentReports extent;
    ExtentTest test;

    public static ExtentReports startExtentReport() { //String OS, String browser
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") +"\\test-output\\testReporttest.html");
        System.out.println("Report Saved at: " + System.getProperty("user.dir") +"\\test-output");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("Browser", "");
        extent.setSystemInfo("Mobile Platform", "Android");
        extent.setSystemInfo("Device Name", "LG-M322");
      //  htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("General-Store Mobile Automation Execution Status");
        htmlReporter.config().setReportName("Test Report");
     //   htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config();
        return extent;
    }
}
