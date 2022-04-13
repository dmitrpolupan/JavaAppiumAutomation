package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase
{
    @Test
    public void testCompareArticleTitle()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

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
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

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
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

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
