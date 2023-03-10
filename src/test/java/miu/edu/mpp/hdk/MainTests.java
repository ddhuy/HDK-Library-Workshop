package miu.edu.mpp.hdk;

import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.model.User;
import org.junit.Test;

import java.util.HashMap;

public class MainTests {

    @Test
    public void testConnectionDB(){
        DataAccess data = DataAccessFactory.createDataAccess(DataAccessType.MONGO);
        HashMap<String, User> users = data.readUserMap();
        users.values().forEach(System.out::println);
        HashMap<String, LibraryMember> members = data.readMemberMap();
        members.values().forEach(System.out::println);
        HashMap<String, Book> books = data.readBooksMap();
        books.values().forEach(System.out::println);
    }
}
