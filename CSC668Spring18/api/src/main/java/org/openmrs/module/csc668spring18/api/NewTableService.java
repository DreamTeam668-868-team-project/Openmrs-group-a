/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.csc668spring18.api;

import java.sql.Date;
import java.util.List;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.csc668spring18.NewTable;
import org.openmrs.module.csc668spring18.NewTableConfig;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured
 * in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(NewTableService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface NewTableService extends OpenmrsService {
	
	/**
	 * Returns an record by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	NewTable getRecordByUuid(String uuid) throws APIException;
        
        /**
	 * Returns an record by id. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
        @Authorized()
	@Transactional(readOnly = true)
        NewTable getRecord(Integer id) throws APIException;
	
        /**
	 * Returns a List of all records. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
        @Authorized()
	@Transactional(readOnly = true)
        List<NewTable> getAllRecords() throws APIException;
        
        /**
	 * Returns a List of records for the given date. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(NewTableConfig.MODULE_PRIVILEGE)
	@Transactional
        List<NewTable> getRecordsByDate(Date date) throws APIException;
        
        /**
	 * Returns a List of records for the given user_id. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(NewTableConfig.MODULE_PRIVILEGE)
	@Transactional
        List<NewTable> getRecordsByUser(Integer userId) throws APIException;
        
        /**
	 * Returns a List of records for the given timeframe. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(NewTableConfig.MODULE_PRIVILEGE)
	@Transactional
        List<NewTable> getRecordsByTimeframe(Date start, Date end);
        
        /**
	 * Returns a List of records for the given user and date. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(NewTableConfig.MODULE_PRIVILEGE)
	@Transactional
        List<NewTable> getRecordsByUserandDate(Integer userId, Date date);
        
        /**
	 * Returns a List of records for the given user and timeframe. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(NewTableConfig.MODULE_PRIVILEGE)
	@Transactional
        List<NewTable> getRecordsByUserandTimeframe(Integer userId, Date start, Date end);
        
	/**
	 * Saves an record. Sets the owner to superuser, if it is not set. It can be called by users
	 * with this module's privilege. It is executed in a transaction.
	 * 
	 * @param record
	 * @return
	 * @throws APIException
	 */
	@Authorized(NewTableConfig.MODULE_PRIVILEGE)
	@Transactional
	NewTable saveRecord(NewTable record) throws APIException;
}
