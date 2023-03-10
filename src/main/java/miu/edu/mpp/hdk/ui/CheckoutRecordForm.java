package miu.edu.mpp.hdk.ui;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CheckoutRecordForm extends MainForm {

    private JPanel mainPanel;
    private JTextArea areTitle;

    public CheckoutRecordForm() {
        controller.setPrintCheckoutForm(this);
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

    public void setTitleTxtArea(String text) {
        this.areTitle.setText(text);
    }
}
