package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.LibraryMember;

import java.util.List;

public class MemberController {
    private final DataAccess da = DataAccessFactory.createDataAccess(DataAccessType.MONGO);

    public List<LibraryMember> getListMember(){
        return da.readMemberMap().values().stream().toList();
    }

}
