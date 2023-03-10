package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.MemberController;
import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;

public class CheckoutBookForm extends MainForm {

    private JPanel mainPanel;
    private final MemberController memberController;

    public CheckoutBookForm(SystemController system) {
        super(system);
        memberController = new MemberController();
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }
}
