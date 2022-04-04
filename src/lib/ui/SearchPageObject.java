package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import java.util.List;

public class SearchPageObject extends MainPageObject
{
    private static final String
                    SKIP_BUTTON = "//*[@text = 'SKIP']",
                    SEARCH_INPUT_FIELD = "//*[@resource-id='org.wikipedia:id/search_container']//*[contains(@class, 'TextView')]",
                    SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
                    SEARCH_RESULT_LOCATOR = "//*[contains(@resource-id, 'search_results_list')]//*[contains(@class, 'ViewGroup')]",
                    SEARCH_INPUT = "//*[@resource-id='org.wikipedia:id/search_src_text']",
                    SEARCH_CANCEL_BUTTON = "search_close_btn",
                    SEARCH_RESULT = "//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = '{SUBSTRING}']",
                    NO_RESULTS_LABEL = "//*[@text = 'No results']",
                    SEARCH_RESULT_ELEMENT = "//*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]",
                    SEARCH_RESULT_ARTICLES_NAMES = "//*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]",
                    SEARCH_RESULT_LOCATOR_FOR_TITLE_DESCRIPTION_TPL = "//*[contains(@resource-id, 'page_list_item_title')][contains(@text, '{TITLE}')]//parent::*//*[contains(@resource-id, 'page_list_item_description')][contains(@text, '{DESCRIPTION}')]";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public SearchPageObject clickSkipButton()
    {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON), "Cannot find and click SKIP button", 5);
        return this;
    }

    public SearchPageObject initSearchInput()
    {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        return this;
    }

    public SearchPageObject typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type search input", 5);
        return this;
    }

    public SearchPageObject waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannon find search cancel button", 5);
        return this;
    }

    public SearchPageObject waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
        return this;
    }

    public SearchPageObject clickCancelButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
        return this;
    }

    public void waitForSearchResult(String substring)
    {
        String searchResultsXpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultsXpath), "Cannot find search result with substring", 5);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String searchResultsXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultsXpath), "Cannot find and click search result with substring", 5);
    }

    public int getAmountOfFoundArticles()
    {
        List<String> listOfArticlesName = this.getArticlesName(By.xpath(SEARCH_RESULT_ELEMENT),"Cannot find anything by the request - ",10);
        return listOfArticlesName.size();
    }

    public SearchPageObject waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(NO_RESULTS_LABEL),"Cannot find empty result label by request", 10);
        return this;
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"We found some results by request");
    }

    public void asserElementHasText(String expectedText)
    {
        this.assertElementHasText(By.xpath(SEARCH_INPUT_FIELD), expectedText,"Search field doesn't have expected text");
    }

    public int getSearchResultCount()
    {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_LOCATOR),"Cannot find result list",5);
        return this.getCountOfWebElements(By.xpath(SEARCH_RESULT_LOCATOR),"Cannot find search results", 5);
    }

    public void waitCloseButtonIsDisappeared()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"X is still present on the page",5);
    }

    public boolean isSearchResultDisplayed()
    {
        return this.isElementDisplayed(By.xpath(SEARCH_RESULT_LOCATOR));
    }

    public boolean isResultItemsContainsSearchValue(String searchValue)
    {
        List<String> listOfArticlesName = this.getArticlesName(By.xpath(SEARCH_RESULT_ARTICLES_NAMES),"Cannot find articles name",5);
        return listOfArticlesName.stream().allMatch(element -> element.contains(searchValue));
    }

    public SearchPageObject waitForElementByTitleAndDescription(String title, String description)
    {
        String resultItemWithTitleAndDescriptionXpath = getResultSearchWithTitleAndDescription(title, description);
        this.waitForElementPresent(
                By.xpath(resultItemWithTitleAndDescriptionXpath),
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
