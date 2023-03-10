package miu.edu.mpp.hdk.ui;

import javax.swing.JPanel;

public class CheckoutBookForm extends IForm {

    private JPanel checkoutPanel;

    public CheckoutBookForm() {
        controller.setCheckoutBookForm(this);
    }

    public JPanel getContent() {
        return checkoutPanel;
    }
}
