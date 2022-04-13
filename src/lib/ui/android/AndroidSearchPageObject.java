package lib.ui.android;
import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
    static
    {
        SKIP_BUTTON = "xpath://*[@text = 'SKIP']";
        SEARCH_INPUT_FIELD = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[contains(@class, 'TextView')]";
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]";
        SEARCH_RESULT_LOCATOR = "xpath://*[contains(@resource-id, 'search_results_list')]//*[contains(@class, 'ViewGroup')]";
        SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']";
        SEARCH_CANCEL_BUTTON = "id:search_close_btn";
        SEARCH_RESULT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = '{SUBSTRING}']";
        NO_RESULTS_LABEL = "xpath://*[@text = 'No results']";
        SEARCH_RESULT_ELEMENT = "xpath://*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]";
        SEARCH_RESULT_ARTICLES_NAMES = "xpath://*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]";
        SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "xpath://*[contains(@resource-id, 'page_list_item_title')][contains(@text, '{TITLE}')]//parent::*//*[contains(@resource-id, 'page_list_item_description')][contains(@text, '{DESCRIPTION}')]";
    }

    public AndroidSearchPageObject(AppiumDriver driver){
        super(driver);
    }
}
