import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FirstTest
{
    private AppiumDriver _driver;
    private boolean _isPortraitDefaultState = true;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("app", "D:\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        _driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        setupOrientation(_isPortraitDefaultState);
    }

    @After
    public void tearDown()
    {
        _driver.quit();
    }

    @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot enter value to search input field",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Object-oriented programming language']"),
                "Search term is not found");
    }

    @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Test test test",
                "Cannot enter value to search input field",
                5);

        waitForElementAndClear(
                By.id("search_src_text"),
                "Cannot find search field",
                5);

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Java",
                "Cannot enter value to search input field",
                5);

        waitForElementAndClick(
                By.id("search_close_btn"),
                "Cannot find 'Cross' icon",
                5);

        waitForElementNotPresent(
                By.id("search_close_btn"),
                "X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot enter value to search input field",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article title",
                5
        );

        WebElement titleElement = waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs']//*[@text = 'Java (programming language)']"),
                "We see unexpected title",
                5
        );

        String articleTitle = titleElement.getAttribute("text");

        Assert.assertEquals("Article header is not equal", "Java (programming language)", articleTitle);
    }

    @Test
    public void testElementHasText()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[contains(@class, 'TextView')]"),
                "Search Wikipedia",
                "Search field doesn't have expected text"
        );
    }

    @Test
    public void testCancelSearchResults()
    {
        waitForElementAndClick(
                By.id("fragment_onboarding_skip_button"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.id("search_src_text"),
                "Selenium",
                "Cannot enter value to search input field",
                5);

        String searchResultsLocator = "//*[contains(@resource-id, 'search_results_list')]//*[contains(@class, 'ViewGroup')]";

        waitForElementPresent(
                By.xpath(searchResultsLocator),
                "Cannot find result list",
                5
        );

        int countOfArticles = getCountOfWebElements(
                By.xpath(searchResultsLocator),
                "Cannot find search results",
                5
        );

        Assert.assertTrue("Search results is not displayed after search", countOfArticles > 0);

        waitForElementAndClick(
                By.id("search_close_btn"),
                "Cannot find 'Cross' icon",
                5);

        waitForElementNotPresent(
                By.id("search_close_btn"),
                "X is still present on the page",
                5
        );

        boolean isResultDisplayed = isElementDisplayed(By.xpath("//*[contains(@resource-id, 'search_results_list')]//*[contains(@class, 'ViewGroup')]"));

        Assert.assertFalse("Search result is still displayed after clear search field", isResultDisplayed);
    }

    @Test
    public void testEachItemContainsSearchValue()
    {
        String searchValue = "Java";

        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        List<String> listOfArticlesName = getArticlesName(
                By.xpath("//*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]"),
                "Cannot find articles name",
                5
        );

        boolean isAllNamesContainSearchValue = listOfArticlesName.stream().allMatch(element -> element.contains(searchValue));

        Assert.assertTrue(
                "Not all result line contains '" + searchValue + "' search value ", isAllNamesContainSearchValue);
    }

    @Test
    public void testSwipeArticle()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Appium", "Cannot enter value to search input field",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Appium']"),
                "Cannot find article title",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs']//*[@text = 'Appium']"),
                "We see unexpected title",
                5
        );

        swipeUpToFindElement(
                By.xpath("//*[@text = 'View article in browser']"),
                "Cannot find the end of the article",
                20
        );
    }

    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java", "Cannot enter value to search input field",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article title",
                5
        );

        String articleName = "Java (programming language)";

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'pcs']//*[@text = '" + articleName + "']"),
                "We see unexpected title",
                5
        );

        waitForElementAndClick(
                By.id("article_menu_bookmark"),
                "Cannot find button to click button for saving article",
                5
        );

        waitForElementAndClick(
                By.id("snackbar_action"),
                "Cannot find link to init saving article process",
                5
        );

        String savedName = "Learning article";

        waitForElementAndSendKeys(
                By.id("text_input"),
                savedName,
                "Cannot find input field for entering text for article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot find Ok button to save article",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@class, 'ImageButton')]"),
                "Cannot find Back button in the first time",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@class, 'ImageButton')]"),
                "Cannot find Back button in the first time",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc=\"Saved\"]/android.widget.ImageView"),
                "Cannot find Saved button in the bottom menu",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text = '" + savedName + "']"),
                "Cannot find Saved list",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@text = '" + articleName + "']"),
                "Cannot find saved article",
                10
        );

        swipeElementToLeft(
                By.xpath("//*[@text = '" + articleName + "']"),
                "Cannot Swipe element"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text = '" + articleName + "']"),
                "Cannot delete saved article",
                10
        );
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        String searchValue = "Linkin Park Diskography";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        List<String> listOfArticlesName = getArticlesName(
                By.xpath("//*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]"),
                "Cannot find anything by the request - " + searchValue,
                10);

        Assert.assertTrue("We didn't find results", listOfArticlesName.size() > 0);
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        String searchValue = "tefgnfgfst";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        waitForElementPresent(
                By.xpath("//*[@text = 'No results']"),
                "Cannot find empty result label by request - " + searchValue,
                10
        );

        assertElementNotPresent(
                By.xpath("//*[contains(@resource-id, 'search_results_list')]//*[contains(@resource-id, 'page_list_item_title')]"),
                "We found some results by request + " + searchValue);
    }

    @Test
    public void testChangeScreenOrientationOnScreenResults()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        String searchValue = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by - " + searchValue,
                10
        );

        String titleBeforeRotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]"),
                "text",
                "Cannot find title article",
                5
        );

        _driver.rotate(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]"),
                "text",
                "Cannot find title article",
                5
        );

        Assert.assertEquals("Article title have been changes after screen rotation", titleBeforeRotation, titleAfterRotation);

        _driver.rotate(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]"),
                "text",
                "Cannot find title article",
                5
        );

        Assert.assertEquals("Article title have been changes after the second screen rotation", titleBeforeRotation, titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                "Java",
                "Cannot enter value to search input field",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article title",
                5
        );

        _driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Object-oriented programming language']"),
                "Cannot find article title after returning from background",
                5
        );
    }

    @Test
    public void testSavingTwoArticles()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        String searchValue = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        String articleName1 = "JavaScript";
        String articleName2 = "Java (programming language)";

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = '" + articleName1 + "']"),
                "Cannot find article title",
                5);

        waitForElementAndClick(
                By.id("article_menu_bookmark"),
                "Cannot find button to click button for saving article",
                5);

        waitForElementAndClick(
                By.id("snackbar_action"),
                "Cannot find link to init saving article process",
                5);

        String folderForSaving = "Learning articles folder";

        waitForElementAndSendKeys(
                By.id("text_input"),
                folderForSaving,
                "Cannot find input field for entering text for article",
                5);

        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "Cannot find Ok button to save article",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@class, 'ImageButton')]"),
                "Cannot find Back button in the first time",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = '" + articleName2 + "']"),
                "Cannot find article title",
                5);

        waitForElementAndClick(
                By.id("article_menu_bookmark"),
                "Cannot find button for saving article",
                5);

        waitForElementAndClick(
                By.id("snackbar_action"),
                "Cannot find link to init saving article process",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/lists_container']//*[@text='" + folderForSaving + "']"),
                "Cannot find link to init saving article process",
                5);

        waitForElementAndClick(
                By.id("snackbar_action"),
                "Cannot find link to init saving article process",
                5);

        waitForElementPresent(
                By.xpath("//*[@text = '" + articleName2 + "']"),
                "Cannot find saved article - " + articleName2,
                10);

        swipeElementToLeft(
                By.xpath("//*[@text = '" + articleName2 + "']"),
                "Cannot swipe article - " + articleName2);

        waitForElementPresent(
                By.xpath("//*[@text = '" + articleName1 + "']"),
                "Cannot find saved article - " + articleName1,
                10);

        waitForElementAndClick(
                By.xpath("//*[@text = '" + articleName1 + "']"),
                "Cannot find saved article - " + articleName1,
                10);

        String actualTitleName = waitForElementAndGetAttribute(
                By.xpath("//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]"),
                "text",
                "Cannot find title article",
                5);

        Assert.assertEquals("Actual article name is not equal to expected one", articleName1, actualTitleName);
    }

    @Test
    public void testCheckTitleElementExist()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        String searchValue = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        String articleName = "Java (programming language)";

        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = '" + articleName + "']"),
                "Cannot find article title",
                5);

        //for testing
        //try
        //{
        //    Thread.sleep(1500);
        //}
        //catch(InterruptedException e)
        //{
        //}

        boolean isArticleTitlePresent = isElementDisplayed(By.xpath("//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]"));

        Assert.assertTrue("Article title has not been displayed at the moment of checking", isArticleTitlePresent);
    }

    @Test
    public void testRotation()
    {
        waitForElementAndClick(
                By.xpath("//*[@text = 'SKIP']"),
                "Cannot find 'Skip' button",
                5);

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input field",
                5);

        String searchValue = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
                searchValue,
                "Cannot enter value to search input field",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Java']"),
                "Cannot find article title",
                5);

        _driver.rotate(ScreenOrientation.LANDSCAPE);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Java']"),
                "Cannot find article title",
                5);

        String noSuchId = "noSuchId";

        waitForElementPresent(
                By.id(noSuchId),
                "Cannot find element with such id + " + noSuchId,
                2);

        _driver.rotate(ScreenOrientation.PORTRAIT);

        waitForElementPresent(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@text = 'Java']"),
                "Cannot find article title",
                5);
    }

    private WebElement waitForElementPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String errorMessage)
    {
        return waitForElementPresent(by, errorMessage, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(_driver, timeoutInSeconds);
        wait.withMessage(errorMessage);
        return  wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String errorMessage, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void assertElementHasText(By by, String expectedText, String errorMessage)
    {
        WebElement element = waitForElementPresent(by, "Cannot find element for test", 5);
        String elementText = element.getAttribute("text");
        Assert.assertEquals(errorMessage, expectedText, elementText);
    }

    private int getCountOfWebElements(By by, String errorMessage, long timeoutInSeconds)
    {
        List<WebElement> list = _driver.findElements(by);

        return list.size();
    }

    private boolean isElementDisplayed(By by)
    {
        boolean flag = true;
        try
        {
            _driver.findElement(by);
        }
        catch(Exception e)
        {
            flag = false;
        }

        return flag;
    }

    private List<String> getArticlesName(By by, String errorMessage, long timeoutInSeconds)
    {
        waitForElementPresent(by, errorMessage, timeoutInSeconds);

        List<WebElement> list = _driver.findElements(by);

        List<String> namesList = new ArrayList<>();

        for(WebElement element : list)
        {
            namesList.add(element.getAttribute("text"));
        }

        return namesList;
    };

    private void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(_driver);
        Dimension size = _driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes)
    {
        int alreadySwipes = 0;

        while(_driver.findElements(by).size() == 0)
        {
            if(alreadySwipes > maxSwipes)
            {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwipes;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage)
    {
        WebElement element = waitForElementPresent(
                by,
                errorMessage,
                5
        );

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();
        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();
        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(_driver);
        action
                .press(rightX, middleY)
                .waitAction(300)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }

    private void assertElementNotPresent(By by, String errorMessage)
    {
        int amountOfElements = getCountOfWebElements(by, errorMessage, 5);
        if(amountOfElements > 0)
        {
            String defaultMessage = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMessage, long timeoutSeconds)
    {
        WebElement element = waitForElementPresent(by, errorMessage, timeoutSeconds);
        return element.getAttribute(attribute);
    }

    private void setupOrientation(boolean isPortrait)
    {
        if(isPortrait)
        {
            _driver.rotate(ScreenOrientation.PORTRAIT);
        }
        else
        {
            _driver.rotate(ScreenOrientation.LANDSCAPE);
        }
    }
}
