package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }
    @Test
    public void testSaveTwoArticlesToMyList()
    {
        MainPageObject MainPageObject = new MainPageObject(driver);
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        String search_word = "Java";
        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_word,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' article",
                5
        );

        String page_title_id = "org.wikipedia:id/view_page_title_text";
        MainPageObject.waitForElementPresent(
                By.id(page_title_id),
                "Cannot find article title",
                15
        );

        String options_xpath = "//android.widget.ImageView[@content-desc='More options']";
        MainPageObject.waitForElementAndClick(
                By.xpath(options_xpath),
                "Cannot find button to open article options",
                5
        );

        String add_to_list_xpath = "//*[@text='Add to reading list']";
        MainPageObject.waitForElementAndClick(
                By.xpath(add_to_list_xpath),
                "Cannot find option to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );

        MainPageObject.waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_word,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='JavaScript']"),
                "Cannot find 'JavaScript' article",
                5
        );

        String article_title = MainPageObject.waitForElementAndGetAttribute(
                By.id(page_title_id),
                "text",
                "Cannot find 'JavaScript' article title",
                30
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(options_xpath),
                "Cannot find button to open 'JavaScript' article options",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath(add_to_list_xpath),
                "Cannot find option to add article to reading list",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' folder",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X link",
                5
        );


        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My lists",
                5
        );

        MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/reading_list_list"),
                "Cannot find article folders list",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "'Java (programming language)' article is not found in the list",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@text='JavaScript']"),
                "'JavaScript' article is not found in the list",
                15
        );

        int articles_amount = MainPageObject.getAmountOfElements(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']")
        );

        assertEquals(
                "The number of articles is not as expected: " + articles_amount,
                2,
                articles_amount
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot delete saved article",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='JavaScript']"),
                "'JavaScript' article is not found in the list",
                30
        );

        String article_title_opened_from_list = MainPageObject.waitForElementAndGetAttribute(
                By.id(page_title_id),
                "text",
                "Cannot find 'JavaScript' article title",
                30
        );

        assertEquals("Article titles do not match",
                article_title,
                article_title_opened_from_list);
    }
}
