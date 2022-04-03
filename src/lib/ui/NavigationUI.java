package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject
{
    private static final String
        MY_SAVED_ARTICLES_LINK = "//android.widget.FrameLayout[@content-desc=\"Saved\"]/android.widget.ImageView",
        BACK_BUTTON = "//*[contains(@class, 'ImageButton')]";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMySavedArticles()
    {
        this.waitForElementAndClick(By.xpath(MY_SAVED_ARTICLES_LINK),"Cannot find Saved button in the bottom menu",5);
    }

    public NavigationUI clickBackButton()
    {
        this.waitForElementAndClick(By.xpath(BACK_BUTTON),"Cannot find Back button in the first time",5);
        return this;
    }
}
