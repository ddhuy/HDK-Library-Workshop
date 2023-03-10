package miu.edu.mpp.hdk.controller;

import miu.edu.mpp.hdk.dao.DataAccess;
import miu.edu.mpp.hdk.dao.impl.DataAccessFactory;
import miu.edu.mpp.hdk.enums.DBCollection;
import miu.edu.mpp.hdk.enums.DataAccessType;
import miu.edu.mpp.hdk.model.CheckoutRecord;
import miu.edu.mpp.hdk.model.LibraryMember;

import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class MemberController {
    private final DataAccess da = DataAccessFactory.createDataAccess(DataAccessType.MONGO);

    public List<LibraryMember> getListMember(){
        return da.readMemberMap().values().stream().toList();
    }

    public void checkout(CheckoutRecord record){
        da.saveToStorage(DBCollection.CHECKOUT_RECORD, record);
    }

    public String printCheckoutRecord(){
        StringJoiner joiner = new StringJoiner("\n");
        HashMap<String, Object> map = da.getDataCollection(DBCollection.CHECKOUT_RECORD);
        map.values().forEach(ob->{
            CheckoutRecord record = (CheckoutRecord) ob;
            joiner.add(record.toString());
        });
        return joiner.toString();
    }

}
