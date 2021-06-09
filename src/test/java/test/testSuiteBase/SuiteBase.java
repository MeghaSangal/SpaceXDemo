package test.testSuiteBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import test.utility.ExtentManager;

public class SuiteBase {

    public static final String BASE_PATH = System.getProperty("user.dir");
    public static Logger Logger = null;
    public static Properties Param = null;
    public String SuiteName = null;
    public static final String EMPTY_STRING = "";

    public static boolean initializeFunctionAlreadyExecuted = false;
    public static String reportsPath;

    public SuiteBase() throws Exception {
        if (initializeFunctionAlreadyExecuted != true) {
            initialize();
            initializeFunctionAlreadyExecuted = true;
        }
    }

    private void initialize() throws IOException {
        Logger = Logger.getLogger("rootLogger");

        Logger.info("Initialize Param.properties file.");
        Param = new Properties();
        FileInputStream fip = new FileInputStream(BASE_PATH + "//src//main//resources//Param.properties");
        Param.load(fip);
        Logger.info("Param.properties file loaded successfully.");

        reportsPath = ExtentManager.CreateHTMLDir();
        Logger.info("Initialize function completed");
    }

    public static void ValidatePerDatePrecision(String datePrecision, String dateUTC) {

        switch (datePrecision) {
            case "hour":
                Assert.assertTrue(dateUTC.contains("00.000Z"), "Failed - dateUTC doesn't contain the constant date part as expected as : '00.000Z'");
                break;
            case "day":
                Assert.assertTrue(dateUTC.contains("T00:00:00.000Z"), "Failed - dateUTC doesn't contain the constant date part as expected as : 'T00:00:00.000Z'");
                break;
            case "month":
                Assert.assertTrue(dateUTC.contains("-01T00:00:00.000Z"), "Failed - dateUTC doesn't contain the constant date part as expected as : '-01T00:00:00.000Z'");
                break;
            case "year":
                Assert.assertTrue(dateUTC.contains("-01-01T00:00:00.000Z"), "Failed - dateUTC doesn't contain the constant date part as expected as : '-01-01T00:00:00..000Z'");
                break;
        }
    }
}
