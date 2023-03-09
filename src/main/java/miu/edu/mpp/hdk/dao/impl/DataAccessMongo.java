package miu.edu.mpp.hdk.dao.impl;

import com.mongodb.MongoCredential;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.enums.StorageType;
import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.model.User;
import org.bson.Document;

import java.util.HashMap;

public class DataAccessMongo implements DataAccess {

    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DB_NAME = "library-workshop";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";

    static final DataAccessMongo INSTANCE = new DataAccessMongo();

//    private final MongoDatabase database;

    private DataAccessMongo() {
        // Creating a Mongo client
        MongoClient mongo = new MongoClient(HOST, PORT);

        // Creating Credentials
        MongoCredential credential = MongoCredential.createCredential(USER, DB_NAME, PASSWORD.toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase(DB_NAME);
        System.out.println("Credentials ::"+ credential);

        for (StorageType type : StorageType.values()) {
            // Retrieving a collection
            MongoCollection<Document> collection = database.getCollection(type.name());
            // Dropping a Collection
            collection.drop();
            // Creating a collection
            database.createCollection(type.name());
            System.out.println(String.format("Collection %s created successfully", type.name()));
        }
        for (String name : database.listCollectionNames()) {
            System.out.println(name);
        }

    }

    public void initData(){

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
