package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.BookController;
import miu.edu.mpp.hdk.controller.SystemController;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewBookForm extends MainForm {
    private JPanel mainPanel;
    private JTextField isbnTxt;
    private JTextField titleTxt;
    private JButton addBookBtn;

    private final BookController bookController;

    public AddNewBookForm(SystemController system) {
        super(system);
        bookController = new BookController();
        addBookBtn.addActionListener(e -> {
            String isbn = isbnTxt.getText();
            String title = titleTxt.getText().trim();
            if (isbn.isBlank() || title.isBlank()) {
                system.error("All fields must be nonempty");
            }
        });
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

    @Override
    public void refresh() {
        isbnTxt.setText("");
        titleTxt.setText("");

    }
}
