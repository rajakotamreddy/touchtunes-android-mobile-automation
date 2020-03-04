package com.touchtunes.Mobile_automation.pages;

import com.aventstack.extentreports.Status;
import com.touchtunes.Mobile_automation.utilities.MobileAppUtil;
import com.touchtunes.Mobile_automation.utilities.PageBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetUpPage extends PageBase {
  public AndroidDriver driver;
  public WebDriverWait wait;
  public static final String logForSkippingDJSelection = "Skipping the DJ selection";
  public static final String logForSelectingDJs = "Successfully selected Dj's";
  public static final String logForSelectingArtists = "Successfully selected Artists";

  public SetUpPage(AndroidDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    wait = new WebDriverWait(driver, 10);
  }

  // *** Page Declaration *** //
  @AndroidFindBy(id = "com.touchtunes.android:id/tt_action_bar_title_image")
  private AndroidElement touchTunesBannerImage;

  @AndroidFindBy(id = "com.touchtunes.android:id/tt_action_bar_right_action_text")
  private AndroidElement skipButton;

  @AndroidFindBy(uiAutomator = "UiSelector().text(\"Alternative\")")
  private AndroidElement alternativeDJSelectionButton;

  @AndroidFindBy(uiAutomator = "UiSelector().text(\"Latin\")")
  private AndroidElement latinDJSelectionButton;

  @AndroidFindBy(uiAutomator = "UiSelector().text(\"Pop\")")
  private AndroidElement popDJSelectionButton;

  @AndroidFindBy(id = "com.touchtunes.android:id/setup_continue_button")
  private AndroidElement continueButton;

  @AndroidFindBy(xpath = "//android.widget.LinearLayout[2]/android.widget.ImageView")
  private List<AndroidElement> artistsList;

  @AndroidFindBy(id = "com.touchtunes.android:id/setup_done_button")
  private AndroidElement setUpDoneButton;

  @AndroidFindBy(id = "com.touchtunes.android:id/location_access_got_it_button")
  private AndroidElement accessLocationButton;

  @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
  private AndroidElement allowLocationButtonInPopup;

  // *** Page Initialization and Utilization *** //
  public void skipDJSelection() {
    wait.until(ExpectedConditions.elementToBeClickable(skipButton));
    MobileAppUtil.tapElement(skipButton);

    logger.log(Status.INFO, logForSkippingDJSelection);
  }

  public void pickThreeDJs() {
    wait.until(ExpectedConditions.elementToBeClickable(alternativeDJSelectionButton));
    MobileAppUtil.tapElement(alternativeDJSelectionButton);
    MobileAppUtil.tapElement(latinDJSelectionButton);
    MobileAppUtil.tapElement(popDJSelectionButton);

    wait.until(ExpectedConditions.visibilityOf(continueButton));
    MobileAppUtil.tapElement(continueButton);

    logger.log(Status.INFO, logForSelectingDJs);
    logger.createNode(logForSelectingDJs);
  }

  public void pickThreeArtists(){
    wait.until(ExpectedConditions.visibilityOf(artistsList.get(0)));
    MobileAppUtil.tapElement( artistsList.get(0));
    MobileAppUtil.tapElement( artistsList.get(1));
    MobileAppUtil.tapElement( artistsList.get(2));

    MobileAppUtil.tapElement(setUpDoneButton);
    logger.log(Status.INFO, logForSelectingArtists);
  }

  public void locationAccess(){
    try {
      MobileAppUtil.tapElement(accessLocationButton);
      MobileAppUtil.tapElement(allowLocationButtonInPopup);
    }catch (NoSuchElementException ee){
      System.out.println(ee);
    }
  }

  public void onboardingSteps(String onboardingStatus) {
    if (onboardingStatus.equalsIgnoreCase("DontSkip")) {
      pickThreeDJs();
      pickThreeArtists();
      locationAccess();
    } else {
      skipDJSelection();
    }
  }
}
