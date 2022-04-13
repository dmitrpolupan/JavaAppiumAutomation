package lib.ui;
import io.appium.java_client.AppiumDriver;

public class NavigationUIPageObject extends MainPageObject
{
    protected static String
        MY_SAVED_ARTICLES_LINK,
        BACK_BUTTON;

    public NavigationUIPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMySavedArticles()
    {
        this.waitForElementAndClick(MY_SAVED_ARTICLES_LINK,"Cannot find Saved button in the bottom menu",5);
    }

    public NavigationUIPageObject clickBackButton()
    {
        this.waitForElementAndClick(BACK_BUTTON,"Cannot find Back button in the first time",5);
        return this;
    }
}
