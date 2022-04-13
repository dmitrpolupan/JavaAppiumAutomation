package lib.ui.android;
import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject
{
    static
    {
        MY_LIST_NOT_NOW_BUTTON = "xpath://*[@text = 'NOT NOW']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']";
        ARTICLE_TITLE_TPL = "xpath://*[@text = '{ARTICLE_TITLE}']";
    }

    public AndroidMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
