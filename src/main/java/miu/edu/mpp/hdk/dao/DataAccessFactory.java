package miu.edu.mpp.hdk.dao;

import miu.edu.mpp.hdk.dao.impl.DataAccessFacade;
import miu.edu.mpp.hdk.dao.impl.DataAccessMongoDB;
import miu.edu.mpp.hdk.exception.DataAccessException;

public class DataAccessFactory {

    private static DataAccessMongoDB mongoDB;
    private static DataAccessFacade facade;

//    private static Set<String> dataAccessSupport = new HashSet<>(){
//        {
//            add(DataAccessMongoDB.class.getName());
//            add(DataAccessFacade.class.getName());
//        }
//    };

    public static DataAccess createDataAccess(String access) {
        if (access.equals(DataAccessMongoDB.class.getName())) {
            if (mongoDB == null) {
                mongoDB = DataAccessMongoDB.INSTANCE;
            }
            return mongoDB;
        }
        if (access.equals(DataAccessFacade.class.getName())) {
            if (facade == null) {
                facade = new DataAccessFacade();
            }
            return facade;
        }
        throw new DataAccessException("Data access not supported!");
    }

    public static DataAccessFacade defaultDataAccess() {
        if (facade == null) {
            facade = new DataAccessFacade();
        }
        return facade;
    }
}
