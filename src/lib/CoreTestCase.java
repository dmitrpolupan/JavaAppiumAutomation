package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;

import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import sun.security.krb5.internal.crypto.Des;

import java.net.URL;

public class CoreTestCase extends TestCase {
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_IOS = "ios";

    protected AppiumDriver _driver;
    protected boolean _isPortraitDefaultState = true;
    private static String _appiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
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

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception
    {
        String platform = System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("appActivity", ".main.MainActivity");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");
        } else if (platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "");
        } else {
            throw new Exception("Cannot get run platform from env env variable. Platform value - " + platform);
        }

        return capabilities;
    }
}
