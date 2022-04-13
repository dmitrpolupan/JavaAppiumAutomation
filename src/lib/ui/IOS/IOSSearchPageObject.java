package lib.ui.IOS;
import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class IOSSearchPageObject extends SearchPageObject
{
    static
    {
        SKIP_BUTTON = "xpath:test";
        SEARCH_INPUT_FIELD = "xpath:test";
        SEARCH_INIT_ELEMENT = "xpath:test";
        SEARCH_RESULT_LOCATOR = "xpath:test";
        SEARCH_INPUT = "xpath:test";
        SEARCH_CANCEL_BUTTON = "xpath:test";
        SEARCH_RESULT = "xpath:test";
        NO_RESULTS_LABEL = "xpath:test";
        SEARCH_RESULT_ELEMENT = "xpath:test";
        SEARCH_RESULT_ARTICLES_NAMES = "xpath:test";
        SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "xpath:test";
    }

    public IOSSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
