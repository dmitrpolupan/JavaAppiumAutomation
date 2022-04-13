package lib.ui.android;
import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject
{
    static
    {
        TITLE = "xpath://*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]";
        SAVED_ICON = "id:article_menu_bookmark";
        ADD_TO_MY_LIST_OVERLAY = "id:snackbar_action";
        MY_LIST_NAME_INPUT = "id:text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
        MY_LIST_FOLDER_NAME = "xpath://*[@resource-id='org.wikipedia:id/lists_container']//*[@text='{FOLDER_NAME}']";
        FOOTER_ELEMENT = "xpath://*[@text = 'View article in browser']";
    }

    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
