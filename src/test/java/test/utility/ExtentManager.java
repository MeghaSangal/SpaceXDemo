package test.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import test.testSuiteBase.SuiteBase;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager extends SuiteBase {

    public ExtentManager() throws Exception {
        super();
    }

    public static String CreateHTMLDir() {
        String destDir = (BASE_PATH + "\\TestReport");
        Logger.info("HTMLDir Path  destDir " + destDir);
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy-hh-mm a");
        String destDir1 = destDir + "/" + dateFormat1.format(new Date());
        new File(destDir1).mkdirs();

        String Path = destDir1;
        System.out.println(Path);
        Logger.info("HTMLDir Path " + Path);
        System.setProperty("ReportDirectoryPath", Path);
        return Path;
    }

    public static ExtentReports Instance(String Path, String SuiteName) {
        DateFormat dateFormat1 = new SimpleDateFormat("dd-MMM-yyyy-hh-mm-s a");
        String Path1 = Path + "/" + SuiteName + "__" + dateFormat1.format(new Date()) + ".html";
        System.out.println(Path1);
        System.out.println(System.getProperty("os.name"));
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(Path1);
        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setReportName("Rest Assured Automation");
        htmlReporter.config().setDocumentTitle("Automation");
        htmlReporter.config().setCSS("body:not(.default) {overflow: scroll !important;}");

        return extent;
    }
}
