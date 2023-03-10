package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;

public class UpdateExistMemberForm extends MainForm {
    private JPanel mainPanel;

    public UpdateExistMemberForm(SystemController system) {
        super(system);
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

    @Override
    public void refresh() {

    }
}
