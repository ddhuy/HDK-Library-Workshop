package miu.edu.mpp.hdk.dao.impl;

import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.model.User;
import miu.edu.mpp.hdk.enums.StorageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class DataAccessFacade implements DataAccess {

    private static final String OUTPUT_DIR = System.getProperty("user.dir")
            + "/src/main/java/miu/edu/mpp/hdk/dataaccess/storage"; //for Unix file system
    //			+ "\\src\\dataaccess\\storage"; //for Windows file system
    private static final String DATE_PATTERN = "MM/dd/yyyy";

    //implement: other save operations
    public void saveNewMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        String memberId = member.getMemberId();
        mems.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, mems);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        //Returns a Map with name/value pairs being
        //   isbn -> Book
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        return (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
    }


    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        return (HashMap<String, User>) readFromStorage(StorageType.USERS);
    }


    /////load methods - these place test data into the storage area
    ///// - used just once at startup


    public void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    public void loadUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    public void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    public void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }


    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        public boolean equals(Object ob) {
            if (ob == null) return false;
            if (this == ob) return true;
            if (ob.getClass() != getClass()) return false;
            @SuppressWarnings("unchecked")
            Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        @Serial
        private static final long serialVersionUID = 5399827794066637059L;
    }

}