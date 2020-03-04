package com.touchtunes.Mobile_automation.utilities;

import static java.time.Duration.ofSeconds;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class MobileAppUtil extends TestBase {

  // Method for tapping element
  public static void tapElement(MobileElement element) {
    TouchAction action = new TouchAction(driver);
    action.tap(new TapOptions().withElement(new ElementOption().withElement(element))).perform();
  }

  public static void scrollDown() {
    Dimension dimension = driver.manage().window().getSize();

    Double scrollHeightStart = dimension.getHeight() * 0.5;
    int scrollStart = scrollHeightStart.intValue();

    Double scrollHeightEnd = dimension.getHeight() * 0.2;
    int scrollEnd = scrollHeightEnd.intValue();

    new TouchAction(driver).press(PointOption.point(0, scrollStart)).waitAction(WaitOptions.waitOptions(ofSeconds(2)))
        .moveTo(PointOption.point(0, scrollEnd)).release().perform();
  }
}
