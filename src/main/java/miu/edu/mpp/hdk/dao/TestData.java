package miu.edu.mpp.hdk.dao;

import miu.edu.mpp.hdk.dao.impl.DataAccessFacade;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.DataAccessType;

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
        facade.loadBookMap(DataConstant.bookData());
        facade.loadUserMap(DataConstant.userData());
        facade.loadMemberMap(DataConstant.libraryMemberData());
        System.out.println(facade.readBooksMap());
        System.out.println(facade.readUserMap());
    }
}
