package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;

public abstract class IForm {

    protected final SystemController controller = SystemController.INSTANCE;

    abstract JPanel getContent();


}
