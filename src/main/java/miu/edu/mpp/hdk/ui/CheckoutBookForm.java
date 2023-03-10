package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.BookController;
import miu.edu.mpp.hdk.controller.MemberController;
import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.BookCopy;
import miu.edu.mpp.hdk.model.CheckoutRecord;
import miu.edu.mpp.hdk.model.LibraryMember;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import java.util.List;

public class CheckoutBookForm extends MainForm {

    private JPanel mainPanel;
    private JComboBox<LibraryMember> comboMember;
    private JComboBox<Book> comboBook;
    private JButton btnCheckout;
    private final MemberController memberController;
    private final BookController bookController;

    public CheckoutBookForm(SystemController system) {
        super(system);
        memberController = new MemberController();
        bookController = new BookController();
        this.refresh();
        btnCheckout.addActionListener(e -> {
            LibraryMember member = (LibraryMember) comboMember.getSelectedItem();
            Book book = (Book) comboBook.getSelectedItem();
            if(book == null || !book.isAvailable()){
                system.error("Book is not available!");
            } else {
                List<BookCopy> copies = book.getCopies();
                for (BookCopy copy : copies){
                    if(copy.isAvailable()){
                        copy.setAvailable(false);
                        break;
                    }
                }
                book.setCopies(copies);
                memberController.checkout(new CheckoutRecord(member, book, system.user.getId()));
                bookController.updateBook(book);
                system.info("Checkout Book Successfully!");
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
        comboMember.removeAllItems();
        List<LibraryMember> members = memberController.getListMember();
        members.forEach(comboMember::addItem);

        comboBook.removeAllItems();
        List<Book> books = bookController.getListBook();
        books.forEach(comboBook::addItem);
    }
}
