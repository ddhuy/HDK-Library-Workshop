package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DetailForm {

    private JPanel detailPanel;
    private JTextArea areTitle;

    public DetailForm() {
        SystemController controller = SystemController.INSTANCE;
        controller.setDetailForm(this);
    }

    public JPanel getContent() {
        return detailPanel;
    }

    public void setTitleTxtArea(String text) {
        this.areTitle.setText(text);
    }
}
