package lib.ui.factories;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.IOS.IOSNavigationUIPageObject;
import lib.ui.NavigationUIPageObject;
import lib.ui.android.AndroidNavigationUIPageObject;

public class NavigationUIPageObjectFactory
{
    public static NavigationUIPageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidNavigationUIPageObject(driver);
        } else {
            return new IOSNavigationUIPageObject(driver);
        }
    }
}
