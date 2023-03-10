package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.BookController;
import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JPanel;

public class AddBookCopyForm extends MainForm {
    private JPanel mainPanel;
    private final BookController bookController;

    public AddBookCopyForm(SystemController system) {
        super(system);
        bookController = new BookController();
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }
}
