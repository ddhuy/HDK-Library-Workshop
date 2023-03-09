package miu.edu.mpp.hdk.dao;

import miu.edu.mpp.hdk.dao.impl.DataAccessFacade;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.Author;
import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;

import java.util.ArrayList;
import java.util.List;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the dataaccess.storage
 * folder.
 */
public class TestData {

    static DataAccessFacade facade = (DataAccessFacade) DataAccessFactory.createDataAccess(DataAccessType.FACADE);

    public static void main(String[] args) {
        TestData td = new TestData();
        td.bookData();
        td.libraryMemberData();
        td.userData();
        System.out.println(facade.readBooksMap());
        System.out.println(facade.readUserMap());
    }

    ///create books
    public void bookData() {
        allBooks.get(0).addCopy();
        allBooks.get(0).addCopy();
        allBooks.get(1).addCopy();
        allBooks.get(3).addCopy();
        allBooks.get(2).addCopy();
        allBooks.get(2).addCopy();
        facade.loadBookMap(allBooks);
    }

    public void userData() {
        facade.loadUserMap(DataConstant.allUsers);
    }

    //create library members
    public void libraryMemberData() {

        LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "641-223-2211", DataConstant.addresses.get(4));
        members.add(libraryMember);
        libraryMember = new LibraryMember("1002", "Drew", "Stevens", "702-998-2414", DataConstant.addresses.get(5));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "451-234-8811", DataConstant.addresses.get(6));
        members.add(libraryMember);

        libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "641-472-2871", DataConstant.addresses.get(7));
        members.add(libraryMember);

        facade.loadMemberMap(members);
    }

    ///////////// DATA //////////////
    List<LibraryMember> members = new ArrayList<>();

    List<Author> allAuthors = new ArrayList<>() {
        {
            add(new Author("Joe", "Thomas", "641-445-2123", DataConstant.addresses.get(0), "A happy man is he."));
            add(new Author("Sandra", "Thomas", "641-445-2123", DataConstant.addresses.get(0), "A happy wife is she."));
            add(new Author("Nirmal", "Pugh", "641-919-3223", DataConstant.addresses.get(1), "Thinker of thoughts."));
            add(new Author("Andrew", "Cleveland", "976-445-2232", DataConstant.addresses.get(2), "Author of childrens' books."));
            add(new Author("Sarah", "Connor", "123-422-2663", DataConstant.addresses.get(3), "Known for her clever style."));
        }
    };

    List<Book> allBooks = new ArrayList<>() {
        {
            add(new Book("23-11451", "The Big Fish", 21, List.of(allAuthors.get(0), allAuthors.get(1))));
            add(new Book("28-12331", "Antartica", 7, List.of(allAuthors.get(2))));
            add(new Book("99-22223", "Thinking Java", 21, List.of(allAuthors.get(3))));
            add(new Book("48-56882", "Jimmy's First Day of School", 7, List.of(allAuthors.get(4))));
        }
    };

}
