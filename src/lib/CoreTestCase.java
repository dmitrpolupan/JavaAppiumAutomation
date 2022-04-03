package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    protected AppiumDriver _driver;
    protected boolean _isPortraitDefaultState = true;
    private static String _appiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        _driver = new AndroidDriver(new URL(_appiumUrl), capabilities);

        setupOrientation(_isPortraitDefaultState);
    }

    @Override
    protected void tearDown() throws Exception
    {
        _driver.quit();

        super.tearDown();
    }

    protected void changeOrientation(ScreenOrientation orientation)
    {
        _driver.rotate(orientation);
    }

    protected void backgroundApp(int seconds)
    {
        _driver.runAppInBackground(seconds);
    }

    private void setupOrientation(boolean isPortrait)
    {
        if(isPortrait)
        {
            this.changeOrientation(ScreenOrientation.PORTRAIT);
        }
        else
        {
            this.changeOrientation(ScreenOrientation.LANDSCAPE);
        }
    }
}
