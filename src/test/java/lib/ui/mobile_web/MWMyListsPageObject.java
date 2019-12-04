package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}]";
        REMOVE_FROM_SAVED_BUTTON ="xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}]/../../div[contains(@class, 'watched')]";
        MY_LISTS = "id:org.wikipedia:id/reading_list_list";
        MY_LIST_ARTICLE_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
