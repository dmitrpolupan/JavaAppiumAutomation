package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");

        String articleTitle = ArticlePageObject.getArticleTitle();

        assertEquals("Article header is not equal", "Java (programming language)", articleTitle);
    }

    @Test
    public void testSwipeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Appium")
                .clickByArticleWithSubstring("Appium");

        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.swipeToFooter();
    }

    @Test
    public void testCheckTitleElementExist()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Java (programming language)");

        //for testing
        try
        {
            Thread.sleep(1500);
        }
        catch(InterruptedException e)
        {
        }

        boolean isArticleTitlePresent = ArticlePageObject.isArticleTitlePresent();

        assertTrue("Article title has not been displayed at the moment of checking", isArticleTitlePresent);
    }
}
