package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.enums.Auth;
import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFacade;
import miu.edu.mpp.hdk.model.User;
import miu.edu.mpp.hdk.exception.LoginException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		return new ArrayList<>(da.readMemberMap().keySet());
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		return new ArrayList<>(da.readBooksMap().keySet());
	}

}
