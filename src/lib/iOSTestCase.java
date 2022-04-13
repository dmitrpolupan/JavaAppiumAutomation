package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class iOSTestCase extends TestCase {

    protected AppiumDriver _driver;
    protected boolean _isPortraitDefaultState = true;
    private static String _appiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "");

        _driver = new IOSDriver(new URL(_appiumUrl), capabilities);

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
