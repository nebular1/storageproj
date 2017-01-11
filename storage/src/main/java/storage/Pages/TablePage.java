package storage.Pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.datetime.StyleDateConverter;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import storage.DAO.RecordDAO;
import storage.Pages.BasePage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TablePage extends BasePage {
    @SpringBean(name = "recordDAO")
    protected RecordDAO recordDAO;

    public Date fromDate = new Date();
    public Date toDate = new Date();
    public WebMarkupContainer recordsPaneContainer;
    public FeedbackPanel feedbackPanel;
    public String from,to;
    @Override
    protected void onInitialize() {

        super.onInitialize();

        Form form = new Form("results");
        form.setOutputMarkupId(true);
        add(form);

        form.add(feedbackPanel = new FeedbackPanel("feedback"));
        feedbackPanel.setOutputMarkupId(true);

        DateTextField fromDateTextField = new DateTextField("date1",
                new PropertyModel<Date>(this,"fromDate"),
                new StyleDateConverter("S-", true));
        DatePicker fromDatePicker = new DatePicker();
        fromDatePicker.setShowOnFieldClick(true);
        fromDatePicker.setAutoHide(true);
        fromDateTextField.add(fromDatePicker);
        form.add(fromDateTextField);

        DateTextField toDateTextField = new DateTextField("date2",
                new PropertyModel<Date>(this,"toDate"),
                new StyleDateConverter("S-", true));
        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setShowOnFieldClick(true);
        toDatePicker.setAutoHide(true);
        toDateTextField.add(toDatePicker);
        form.add(toDateTextField);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        from = formatter.format(fromDate);
        to = formatter.format(toDate);

        form.add(new AjaxSubmitLink("submitBtn") {
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                from = formatter.format(fromDate);
                to = formatter.format(toDate);

                target.add(feedbackPanel);
                target.add(recordsPaneContainer);
            }
        });
    }

}
