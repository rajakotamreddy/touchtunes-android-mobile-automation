package com.touchtunes.Mobile_automation.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * @author rajakotamreddy
 */
public class PageBase {

  public static ExtentReports reports;
  public static ExtentTest logger;
  public ExtentHtmlReporter htmlReporter;
  private static final int port = 4723;
  private static final String tekeHomeTest = "TakeHomeTest";
  private static final String killServer = "/usr/bin/killall -KILL node";
  private static final String dateFormat = "yyyyMMdd";
  private static final String reportFormat = ".html";
  private static final String imageFormat = ".png";
  private static final String errorValidationMessage = "UI VALIDATION IS FAILED";
  private static final String mobileCapabilityGpsEnabled = "gpsEnabled";
  public static AppiumDriverLocalService service;
  public static AndroidDriver driver = null;

  // initializing the Tests
  public void initialize() throws Exception {
    boolean isInitalized = false;
    if (!isInitalized) {
      DesiredCapabilities capabilities = new DesiredCapabilities();

      String os = System.getProperty("os.name");

      if (os.equals("Mac OS X")) {
        startServer();
        File appDir;
        appDir = new File(System.getProperty("user.dir") + ConfigReader.getAndroidAppLocation());

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigReader.getAndroidEmulatorName());
        capabilities.setCapability(MobileCapabilityType.APP, appDir.getAbsolutePath());
      }

      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
      capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
      capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
      capabilities.setCapability(mobileCapabilityGpsEnabled, "true");

      driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
      //Set location to montreal 
      driver.setLocation(new Location(45.5017 ,-73.5673, 5));

      // ExtentReport Initialization
      String reportsDirPath = "src/main/resources/reports/";
      htmlReporter = new ExtentHtmlReporter(reportsDirPath + tekeHomeTest + new SimpleDateFormat(dateFormat).format(new Date()) + reportFormat);
      htmlReporter.config().enableTimeline(false);
      htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
      htmlReporter.config().setTheme(Theme.STANDARD);

      reports = new ExtentReports();
      reports.attachReporter(htmlReporter);
      reports.setSystemInfo("OS", System.getProperty("os.name"));

      isInitalized = true;
    } else {
      System.out.println("Driver is already initialized...");
    }
  }

  public AppiumDriverLocalService startServer() throws IOException {
    killServer();

    service = AppiumDriverLocalService.buildDefaultService();
    service.start();

    return service;
  }

  public static void killServer() throws IOException {
    Runtime.getRuntime().exec(killServer);
  }

  public static void captureScreenShot(ITestResult testResult, AndroidDriver driver) throws IOException {
    if (testResult.getStatus() == ITestResult.FAILURE) {
      // TakeScreenShots Initialization
      String screenshotsDirPath = System.getenv("SCREENSHOTS_DIRECTORY_PATH");
      if (screenshotsDirPath == null) {
        screenshotsDirPath = "./src/main/resources/reports/screenshots/";
      }

      File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String destPath = screenshotsDirPath + testResult.getName() + imageFormat;
      File destination = new File(destPath);
      FileUtils.copyFile(scrFile, destination);
      logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath("./screenshots/" + testResult.getName() + imageFormat));
      System.out.println(errorValidationMessage);
    }
  }
}
