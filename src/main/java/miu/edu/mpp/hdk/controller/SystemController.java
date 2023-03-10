package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.LibraryWorkshopApplication;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.Auth;
import miu.edu.mpp.hdk.exception.LoginException;
import miu.edu.mpp.hdk.model.User;
import miu.edu.mpp.hdk.ui.AddBookForm;
import miu.edu.mpp.hdk.ui.DetailForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {

    public static final SystemController INSTANCE = new SystemController();

    private LibraryWorkshopApplication application;
    private DetailForm detailForm;
    private AddBookForm addBookForm;
    private User user = new User("", "", Auth.ANONYMOUS);

    public static Auth currentAuth = null;
    private final DataAccess da = DataAccessFactory.defaultDataAccess();

    public void login(String id, String password) throws LoginException {
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();

    }

    public void doLogin(String username, String password) {
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
        this.user = map.get(username);
        this.application.info("Login successful");
        this.application.auth(user.getAuthorization());
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

    public void setDetailForm(DetailForm detailForm) {
        this.detailForm = detailForm;
    }

    public void setAddBookForm(AddBookForm addBookForm) {
        this.addBookForm = addBookForm;
    }

    public User getUser() {
        return user;
    }

    public void refresh() {

    }

    public void error(String msg) {
        this.application.error(msg);
    }
}
