package storage.Pages;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import storage.DTO.Record;
import storage.Pages.Panels.RecordPanel;

import java.util.List;

public class IncomePage extends TablePage {

    @Override
    protected void onInitialize() {

        super.onInitialize();
        ListView<Record> recordsListView = new ListView<Record>(
                "list",
                new AbstractReadOnlyModel<List<Record>>() {
                    @Override
                    public List<Record> getObject() {
                        return recordDAO.getRecordsDate(from, to, "income_v");
                    }
                }
        ) {
            @Override
            protected void populateItem(ListItem<Record> listItem) {
                listItem.add(new RecordPanel("record", listItem.getModel()));
            }
        };
        add(recordsPaneContainer = new WebMarkupContainer("recordsPaneContainer"));
        recordsPaneContainer.setOutputMarkupId(true);
        recordsPaneContainer.add(recordsListView);
    }
}
