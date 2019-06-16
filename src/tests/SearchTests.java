package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearchByWordAndCancelSearching() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        WebElement listOfResults = searchPageObject.waitForSearchResultsListElement();
        int size = listOfResults.findElements(By.xpath("//*[@class='android.view.ViewGroup']//*[@resource-id='org.wikipedia:id/page_list_item_title']")).size();

        assertTrue("The searching finds some articles", size > 0);
        searchPageObject.clickCancelSearch();
        boolean resultIsEmpty = searchPageObject.waitForSearchResultsListIsNotPresent();
        assertTrue("There are some results of empty searching", resultIsEmpty);
    }

}
