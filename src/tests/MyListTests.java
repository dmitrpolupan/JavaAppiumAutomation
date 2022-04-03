package tests;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(_driver);
        NavigationUI NavigationUI = new NavigationUI(_driver);
        MyListPageObject MyListPageObject = new MyListPageObject(_driver);

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
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(_driver);
        NavigationUI NavigationUI = new NavigationUI(_driver);
        MyListPageObject MyListPageObject = new MyListPageObject(_driver);

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
