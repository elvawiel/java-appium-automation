package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        MY_LISTS,
        MY_LIST_ARTICLE_ELEMENT,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXPathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXPathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void waitForMyListsToAppear()
    {
        this.waitForElementPresent(
                MY_LISTS,
                "Cannot find article folders list",
                15
        );
    }

    public void openFolderByName(String name_of_folder)
    {
        this.waitForMyListsToAppear();
        String folder_name_xpath = getFolderXPathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXPathByTitle(article_title);
        this.waitForElementPresent(
                article_xpath,
                "Cannot find saved article by title " + article_title,
                15
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXPathByTitle(article_title);
        this.waitForElementNotPresent(
                article_xpath,
                "Saved article is still present with title " + article_title,
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXPathByTitle(article_title);
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isIOS()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(remove_locator, "Cannot click button to remove article from saved list", 10);
        }
        if(Platform.getInstance().isMw()) {
            driver.navigate().refresh();
        }

        if(Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleToDisappearByTitle(article_title);
    }



    public int getAmountOfSavedArticles()
    {
        return this.getAmountOfElements(MY_LIST_ARTICLE_ELEMENT);
    }

    public void openArticleFromMyList(String article_title)
    {
        String article_xpath = getSavedArticleXPathByTitle(article_title);
        this.waitForElementAndClick(
                article_xpath,
                "Cannot find and click article by title " + article_title,
                15
        );
    }
}
