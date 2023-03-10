package miu.edu.mpp.hdk.dao;

import miu.edu.mpp.hdk.enums.StorageType;
import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.model.User;

import java.util.HashMap;

public interface DataAccess { 
	HashMap<String, Book> readBooksMap();
	HashMap<String, User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	void saveToStorage(StorageType type, Object ob);
	void updateToStorage(StorageType type, Object ob);
}
