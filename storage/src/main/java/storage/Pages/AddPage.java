package storage.Pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.RangeValidator;
import storage.DAO.RecordDAO;
import storage.DTO.Record;

import java.util.Date;
import java.util.List;

/**
 * Created by nebular on 09.01.2017.
 */
public class AddPage extends BasePage {

    @SpringBean(name = "recordDAO")
    private RecordDAO recordDAO;

    private Form<Record> form;
    private Form delform;
    private FeedbackPanel feedbackPanel;
    private String selected_status;

    private Date date, outcomeDate = new Date();
    @Override
    protected void onInitialize() {

        super.onInitialize();
        form = new Form<>("newRecordForm", new CompoundPropertyModel<>(new Record()));
        form.setOutputMarkupId(true);
        add(form);

        delform = new Form<>("deleteForm");
        delform.setOutputMarkupId(true);
        add(delform);

        form.add(feedbackPanel = new FeedbackPanel("feedback"));
        feedbackPanel.setOutputMarkupId(true);

        form.add(new TextField<String>("name")
                .setRequired(true)
        );

        form.add(new TextField<>("size", Integer.class)
                .setRequired(true)
                .add(new RangeValidator<>(1, Integer.MAX_VALUE))
        );

        form.add(new TextField<>("price", Double.class)
                .setRequired(true)
        );

        DateTextField dateTextField = new DateTextField("date",
                new PropertyModel<Date>(this,"date"),
                new StyleDateConverter("S-", true));
        DatePicker datePicker = new DatePicker();
        datePicker.setShowOnFieldClick(true);
        datePicker.setAutoHide(true);
        dateTextField.add(datePicker);
        form.add(dateTextField);

        form.add(new AjaxSubmitLink("send") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                if (!form.hasError()) {
                    Record record = AddPage.this.form.getModelObject();
                    record.setDate(date);
                    recordDAO.postRecord(record, "products");
                    recordDAO.postRecord(record, "income");
                    AddPage.this.form.setModelObject(new Record());
                    target.add(form);
                    target.add(feedbackPanel);
                    setResponsePage(new AddPage());
                }
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(feedbackPanel);
            }
        });



        TextField existingSize = new TextField<Integer>("existingSize",new Model<Integer>(), Integer.class);
        existingSize.setRequired(true);
        existingSize.add(new RangeValidator<>(1, Integer.MAX_VALUE));
        existingSize.setOutputMarkupId(true);
        delform.add(existingSize);

        TextField existingPrice = new TextField<Double>("existingPrice", new Model<Double>(), Double.class);
        existingPrice.setRequired(true);
        existingPrice.setOutputMarkupId(true);
        delform.add(existingPrice);

        DateTextField outDateTextField = new DateTextField("outcome",
                new PropertyModel<Date>(this,"outcomeDate"),
                new StyleDateConverter("S-", true));
        DatePicker outDatePicker = new DatePicker();
        outDatePicker.setShowOnFieldClick(true);
        outDatePicker.setAutoHide(true);
        outDateTextField.add(outDatePicker);
        delform.add(outDateTextField);

        List<Record> records = recordDAO.getRecords();

        DropDownChoice names = new DropDownChoice("recordName",
                new PropertyModel<>(this,"selected_status"),
                records,
                new ChoiceRenderer( "name" ));
        names.add(new AjaxFormComponentUpdatingBehavior("onchange"){
                      @Override
                      protected void onUpdate(AjaxRequestTarget target) {
                          Record record = records.get(Integer.valueOf(names.getInput()));
                          existingSize.setModelObject(record.getSize());
                          existingPrice.setModelObject(record.getPrice());
                          target.add(existingSize);
                          target.add(existingPrice);
                      }
                  }
        );
        names.setNullValid(false);
        names.setRequired(true);
        delform.add(names);

        delform.add(new AjaxSubmitLink("senddel") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> delform) {
                if (!delform.hasError()) {
                    Record record = records.get(Integer.valueOf(names.getInput()));
                    if(record.getSize() - Integer.valueOf(existingSize.getInput()) <= 0) {
                        recordDAO.deleteRow(record.getId(), outcomeDate);
                    }
                    else
                    {
                        record.setSize(record.getSize() - Integer.valueOf(existingSize.getInput()));
                        recordDAO.updateRow(record, "products");
                        record.setDate(outcomeDate);
                        record.setSize(Integer.valueOf(existingSize.getInput()));
                        record.setPrice(Double.valueOf(existingPrice.getInput()));
                        recordDAO.postRecord(record, "outcome");
                    }
                    setResponsePage(new AddPage());
                }
            }
        });

    }
    public AddPage() {

    }
}
