package miu.edu.mpp.hdk.ui;

import javax.swing.JPanel;

public class CheckoutBookForm extends MainForm {

    private JPanel mainPanel;

    public CheckoutBookForm() {
        controller.setCheckoutBookForm(this);
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }
}
