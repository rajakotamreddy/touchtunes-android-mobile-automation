package com.touchtunes.Mobile_automation.pages;

import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.touchtunes.Mobile_automation.utilities.MobileAppUtil;
import com.touchtunes.Mobile_automation.utilities.PageBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

/**
 * @author rajakotamreddy
 */
public class JukeBoxPage extends PageBase {

  public AndroidDriver driver;
  public WebDriverWait wait;
  public static final String logForSelectingJukebox = "Successfully selected first Jukebox";
  public static final String logForCreatingVerticalArtistList = "Successfully created list of artists in Hot Artists page";
  public static final String logForCreatingHorizontalArtistList = "Successfully created list of first four artists in homepage";
  public static final String logForComparingLists = "Successfully validated List content";
  public static final String logForComparingListsFail = "List content validation failed";

  public JukeBoxPage(AndroidDriver driver) {
    this.driver = driver;
    PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    wait = new WebDriverWait(driver, 10);
  }

  // *** Page Declaration *** //
  @AndroidFindBy(xpath = "(//android.view.ViewGroup/android.widget.TextView[1])[1]")
  private MobileElement jukeBoxList;

  @AndroidFindBy(id = "com.touchtunes.android:id/ttab_title_text")
  private MobileElement jukeBoxTitle;

  @AndroidFindBy(uiAutomator = "UiSelector().textContains(\"HOT AT \")")
  private MobileElement hotAtJukeBoxSection;

  @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView")
  private List<MobileElement> accountOptions;

  @AndroidFindBy(id = "com.touchtunes.android:id/tt_action_bar_title_text")
  private MobileElement hotAtJukeBoxTitle;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.touchtunes.android:id/widget_item_round_title']")
  private List<MobileElement> hotAtJukeBoxList;

  @AndroidFindBy(uiAutomator = "UiSelector().textContains(\"Hot at \")")
  private MobileElement hotAtJukeBoxHomeRowTitle;

  @AndroidFindBy(uiAutomator = "UiSelector().text(\"Hot Artists\")")
  private MobileElement hotArtistsButton;

  @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.touchtunes.android:id/item_artist_view_name']")
  private List<MobileElement> hotArtistsNamesList;

  @AndroidFindBy(id = "com.touchtunes.android:id/item_empty_reload_button")
  private MobileElement reloadButton;

  // *** Page Initialization and Utilization *** //
  public void selectJukeBox() {
    try{
    wait.until(ExpectedConditions.visibilityOf(jukeBoxList));
    MobileAppUtil.tapElement(jukeBoxList);
    wait.until(ExpectedConditions.visibilityOf(jukeBoxTitle));
    }catch (TimeoutException e){
      MobileAppUtil.tapElement(reloadButton);
      MobileAppUtil.tapElement(jukeBoxList);
    }

    logger.log(Status.INFO, logForSelectingJukebox);
  }

  public List<String> hotAtJukeBoxHorizontalSection() {
    MobileAppUtil.scrollDown();
    MobileAppUtil.scrollDown();
    MobileAppUtil.scrollDown();

    List<String> horizontalArtistList = new ArrayList<>();
    for (MobileElement artist : hotAtJukeBoxList) {
      horizontalArtistList.add(artist.getText());
    }
    System.out.println(horizontalArtistList);

    logger.log(Status.INFO, logForCreatingHorizontalArtistList);
    return horizontalArtistList;
  }

  public List<String> hotAtJukeBoxVerticalSection() {
    MobileAppUtil.scrollDown();
    MobileAppUtil.scrollDown();

    wait.until(ExpectedConditions.visibilityOf(hotAtJukeBoxHomeRowTitle));
    MobileAppUtil.tapElement(hotAtJukeBoxHomeRowTitle);

    wait.until(ExpectedConditions.visibilityOf(hotArtistsButton));
    MobileAppUtil.tapElement(hotArtistsButton);

    List<String> verticalArtistList = new ArrayList<>();

//    for (int i =0; i<4;i++) {
//      artistList.add(hotArtistsNamesList.get(i).getText());
//    }

    for (MobileElement artist : hotArtistsNamesList) {
      verticalArtistList.add(artist.getText());
      MobileAppUtil.scrollDown();
    }
    System.out.println(verticalArtistList);

    logger.log(Status.INFO, logForCreatingVerticalArtistList);
  return verticalArtistList;
  }

  public void compareVerticalListWithHorizontalArtistList(){

    List<String> horizontal = hotAtJukeBoxHorizontalSection();
    List<String> vertical = hotAtJukeBoxVerticalSection();
    boolean flagT = true;
    for(String hor: horizontal) {
      if (!vertical.contains(hor)) {
        flagT=false;
      }
    }
    if (flagT) {
      logger.log(Status.PASS, logForComparingLists);
      logger.createNode(logForComparingLists);
    }else{
      logger.log(Status.FAIL, logForComparingListsFail);
    }
  }
}
