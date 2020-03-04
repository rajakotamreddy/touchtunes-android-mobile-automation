package com.touchtunes.Mobile_automation.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.touchtunes.Mobile_automation.utilities.PageBase;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * @author rajakotamreddy
 *
 */
public class MyListener extends TestListenerAdapter {

  @Override
  public void onTestSuccess(ITestResult result) {
    System.out.println("on test success");
    PageBase.logger.pass(MarkupHelper.createLabel(result.getMethod().getMethodName() + " --> Test Case PASSED", ExtentColor.GREEN));
  }

  @Override
  public void onTestFailure(ITestResult result) {
    System.out.println("on test failure");
    PageBase.logger.fail(MarkupHelper.createLabel(result.getMethod().getMethodName() + " --> Test Case Failed", ExtentColor.RED));
    PageBase.logger.fail(result.getThrowable() + " --> Test Case Failed");
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    System.out.println("on test skipped");
    PageBase.logger.skip(MarkupHelper.createLabel(result.getMethod().getMethodName() + " --> Test Case Skipped", ExtentColor.ORANGE));
  }
}
