package lib.ui.IOS;
import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath:test";
        SAVED_ICON = "xpath:test";
        ADD_TO_MY_LIST_OVERLAY = "xpath:test";
        MY_LIST_NAME_INPUT = "xpath:test";
        MY_LIST_OK_BUTTON = "xpath:test";
        MY_LIST_FOLDER_NAME = "xpath:test";
        FOOTER_ELEMENT = "xpath:test";
    }

    public IOSArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
