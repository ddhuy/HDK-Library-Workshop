package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.Book;

import java.util.List;

public class BookController {

    private final DataAccess da = DataAccessFactory.createDataAccess(DataAccessType.MONGO);

    public List<Book> getListBook(){
        return da.readBooksMap().values().stream().toList();
    }
}
