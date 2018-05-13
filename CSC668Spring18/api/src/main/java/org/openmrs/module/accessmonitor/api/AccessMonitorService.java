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
package org.openmrs.module.accessmonitor.api;

import java.util.Date;
import java.util.List;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.module.accessmonitor.AccessMonitorConfig;
import org.openmrs.module.accessmonitor.ChartData;
import org.openmrs.module.accessmonitor.DetailData;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured
 * in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(AccessMonitorService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional(readOnly = true)
public interface AccessMonitorService extends OpenmrsService {
	
	/**
	 * Returns a record by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	public AccessMonitor getAccessMonitorByUuid(String uuid) throws APIException;
	
	/**
	 * Returns an record by id. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	AccessMonitor getAccessMonitor(Integer id) throws APIException;
	
	/**
	 * Returns a List of all records. It can be called by any authenticated user. It is fetched in
	 * read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAllAccessMonitors() throws APIException;
	
	/**
	 * Returns a List of records for the given date. It can be called by any authenticated user. It
	 * is fetched in read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAccessMonitorsByDate(Date date) throws APIException;
	
	/**
	 * Returns a List of records for the given user_id. It can be called by any authenticated user.
	 * It is fetched in read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAccessMonitorsByUser(Integer userId) throws APIException;
	
	/**
	 * Returns a List of records for the given timeframe. It can be called by any authenticated
	 * user. It is fetched in read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAccessMonitorsByTimeframe(Date start, Date end);
	
	/**
	 * Returns a List of records for the given user and date. It can be called by any authenticated
	 * user. It is fetched in read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAccessMonitorsByUserandDate(Integer userId, Date date);
	
	/**
	 * Returns a List of records for the given user and timeframe. It can be called by any
	 * authenticated user. It is fetched in read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAccessMonitorsByUserandTimeframe(Integer userId, Date start, Date end);
	
	/**
	 * Returns a List of records for the given user and timeframe. It can be called by any
	 * authenticated user. It is fetched in read only transaction.
	 * 
	 * @param id
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<AccessMonitor> getAccessMonitors(Integer userId, Date start, Date end);
	
	/**
	 * Returns the number of records for a given timeframe and time interval authenticated user. It
	 * is fetched in read only transaction.
	 * 
	 * @param Date start
	 * @param Date end
	 * @param Integer interval
	 * @return Integer
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<ChartData> getNumberOfRecords(Date start, Date end, Integer interval) throws IllegalArgumentException, APIException;
	
	/**
	 * Returns a number of records in given timeframe, separated by interval and user
	 * 
	 * @param start the start of the time frame
	 * @param end the end of the time frame
	 * @param interval integer number of hours to separate segments by
	 * @return
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	List<DetailData> getFilteredNumberOfRecords(Date start, Date end, Integer interval) throws IllegalArgumentException,
	        APIException;
	
	/**
	 * Saves an record. Sets the owner to superuser, if it is not set. It can be called by users
	 * with this module's privilege. It is executed in a transaction.
	 * 
	 * @param record
	 * @return
	 * @throws APIException
	 */
	AccessMonitor saveAccessMonitor(AccessMonitor record) throws APIException;
}
