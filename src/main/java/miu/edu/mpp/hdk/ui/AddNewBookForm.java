package miu.edu.mpp.hdk.ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewBookForm extends MainForm {
    private JPanel mainPanel;
    private JTextField firstNameTxt;
    private JTextField lastNameTxt;
    private JTextField bookTitleTxt;
    private JButton addBookButton;

    public AddNewBookForm() {
        controller.setAddBookForm(this);
        addBookButton.addActionListener(e -> {
            String firstName = firstNameTxt.getText();
            String lastName = lastNameTxt.getText().trim();
            String bookTitle = bookTitleTxt.getText().trim();
            if (firstName.isBlank() || lastName.isBlank() || bookTitle.isBlank()) {
                controller.error("All fields must be nonempty");
                return;
            }
//            controller.addBookTitle(firstName,  lastName, bookTitle);
        });
    }

    public void cleanText() {
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        bookTitleTxt.setText("");
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

}
