package com.touchtunes.Mobile_automation.jukeboxPageTests;

import com.touchtunes.Mobile_automation.utilities.TestBase;
import org.testng.annotations.Test;

public class JukeBoxPageTests extends TestBase {
  private static final String dontSkipOnboarding = "DontSkip";

  @Test(priority = 1, enabled = true, description = "Take Home Test")
  public void takeHomeTest() {
    // Onboarding process
    setUpPage.onboardingSteps(dontSkipOnboarding);

    //Select first Jukebox
    jukeboxPage.selectJukeBox();

    //Compare horizontal artist list with vertical list
    jukeboxPage.compareVerticalListWithHorizontalArtistList();
  }
}
