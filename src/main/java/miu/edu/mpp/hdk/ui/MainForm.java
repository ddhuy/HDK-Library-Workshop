package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;

public abstract class MainForm {

    protected final SystemController controller = SystemController.INSTANCE;

    public abstract JPanel getContent();

}
