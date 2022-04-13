package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUIPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIPageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);
        NavigationUIPageObject NavigationUI = NavigationUIPageObjectFactory.get(_driver);
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .clickByArticleWithSubstring("Object-oriented programming language");

        String articleName = "Java (programming language)";
        String savedName = "Learning article";

        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.addArticleToMyList(savedName);

        NavigationUI
                .clickBackButton()
                .clickBackButton()
                .clickMySavedArticles();

        MyListPageObject
                .clickNotNowButton()
                .openSavedArticles(savedName)
                .swipeByArticleToDelete(articleName);
    }

    @Test
    public void testSavingTwoArticles()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(_driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(_driver);
        NavigationUIPageObject NavigationUI = NavigationUIPageObjectFactory.get(_driver);
        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(_driver);

        String searchValue = "Java";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue);

        String articleName1 = "JavaScript";
        String articleName2 = "Java (programming language)";
        String folderForSaving = "Learning articles folder";

        SearchPageObject.clickByArticleWithSubstring(articleName1);

        ArticlePageObject.waitForTitleElement();

        ArticlePageObject.addArticleToMyList(folderForSaving);

        NavigationUI.clickBackButton();

        SearchPageObject.clickByArticleWithSubstring(articleName2);

        ArticlePageObject.waitForTitleElement();

        ArticlePageObject
                .clickSavedIconAndClickOverlay()
                .moveToSpecifiedFolder(folderForSaving)
                .moveToListWithSavedArticles();

        MyListPageObject
                .swipeByArticleToDelete(articleName2)
                .openArticleByTitle(articleName1);

        String actualTitleName = ArticlePageObject.getArticleTitle();

        assertEquals("Actual article name is not equal to expected one", articleName1, actualTitleName);
    }
}
