package miu.edu.mpp.hdk.dao.impl;

import com.mongodb.MongoClient;
import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.model.User;

import java.util.HashMap;

public class DataAccessMongoDB implements DataAccess {

    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    public static final DataAccessMongoDB INSTANCE = new DataAccessMongoDB();

    // Creating a Mongo client
    private MongoClient mongoClient;

    DataAccessMongoDB() {
        this.mongoClient = new MongoClient(HOST, PORT);
    }

    @Override
    public HashMap<String, Book> readBooksMap() {
        return null;
    }

    @Override
    public HashMap<String, User> readUserMap() {
        return null;
    }

    @Override
    public HashMap<String, LibraryMember> readMemberMap() {
        return null;
    }

    @Override
    public void saveNewMember(LibraryMember member) {

    }
}
