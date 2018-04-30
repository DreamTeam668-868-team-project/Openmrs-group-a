/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.api;

import java.util.List;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.csc668spring18.AccessRecordConfig;
import org.openmrs.module.csc668spring18.RecordList;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Travis
 */
public interface RecordListService extends OpenmrsService {
        
        /**
	 * Returns a record by id. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	public RecordList getRecordListById(Integer id) throws APIException;
        
        /**
	 * Returns a record by id. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	public List<RecordList> getRecordListByAccessId(Integer accessRecordId) throws APIException;
        
        public List<RecordList> saveRecordList(List<RecordList> recordList) throws APIException;
}
