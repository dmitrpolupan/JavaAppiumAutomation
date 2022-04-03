package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
            TITLE = "//*[@resource-id='pcs']/android.view.View[1]/android.view.View[1]",
            SAVED_ICON = "article_menu_bookmark",
            ADD_TO_MY_LIST_OVERLAY = "snackbar_action",
            MY_LIST_NAME_INPUT = "text_input",
            MY_LIST_OK_BUTTON = "//*[@text = 'OK']",
            MY_LIST_FOLDER_NAME = "//*[@resource-id='org.wikipedia:id/lists_container']//*[@text='{FOLDER_NAME}']",
            FOOTER_ELEMENT = "//*[@text = 'View article in browser']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE), "Cannot find article title on page", 5);
    }

    public String getArticleTitle()
    {
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT), "Cannot find the end of article", 20);
    }

    public ArticlePageObject clickSavedIconAndClickOverlay()
    {
        this.waitForElementAndClick(By.id(SAVED_ICON),"Cannot find button to click button for saving article",5);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),"Cannot find link to init saving article process",5);
        return this;
    }

    public void addArticleToMyList(String savedName)
    {
        this.clickSavedIconAndClickOverlay();
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT), savedName,"Cannot find input field for entering text for article",5);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON),"Cannot find Ok button to save article",5);
    }

    public ArticlePageObject moveToSpecifiedFolder(String folderName)
    {
        String folderNameFromListXpath = getMyListFolderNameXpath(folderName);
        this.waitForElementAndClick(By.xpath(folderNameFromListXpath),"Cannot find link to init saving article process",5);
        return this;
    }

    public ArticlePageObject moveToListWithSavedArticles()
    {
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY),"Cannot find link to init saving article process",5);
        return this;
    }

    public boolean isArticleTitlePresent()
    {
        return this.isElementDisplayed(By.xpath(TITLE));
    }

    /*TEMPLATE METHODS*/
    private static String getTitleElement(String substring)
    {
        return TITLE.replace("{SUBSTRING}", substring);
    }

    private static String getMyListFolderNameXpath(String folderName)
    {
        return MY_LIST_FOLDER_NAME.replace("{FOLDER_NAME}", folderName);
    }
    /*TEMPLATE METHODS*/
}
