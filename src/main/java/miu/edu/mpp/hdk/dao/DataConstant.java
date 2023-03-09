package miu.edu.mpp.hdk.dao;

import miu.edu.mpp.hdk.enums.Auth;
import miu.edu.mpp.hdk.model.Address;
import miu.edu.mpp.hdk.model.User;

import java.util.ArrayList;
import java.util.List;

public class DataConstant {

    public static final List<User> allUsers = new ArrayList<>() {
        {
            add(new User("101", "xyz", Auth.LIBRARIAN));
            add(new User("102", "abc", Auth.ADMIN));
            add(new User("103", "111", Auth.BOTH));
        }
    };

    public static final List<Address> addresses = new ArrayList<>() {
        {
            add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
            add(new Address("51 S. George", "Georgetown", "MI", "65434"));
            add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
            add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
            add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
            add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
            add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
            add(new Address("501 Central", "Mountain View", "CA", "94707"));
        }
    };

}
