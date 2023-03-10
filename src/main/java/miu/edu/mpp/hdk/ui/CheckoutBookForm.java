package miu.edu.mpp.hdk.ui;

import miu.edu.mpp.hdk.controller.BookController;
import miu.edu.mpp.hdk.controller.MemberController;
import miu.edu.mpp.hdk.controller.SystemController;
import miu.edu.mpp.hdk.model.Book;
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
        List<LibraryMember> members = memberController.getListMember();
        members.forEach(comboMember::addItem);
        List<Book> books = bookController.getListBook();
        books.forEach(comboBook::addItem);
        btnCheckout.addActionListener(e -> {
            LibraryMember member = (LibraryMember) comboMember.getSelectedItem();
            Book book = (Book) comboBook.getSelectedItem();
            System.out.println(member);
            System.out.println(book);
//            if (firstName.isBlank() || lastName.isBlank() || bookTitle.isBlank()) {
//                system.error("All fields must be nonempty");
//                return;
//            }
//            controller.addBookTitle(firstName,  lastName, bookTitle);
        });
    }

    @Override
    public JPanel getContent() {
        return mainPanel;
    }
}
