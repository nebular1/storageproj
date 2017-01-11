package storage.Pages.Panels;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import storage.DTO.Record;

/**
 * Created by nebular on 09.01.2017.
 */
public class RecordPanel extends Panel {
    public RecordPanel(String id, IModel<Record> model) {
        super(id);
        setDefaultModel(model instanceof CompoundPropertyModel ? model : new CompoundPropertyModel<>(model.getObject()));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Label("name"));
        add(new Label("size"));
        add(new Label("price"));
        add(new Label("date"));

        Record record = getRecord();
    }

    private Record getRecord() {
        return (Record) getDefaultModelObject();
    }
}
