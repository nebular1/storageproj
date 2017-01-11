package storage.Pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

/**
 * Created by nebular on 09.01.2017.
 */
public class BasePage extends WebPage {
    public BasePage() {
        add(new BookmarkablePageLink("addPage", AddPage.class));
        add(new BookmarkablePageLink("incomePage", IncomePage.class));
        add(new BookmarkablePageLink("outcomePage", OutcomePage.class));
        add(new BookmarkablePageLink("statusPage", StatusPage.class));
    }
}
