package miu.edu.mpp.hdk.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PrintCheckoutForm extends IForm {

    private JPanel detailPanel;
    private JTextArea areTitle;

    public PrintCheckoutForm() {
        controller.setPrintCheckoutForm(this);
    }

    public JPanel getContent() {
        return detailPanel;
    }

    public void setTitleTxtArea(String text) {
        this.areTitle.setText(text);
    }
}
