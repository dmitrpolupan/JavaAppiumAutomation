package lib.ui.IOS;
import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IOSMyListPageObject extends MyListPageObject
{
    static
    {
        MY_LIST_NOT_NOW_BUTTON = "xpath:test";
        FOLDER_BY_NAME_TPL = "xpath:test";
        ARTICLE_TITLE_TPL = "xpath:test";
    }

    public IOSMyListPageObject(AppiumDriver driver){
        super(driver);
    }
}
