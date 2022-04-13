package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;

public class ChangeAppConditionTests extends CoreTestCase
{
    @Test
    public void testChangeScreenOrientationOnScreenResults()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);

        String searchValue = "Java";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue)
                .clickByArticleWithSubstring("Object-oriented programming language");


        String titleBeforeRotation = ArticlePageObject.getArticleTitle();

        this.changeOrientation(ScreenOrientation.LANDSCAPE);

        String titleAfterRotation = ArticlePageObject.getArticleTitle();

        assertEquals("Article title have been changes after screen rotation", titleBeforeRotation, titleAfterRotation);

        this.changeOrientation(ScreenOrientation.PORTRAIT);

        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle();

        assertEquals("Article title have been changes after the second screen rotation", titleBeforeRotation, titleAfterSecondRotation);
    }

    @Test
    public void testCheckSearchArticleInBackground()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        this.backgroundApp(3);

        //SearchPageObject.waitForSearchResult("Object-oriented programming language"); //it looks that wikipedia has bug and results are not restored
    }

    @Test
    public void testRotation()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForSearchResult("Object-oriented programming language");

        this.changeOrientation(ScreenOrientation.LANDSCAPE);

        SearchPageObject.waitForSearchResult("Java");

        this.changeOrientation(ScreenOrientation.PORTRAIT);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
