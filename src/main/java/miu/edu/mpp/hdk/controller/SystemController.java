package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.LibraryWorkshopApplication;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.Auth;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {

    public static final SystemController INSTANCE = new SystemController();

    private LibraryWorkshopApplication application;
    public Auth currentAuth = Auth.ANONYMOUS;
    public User user = new User();
    private final DataAccess da = DataAccessFactory.createDataAccess(DataAccessType.MONGO);

    public void login(String username, String password) {
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(username)) {
            this.error("ID " + username + " not found");
            return;
        }
        String passwordFound = map.get(username).getPassword();
        if (!passwordFound.equals(password)) {
            this.error("Password incorrect");
            return;
        }
        this.user = map.get(username);
        this.currentAuth = map.get(username).getAuthorization();
        this.info("Login successful");
        this.application.auth(currentAuth);
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

//    public void refresh() {
//        if (currentAuth.equals(Auth.BOTH) || currentAuth.equals(Auth.LIBRARIAN)) {
//            StringJoiner joiner = new StringJoiner("\n");
//            HashMap<String, LibraryMember> members = da.readMemberMap();
//            members.values().forEach(m -> joiner.add(m.toString()));
//            printCheckoutForm.setTitleTxtArea(joiner.toString());
//        } else {
//            printCheckoutForm.setTitleTxtArea("");
//        }
//        addNewBookForm.cleanText();
//    }

    public void error(String msg) {
        this.application.error(msg);
    }

    public void info(String msg) {
        this.application.info(msg);
    }

    public void refresh() {
        this.application.refresh();
    }
}
