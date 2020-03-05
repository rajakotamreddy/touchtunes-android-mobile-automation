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

- Run this on Android emulator with Android version 8
- Please upload app in: /src/main/resources/data/ (if app name is changed update new name in config.properties file)
- This project is capable of opening appium server from test page.
- location is setted using "driver.setlocation()" method. location setted to montreal (45.5017 ,-73.5673).
- Valdiation is limted to 4 items in the list (this can be done dynamically with test data using Horizontal and vertical swipe).
- Reports(with screen shots) and logs added in the project, can be found under reports package
