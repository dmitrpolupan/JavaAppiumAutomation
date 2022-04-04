package tests;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase
{
    @Test
    public void testSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Java")
                .waitForCancelButtonToAppear()
                .clickCancelButton()
                .waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        String searchValue = "Linkin Park Diskography";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue);

        int amountOfFoundArticles = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("We didn't find results", amountOfFoundArticles > 0);
    }

    @Test
    public void testAmountOfEmptySearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        String searchValue = "tefgnfgfst";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue)
                .waitForEmptyResultsLabel()
                .assertThereIsNoResultOfSearch();
    }

    @Test
    public void testElementHasText()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .asserElementHasText("Search Wikipedia");
    }

    @Test
    public void testCancelSearchResults()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine("Selenium");

        int countOfArticles = SearchPageObject.getSearchResultCount();

        assertTrue("Search results is not displayed after search", countOfArticles > 0);

        SearchPageObject
                .clickCancelButton()
                .waitCloseButtonIsDisappeared();

        boolean isResultDisplayed = SearchPageObject.isSearchResultDisplayed();

        assertFalse("Search result is still displayed after clear search field", isResultDisplayed);
    }

    @Test
    public void testEachItemContainsSearchValue()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        String searchValue = "Java";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(searchValue);

        boolean isAllNamesContainSearchValue = SearchPageObject.isResultItemsContainsSearchValue(searchValue);

        assertTrue("Not all result line contains '" + searchValue + "' search value ", isAllNamesContainSearchValue);
    }

    @Test
    public void testResultItemsContainsSpecifiedTitlesAndDescriptions()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(_driver);

        String query = "java";
        String title1 = "Java";
        String description1 = "Island in Southeast Asia";
        String title2 = "JavaScript";
        String description2 = "High-level programming language";
        String title3 = "Java (programming language)";
        String description3 = "Object-oriented programming language";

        SearchPageObject
                .clickSkipButton()
                .initSearchInput()
                .typeSearchLine(query)
                .waitForElementByTitleAndDescription(title1, description1)
                .waitForElementByTitleAndDescription(title2, description2)
                .waitForElementByTitleAndDescription(title3, description3);
    }
}
