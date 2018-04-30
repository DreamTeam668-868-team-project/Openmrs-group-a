/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.api.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.csc668spring18.AccessRecordConfig;
import org.openmrs.module.csc668spring18.RecordList;
import org.openmrs.module.csc668spring18.api.RecordListService;
import org.openmrs.module.csc668spring18.api.db.RecordListDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Travis
 */
public class RecordListServiceImpl extends BaseOpenmrsService implements RecordListService {

    protected final Log log = LogFactory.getLog(this.getClass());

    private RecordListDAO dao;

    UserService userService;

    /**
     * Injected in moduleApplicationContext.xml
     */
    public void setDao(RecordListDAO dao) {
        this.dao = dao;
    }

    /**
     * Returns a record by id. It can be called by any authenticated user. It is
     * fetched in read only transaction.
     *
     * @param id
     * @return
     * @throws APIException
     */    
    @Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public RecordList getRecordListById(Integer id) throws APIException {
        return dao.getRecordListById(id);
    }

    /**
     * Returns a record by id. It can be called by any authenticated user. It is
     * fetched in read only transaction.
     *
     * @param accessId
     * @return
     * @throws APIException
     */
    @Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<RecordList> getRecordListByAccessId(Integer accessId) throws APIException {
        return dao.getRecordListByAccessId(accessId);
    }
    
    public List<RecordList> getAllRecordLists() throws APIException {
        return dao.getAllRecordLists();
    }
    
    @Override
    public List<RecordList> saveRecordList(List<RecordList> recordList) throws APIException {
        return dao.saveRecordList(recordList);
    }

}
