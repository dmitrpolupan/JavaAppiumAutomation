package lib.ui.android;
import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUIPageObject;

public class AndroidNavigationUIPageObject extends NavigationUIPageObject
{
    static
    {
        MY_SAVED_ARTICLES_LINK = "xpath://android.widget.FrameLayout[@content-desc=\"Saved\"]/android.widget.ImageView";
        BACK_BUTTON = "xpath://*[contains(@class, 'ImageButton')]";
    }

    public AndroidNavigationUIPageObject(AppiumDriver driver){
        super(driver);
    }
}
