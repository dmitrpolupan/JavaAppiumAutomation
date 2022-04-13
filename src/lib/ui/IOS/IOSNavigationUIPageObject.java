package lib.ui.IOS;
import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUIPageObject;

public class IOSNavigationUIPageObject extends NavigationUIPageObject
{
    static
    {
        MY_SAVED_ARTICLES_LINK = "xpath:test";
        BACK_BUTTON = "xpath:test";
    }

    public IOSNavigationUIPageObject(AppiumDriver driver){
        super(driver);
    }
}
