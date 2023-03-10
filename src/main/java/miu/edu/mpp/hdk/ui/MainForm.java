package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;

public abstract class MainForm {

    protected final SystemController system;

    protected MainForm(SystemController system) {
        this.system = system;
    }

    public abstract JPanel getContent();

}
