package miu.edu.mpp.hdk.dao;

import miu.edu.mpp.hdk.model.Book;
import miu.edu.mpp.hdk.model.LibraryMember;
import miu.edu.mpp.hdk.model.User;

import java.util.HashMap;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
}
