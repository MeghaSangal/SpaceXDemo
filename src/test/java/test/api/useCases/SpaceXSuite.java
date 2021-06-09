package test.api.useCases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.api.common.SpaceClient;
import test.api.model.Space;
import test.testSuiteBase.SuiteBase;
import test.utility.ExtentManager;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static test.api.common.SpaceClient.getSpaceData;

public class SpaceXSuite extends SuiteBase {

    private static Response response = null;
    private static Space space = null;
    private static String dateUTC = EMPTY_STRING;
    private static String dateLocal = EMPTY_STRING;
    private static int dateUnix;
    private static String datePrecision = EMPTY_STRING;
    private static boolean tbd;
    private static boolean net;
    ExtentTest test;
    private ExtentReports extent;

    public SpaceXSuite() throws Exception {
        SuiteName = "APITest_SpaceXSuite";
    }

    @BeforeClass
    public void setUp() {
        Logger.info("BeforeTest file");
        final String Path = System.getProperty("ReportDirectoryPath");
        Logger.info("BeforeTest file Path " + Path);
        extent = ExtentManager.Instance(Path, SuiteName);

        response = SpaceClient.getLatestLaunchDetails("latest");
        space = getSpaceData(response);
    }

    @Test
    public void VerifyStatusCode() {
        try {
            test = extent.createTest("VerifyStatusCode");

            Logger.info("Validating the status code from the response as : " + Param.getProperty("CodeSuccess"));
            test.log(Status.INFO, "Validating the status code from the response as : " + Param.getProperty("CodeSuccess"));
            Assert.assertEquals(Integer.toString(response.getStatusCode()), Param.getProperty("CodeSuccess"));

        } catch (Exception e) {
            Logger.error("Exception occurred while trying to verify the status code from user response as :" + e.getMessage(), e);
            test.log(Status.ERROR, "Exception occurred while trying to verify the status code from user response as :" + e.getMessage());
        }
    }

    @Test
    public void VerifyTbdAndNetData() {
        try {
            test = extent.createTest("VerifyTbdAndNetData");
            dateUTC = space.getDateUtc();
            dateUnix = space.getDateUnix();
            dateLocal = space.getDateLocal();
            datePrecision = space.getDatePrecision();

            tbd = space.getTbd();
            net = space.getNet();

            Logger.info("Validating the impact of TBD and NET values on the DateUTC, DateLocal, DateUNIX data in the response.");
            test.log(Status.INFO, "Validating the impact of TBD and NET values on the DateUTC, DateLocal, DateUNIX data in the response.");

            if (tbd && net) {
                Assert.assertEquals(dateUTC, null, "Failed - 'DateUTC should be null.");
                Assert.assertEquals(dateLocal, null, "Failed - DateLocal should be null.");
                Assert.assertEquals(dateUnix, null, "Failed - DateUnix should be null.");
            } else {
                Assert.assertNotEquals(dateUTC, null, "Failed - 'DateUTC should not be null.");
                Assert.assertNotEquals(dateLocal, null, "Failed - DateLocal should not be null.");
                Assert.assertNotEquals(dateUnix, null, "Failed - DateUnix should not be null.");
            }
        } catch (Exception e) {
            Logger.error("Exception occurred while trying to verify the tbd and net data impact from user response as :" + e.getMessage(), e);
            test.log(Status.ERROR,"Exception occurred while trying to verify the tbd and net data impact from user response as :" + e.getMessage());
        }
    }

    @Test
    public void VerifyDateUnixConversion() {
        try {
            test = extent.createTest("VerifyDateUnixConversion");
            if (!(tbd && net)) {
                Logger.info("Formatting the dateUnix : " + dateUnix + " to UTC format and verifying it has an expected value in the response as : " + dateUTC);
                test.log(Status.INFO, "Formatting the dateUnix : " + dateUnix + " to UTC format and verifying it has an expected value in the response as : " + dateUTC);

                Date date = new java.util.Date(dateUnix * 1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                String formattedDate = sdf.format(date);
                formattedDate = formattedDate.replace("+0000", "Z");

                Logger.info("Formatted unix date to UTC date : " + formattedDate);
                test.log(Status.INFO, "Formatted unix date to Local date : " + formattedDate);
                Assert.assertEquals(formattedDate, dateUTC, "Failed - Value in the response for dateUTC is incorrect.");

                Logger.info("Formatting the dateUnix : " + dateUnix + " to Local format and verifying it has an expected value in the response as : " + dateLocal);
                test.log(Status.INFO, "Formatting the dateUnix : " + dateUnix + " to Local format and verifying it has an expected value in the response as : " + dateLocal);
                sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                sdf.setTimeZone(TimeZone.getTimeZone("GMT-4"));
                formattedDate = sdf.format(date);

                Logger.info("Formatted unix date to Local date : " + formattedDate);
                test.log(Status.INFO, "Formatted unix date to Local date : " + formattedDate);
                Assert.assertEquals(formattedDate, dateLocal, "Failed - Value in the response for dateLocal is incorrect.");
            } else {
                Logger.info("TBD and NET values are true, so the dates data is not relevant for further validation.");
                test.log(Status.INFO, "TBD and NET values are true, so the dates data is not relevant for further validation.");
            }
        } catch (Exception e) {
            Logger.error("Exception occurred while trying to verify the date unix conversion from user response as :" + e.getMessage(), e);
            test.log(Status.ERROR,"Exception occurred while trying to verify the date unix conversion from user response as :" + e.getMessage());
        }
    }

    @Test
    public void VerifyDatePrecision() {
        try {
            test = extent.createTest("VerifyDatePrecision");
            if (!(tbd && net)) {
                Logger.info("Validating if the date precision value : " + datePrecision + " in the response is under the valid range.");
                test.log(Status.INFO, "Validating if the date precision value : " + datePrecision + " in the response is under the valid range.");

                List<String> datePrecisionValues = Arrays.asList("quarter", "half", "year", "month", "day", "hour");
                Assert.assertTrue(datePrecisionValues.contains(datePrecision), "Failed - The date precision value obtained in response is not in valid range.");

                ValidatePerDatePrecision(datePrecision, dateUTC);
            } else {
                Logger.info("TBD and NET values are true, so the dates data is not relevant for further validation.");
                test.log(Status.INFO, "TBD and NET values are true, so the dates data is not relevant for further validation.");
            }
        } catch (Exception e) {
            Logger.error("Exception occurred while trying to verify the date precision data from user response as :" + e.getMessage(), e);
            test.log(Status.ERROR, "Exception occurred while trying to verify the date precision data from user response as :" + e.getMessage());
        }
    }

    @AfterClass
    public void writeToReport() {
        extent.flush();
    }
}