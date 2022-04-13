package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.util.List;

public class SearchPageObject extends MainPageObject
{
    private static final String
                    SKIP_BUTTON = "xpath://*[@text = 'SKIP']",
                    SEARCH_INPUT_FIELD = "xpath://*[@resource-id='org.wikipedia:id/search_container']//*[contains(@class, 'TextView')]",
                    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
                    SEARCH_RESULT_LOCATOR = "xpath://*[contains(@resource-id, 'search_results_list')]//*[contains(@class, 'ViewGroup')]",
                    SEARCH_INPUT = "xpath://*[@resource-id='org.wikipedia:id/search_src_text']",
                    SEARCH_CANCEL_BUTTON = "id:search_close_btn",
                    SEARCH_RESULT = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = '{SUBSTRING}']",
                    NO_RESULTS_LABEL = "xpath://*[@text = 'No results']",
                    SEARCH_RESULT_ELEMENT = "xpath://*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]",
                    SEARCH_RESULT_ARTICLES_NAMES = "xpath://*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]",
                    SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "xpath://*[contains(@resource-id, 'page_list_item_title')][contains(@text, '{TITLE}')]//parent::*//*[contains(@resource-id, 'page_list_item_description')][contains(@text, '{DESCRIPTION}')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public SearchPageObject clickSkipButton()
    {
        this.waitForElementAndClick(SKIP_BUTTON, "Cannot find and click SKIP button", 5);
        return this;
    }

    public SearchPageObject initSearchInput()
    {
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
        return this;
    }

    public SearchPageObject typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and type search input", 5);
        return this;
    }

    public SearchPageObject waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannon find search cancel button", 5);
        return this;
    }

    public SearchPageObject waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);
        return this;
    }

    public SearchPageObject clickCancelButton()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click search cancel button", 5);
        return this;
    }

    public void waitForSearchResult(String substring)
    {
        String searchResultsXpath = getResultSearchElement(substring);
        this.waitForElementPresent(searchResultsXpath, "Cannot find search result with substring", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String searchResultsXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultsXpath, "Cannot find and click search result with substring", 5);
    }

    public int getAmountOfFoundArticles()
    {
        List<String> listOfArticlesName = this.getArticlesName(SEARCH_RESULT_ELEMENT,"Cannot find anything by the request - ",10);
        return listOfArticlesName.size();
    }

    public SearchPageObject waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(NO_RESULTS_LABEL,"Cannot find empty result label by request", 10);
        return this;
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(SEARCH_RESULT_ELEMENT,"We found some results by request");
    }

    public void asserElementHasText(String expectedText)
    {
        this.assertElementHasText(SEARCH_INPUT_FIELD, expectedText,"Search field doesn't have expected text");
    }

    public int getSearchResultCount()
    {
        this.waitForElementPresent(SEARCH_RESULT_LOCATOR,"Cannot find result list",5);
        return this.getCountOfWebElements(SEARCH_RESULT_LOCATOR,"Cannot find search results", 5);
    }

    public void waitCloseButtonIsDisappeared()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON,"X is still present on the page",5);
    }

    public boolean isSearchResultDisplayed()
    {
        return this.isElementDisplayed(SEARCH_RESULT_LOCATOR);
    }

    public boolean isResultItemsContainsSearchValue(String searchValue)
    {
        List<String> listOfArticlesName = this.getArticlesName(SEARCH_RESULT_ARTICLES_NAMES,"Cannot find articles name",5);
        return listOfArticlesName.stream().allMatch(element -> element.contains(searchValue));
    }

    public SearchPageObject waitForElementByTitleAndDescription(String title, String description)
    {
        String resultItemWithTitleAndDescriptionXpath = getResultSearchWithTitleAndDescription(title, description);
        this.waitForElementPresent(
                resultItemWithTitleAndDescriptionXpath,
                "Cannot find search result with title + '" + title + "' and description '" + description +"'",
                5);
        return this;
    }

    /*TEMPLATE METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    }

    private static String getResultSearchWithTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /*TEMPLATE METHODS*/
}
