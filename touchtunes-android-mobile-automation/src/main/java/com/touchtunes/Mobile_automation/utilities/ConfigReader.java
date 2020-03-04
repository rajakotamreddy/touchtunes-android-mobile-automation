package com.touchtunes.Mobile_automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
  private static String startAndroidEmulator;
  private static String androidEmulatorName;
  private static String androidAppLocation;

  /**
   * Class constructor loads settings from the file and saves to fields
   */
  public ConfigReader() {
    FileInputStream fis;
    Properties p = new Properties();
    try {
      String configPath = System.getenv("CONFIG_FILE_PATH");
      if (configPath == null) {
        configPath = "src/main/resources/config.properties";
      }
      fis = new FileInputStream(configPath);
      p.load(fis);
    } catch (IOException e) {
      e.printStackTrace();
      System.out.println("Cant't read config.properties file!");
      return;
    }

    startAndroidEmulator = p.getProperty("START_ANDROID_EMULATOR");
    androidEmulatorName = p.getProperty("ANDROID_EMULATOR_NAME");
    androidAppLocation = p.getProperty("ANDROID_APP_LOCATION");
  }

  public static String getStartAndroidEmulator() {
    return startAndroidEmulator;
  }

  public static String getAndroidEmulatorName() {
    return androidEmulatorName;
  }

  public static String getAndroidAppLocation() {
    return androidAppLocation;
  }
}
