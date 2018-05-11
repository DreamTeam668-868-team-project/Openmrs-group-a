/**
 * The contents of this file are subject to the OpenMRS Public License Version
 * 1.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Copyright (C) OpenMRS, LLC. All Rights Reserved.
 */
package org.openmrs.module.accessmonitor.api.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.module.accessmonitor.AccessMonitorConfig;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
import org.openmrs.module.accessmonitor.api.db.AccessMonitorDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * It is a default implementation of {@link AccessMonitorService}.
 */
public class AccessMonitorServiceImpl extends BaseOpenmrsService implements AccessMonitorService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private AccessMonitorDAO dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(AccessMonitorDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public AccessMonitor getAccessMonitorByUuid(String uuid) throws APIException {
		return dao.getRecordByUuid(uuid);
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public AccessMonitor getAccessMonitor(Integer id) throws APIException {
		return dao.getRecord(id);
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAllAccessMonitors() throws APIException {
		return dao.getAllRecords();
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAccessMonitorsByDate(Date date) throws APIException {
		return dao.getRecordsByDate(date);
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAccessMonitorsByUser(Integer userId) throws APIException {
		return dao.getRecordsByUser(userId);
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAccessMonitorsByTimeframe(Date start, Date end) throws APIException {
		return dao.getRecordsByTimeframe(start, end);
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAccessMonitorsByUserandDate(Integer userId, Date date) throws APIException {
		return dao.getRecordsByUserandDate(userId, date);
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAccessMonitorsByUserandTimeframe(Integer userId, Date start, Date end) throws APIException {
		return dao.getRecordsByUserandTimeframe(userId, start, end);
	}
	
	/**
	 * Returns a List of records for the given user and timeframe. It can be called by any
	 * authenticated user. It is fetched in read only transaction.
	 * 
	 * @param userId Integer userId or null
	 * @param start start date for time frame, single date, or null
	 * @param end end date for time frame, single date, or null
	 * @return List of AccessMonitors (might be list of 1)
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessMonitor> getAccessMonitors(Integer userId, Date start, Date end) {
		
		// code the method parameters as an integer
		int method = 0;
		if (userId != null) {
			method += 4;
		}
		if (start != null) {
			method += 2;
		}
		if (end != null) {
			method += 1;
		}
		
		Date date = start;
		List<AccessMonitor> list = null;
		
		// call the appropriate method, according to the provided params
		switch (method) {
			case 1:
				date = end;
			case 2: // getAccessMonitorsByDate()
				list = dao.getRecordsByDate(date);
				break;
			case 3: // getAccessMonitorsByTimeframe();
				list = dao.getRecordsByTimeframe(start, end);
				break;
			case 4: // getAccessMonitorsByUser()
				list = dao.getRecordsByUser(userId);
				break;
			case 5: // getAccessMonitorsByUserAndDate()
				date = end;
			case 6:
				list = dao.getRecordsByUserandDate(userId, date);
				break;
			case 7: // getAccessRecrodsByUserAndTimeframe()
				list = dao.getRecordsByUserandTimeframe(userId, start, end);
				break;
			default: // getAllAccessMonitors()
				list = dao.getAllRecords();
		}
		
		return list;
	}
	
	/**
	 * Returns a number of records in given timeframe, seperated by interval authenticated user. It
	 * is fetched in read only transaction.
	 * 
	 * @param start the start of the time frame
	 * @param end the end of the time frame
	 * @param interval integer number of hours to separate segments by
	 * @return list of Ojbect in sequence of Date, Integer, Date, Integer, etc... representing the
	 *         number of system accesses at the specified time represented by the Date object
	 * @throws APIException
	 */
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<Object> getNumberOfRecords(Date start, Date end, Integer interval) throws IllegalArgumentException,
	        APIException {
		if (interval < 1 || interval > 24) {
			throw new IllegalArgumentException();
		}
		
		List<Object> list = new ArrayList();
		
		Calendar startTime = Calendar.getInstance();
		Calendar stopTime = Calendar.getInstance();
		startTime.setTime(start);
		stopTime.setTime(start);
		startTime.set(Calendar.HOUR_OF_DAY, 0);
		stopTime.set(Calendar.HOUR_OF_DAY, 0);
		stopTime.add(Calendar.DATE, 1);
		stopTime.add(Calendar.HOUR_OF_DAY, interval);
		stopTime.add(Calendar.MILLISECOND, -1);
		
		while (!stopTime.after(end)) {
			list.add(startTime.getTime());
			list.add(Context.getService(AccessMonitorService.class)
			        .getAccessMonitors(null, startTime.getTime(), stopTime.getTime()).size());
			startTime.add(Calendar.HOUR_OF_DAY, interval);
			stopTime.add(Calendar.HOUR_OF_DAY, interval);
		}
		
		return list;
	}
	
	@Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
	@Transactional
	@Override
	public AccessMonitor saveAccessMonitor(AccessMonitor record) throws APIException {
		//		if (record.getOwner() == null) {
		//			record.setOwner(userService.getUser(1));
		//		}
		
		return dao.saveAccessMonitor(record);
	}
}
