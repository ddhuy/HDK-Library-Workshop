package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.AuthorController;
import miu.edu.mpp.hdk.controller.BookController;
import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.model.Author;
import miu.edu.mpp.hdk.model.Book;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddNewBookForm extends MainForm {
    private final BookController bookController = new BookController();
    private final AuthorController authorController = new AuthorController();

    private JPanel mainPanel;
    private JTextField txtBookIsbn;
    private JTextField txtBookTitle;
    private JButton btnAddBook;
    private JComboBox<Author> cbbAuthors;
    private JLabel lblErrorMsg;

    public AddNewBookForm(SystemController system) {
        super(system);

        refresh();

        btnAddBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String isbn = txtBookIsbn.getText().trim();
                String title = txtBookTitle.getText().trim();

                Author author = (Author) cbbAuthors.getSelectedItem();
                if (author == null)
                    return;
                List<Author> lsAuthors = new ArrayList<>();
                lsAuthors.add(author);

                if (isbn.isBlank() || title.isBlank()) {
                    lblErrorMsg.setText("All fields must be nonempty");
                    return;
                }

                // check ISBN existence
                if (bookController.checkIsbn(isbn)) {
                    lblErrorMsg.setText("Book with same ISBN is already in DB!");
                    return;
                }

                // request controller to create Book & its copy
                Book book = bookController.createBook(isbn, title, lsAuthors);
                if (bookController.saveBook(book)) {
                    lblErrorMsg.setText("Book is created successfully");
                    refresh();
                    system.refresh();
                } else {
                    lblErrorMsg.setText("Could not create book");
                }
            }
        });

        cbbAuthors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

    @Override
    public void refresh() {
        txtBookIsbn.setText("");
        txtBookTitle.setText("");

        cbbAuthors.removeAllItems();
        List<Author> authors = authorController.getListAuthors();
        authors.forEach(cbbAuthors::addItem);
    }
}
