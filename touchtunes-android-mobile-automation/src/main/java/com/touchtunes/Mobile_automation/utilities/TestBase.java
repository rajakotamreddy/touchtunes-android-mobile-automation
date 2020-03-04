package com.touchtunes.Mobile_automation.utilities;

import com.touchtunes.Mobile_automation.pages.SetUpPage;
import com.touchtunes.Mobile_automation.pages.JukeBoxPage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBase extends PageBase {
  private static final ConfigReader configReader = new ConfigReader();
  public static JukeBoxPage jukeboxPage = null;
  public static SetUpPage setUpPage = null;
  public static ConfigReader config = null;

  @BeforeMethod
  public void setUp(ITestResult testResult) {
    logger = reports.createTest(testResult.getMethod().getMethodName());
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    jukeboxPage = new JukeBoxPage(driver);
    setUpPage = new SetUpPage(driver);

    driver.launchApp();
  }

  @BeforeSuite
  public void initBase() throws Exception {
    initialize();
  }

  @AfterMethod
  public void closeApp(ITestResult testResult) throws IOException {
    try {
      captureScreenShot(testResult, driver);
    } catch (AssertionError e) {
      e.printStackTrace();
      System.out.println("UI VALIDATION FAILED - NO SCREENSHOT");
    }
    reports.flush();
    driver.closeApp();
  }

  @AfterSuite
  public static void afterSuite() {
    if (driver != null) {
      driver.quit();
    }
    if (service != null) {
      service.stop();
    }
  }
}
