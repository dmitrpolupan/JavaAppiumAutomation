package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject
{
    private static final String
            MY_LIST_NOT_NOW_BUTTON = "xpath://*[@text = 'NOT NOW']",
            FOLDER_BY_NAME_TPL = "xpath://*[@text = '{FOLDER_NAME}']",
            ARTICLE_TITLE_TPL = "xpath://*[@text = '{ARTICLE_TITLE}']";

    public MyListPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public MyListPageObject clickNotNowButton()
    {
        this.waitForElementAndClick(MY_LIST_NOT_NOW_BUTTON,"Cannot find 'NOT NOW' button",5);
        return this;
    }

    public MyListPageObject openSavedArticles(String substring)
    {
        String folderNameXpath = getFolderNameXpath(substring);
        this.waitForElementAndClick(folderNameXpath,"Cannot find Saved list",5);
        this.waitForElementPresent(folderNameXpath,"Cannot find saved article",10);
        return this;
    }

    public MyListPageObject swipeByArticleToDelete(String articleTitle)
    {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleTitleXpath = getArticleTitleXpath(articleTitle);
        this.swipeElementToLeft(articleTitleXpath,"Cannot Swipe element");
        this.waitForArticleToDisappearByTitle(articleTitle);
        return this;
    }

    public MyListPageObject waitForArticleToAppearByTitle(String articleTitle)
    {
        String articleTitleXpath = getArticleTitleXpath(articleTitle);
        this.waitForElementPresent(articleTitleXpath,"Cannot delete saved article",10);
        return this;
    }

    public MyListPageObject waitForArticleToDisappearByTitle(String articleTitle)
    {
        String articleTitleXpath = getArticleTitleXpath(articleTitle);
        this.waitForElementNotPresent(articleTitleXpath, "Cannot delete saved article",10);
        return this;
    }

    public void openArticleByTitle(String title)
    {
        String atricleTitle = getArticleTitleXpath(title);
        this.waitForElementPresent(atricleTitle,"Cannot find saved article - " + title,10);
        this.waitForElementAndClick(atricleTitle,"Cannot find saved article - " + title,10);
    }

    /*TEMPLATE METHODS*/
    private static String getFolderNameXpath(String folderName)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folderName);
    }

    private static String getArticleTitleXpath(String articleTitle)
    {
        return ARTICLE_TITLE_TPL.replace("{ARTICLE_TITLE}", articleTitle);
    }
    /*TEMPLATE METHODS*/
}
