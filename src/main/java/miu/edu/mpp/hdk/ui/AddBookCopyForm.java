package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.BookController;
import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.model.Book;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.List;

public class AddBookCopyForm extends MainForm {
    private JPanel mainPanel;
    private JComboBox<Book> comboBook;
    private JButton btnCopy;
    private final BookController bookController;

    public AddBookCopyForm(SystemController system) {
        super(system);
        bookController = new BookController();
        refresh();
        btnCopy.addActionListener(e -> {
            Book book = (Book) comboBook.getSelectedItem();
            if(book == null){
                system.error("Book not found!");
            } else {
                book.addCopy();
                bookController.updateBook(book);
                system.info("Copy Book Successfully!");
                system.refresh();
            }
        });
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }

    @Override
    public void refresh() {
        comboBook.removeAllItems();
        List<Book> books = bookController.getListBook();
        books.forEach(comboBook::addItem);
    }
}
