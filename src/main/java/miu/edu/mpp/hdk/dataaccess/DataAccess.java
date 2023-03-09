package miu.edu.mpp.hdk.dataaccess;

import miu.edu.mpp.hdk.business.Book;
import miu.edu.mpp.hdk.business.LibraryMember;

import java.util.HashMap;

public interface DataAccess { 
	public HashMap<String, Book> readBooksMap();
	public HashMap<String, User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member); 
}
