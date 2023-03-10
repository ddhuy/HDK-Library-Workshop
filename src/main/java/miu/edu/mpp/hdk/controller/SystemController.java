package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.LibraryWorkshopApplication;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.Auth;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.model.User;
import miu.edu.mpp.hdk.ui.AddBookForm;
import miu.edu.mpp.hdk.ui.CheckoutBookForm;
import miu.edu.mpp.hdk.ui.PrintCheckoutForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class SystemController implements ControllerInterface {

    public static final SystemController INSTANCE = new SystemController();

    private LibraryWorkshopApplication application;
    private CheckoutBookForm checkoutBookForm;
    private AddBookForm addBookForm;
    private PrintCheckoutForm printCheckoutForm;
    public Auth currentAuth = Auth.BOTH;

    private final DataAccess da = DataAccessFactory.createDataAccess(DataAccessType.MONGO);

    public void login(String username, String password) {
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(username)) {
            this.application.error("ID " + username + " not found");
            return;
        }
        String passwordFound = map.get(username).getPassword();
        if (!passwordFound.equals(password)) {
            this.application.error("Password incorrect");
            return;
        }
        this.currentAuth = map.get(username).getAuthorization();
        this.application.info("Login successful");
        this.application.auth(currentAuth);
        this.refresh();
    }

    @Override
    public List<String> allMemberIds() {
        return new ArrayList<>(da.readMemberMap().keySet());
    }

    @Override
    public List<String> allBookIds() {
        return new ArrayList<>(da.readBooksMap().keySet());
    }


    public void setApplication(LibraryWorkshopApplication application) {
        this.application = application;
    }

    public void setAddBookForm(AddBookForm addBookForm) {
        this.addBookForm = addBookForm;
    }

    public void setCheckoutBookForm(CheckoutBookForm checkoutBookForm) {
        this.checkoutBookForm = checkoutBookForm;
    }

    public void setPrintCheckoutForm(PrintCheckoutForm printCheckoutForm) {
        this.printCheckoutForm = printCheckoutForm;
    }

    public void refresh() {
        if (currentAuth.equals(Auth.BOTH) || currentAuth.equals(Auth.LIBRARIAN)) {
            StringJoiner joiner = new StringJoiner("\n");
            HashMap<String, LibraryMember> members = da.readMemberMap();
            members.values().forEach(m -> joiner.add(m.toString()));
            printCheckoutForm.setTitleTxtArea(joiner.toString());
        } else {
            printCheckoutForm.setTitleTxtArea("");
        }
        addBookForm.cleanText();
    }

    public void error(String msg) {
        this.application.error(msg);
    }
}
