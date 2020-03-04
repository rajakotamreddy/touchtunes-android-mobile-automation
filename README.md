Appium:
Appium is one of the most popular test automation tool currently present in the market. Appium studio is open source that makes it even more desirable. It helps in improving both test execution and test development. It allows the testers to easily and quickly create a robust test.

Appium Features:
Appium has been adopted in the industry due to its wide range of features. they are,

Support Webdriver Protocol – Webdriver Protocol gives better control over web UI. Automation without interrupting with the JS running on the page. Appium still is backward Compatible with the JSON Wire Protocol.
Robust Test Execution – Appium can easily execute tests regardless of the device being locally available or being on a remote server. The tests can be monitored in real-time as well.
Multi-Platform Support – Appium can execute test cases across multiple platforms. As of now, Appium supports Android, iOS and Windows applications.
No need to Rebuild Application – Appium doesn’t reinstall the application being tested onto the system again and again. Neither does it need any access to the library or source code of the application.
Parallel Execution – Appium enables users to execute test automation scripts on multiple Android or iOS sessions.
Step by Step Instructions to Configure and run Mobile Automation Test cases using Appium:
Download Android studio:
You need to go to the below address, download Android Studio for Mac and do the followings as stated below.

https://developer.android.com/studio/

To install Android Studio on your Mac, proceed as follows:

- Launch the Android Studio DMG file.
- Drag and drop Android Studio into the Applications folder, then launch Android Studio.
- The Android Studio Setup Wizard guides you through the rest of the setup, which includes downloading Android SDK components that are required for the development.

Open Android Studio and configure Virtual device/Emulator:
Follow below link to create virtual devices in the emulator. Name of the emulator requires to be set to AndroidEmulator to work with the current framework. Ask which version of the Android API is currently in test before installing. 
https://developer.android.com/studio/run/managing-avds

Install Appium Doctor:
Appium Doctor checks most of the preconditions for appium to run successfully. In order to install it, run below command:

npm install -g appium-doctor


Note to run project:

- Please upload app in: /src/main/resources/data/
- This project is capable of opening emmulator and appium server from test page.
