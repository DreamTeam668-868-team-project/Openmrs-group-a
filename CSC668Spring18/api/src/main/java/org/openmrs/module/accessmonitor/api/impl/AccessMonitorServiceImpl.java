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
import org.openmrs.module.accessmonitor.ChartData;
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
				Calendar stopTime = Calendar.getInstance();
				stopTime.setTime(end);
				stopTime.add(Calendar.DATE, 1);
				list = dao.getRecordsByTimeframe(start, stopTime.getTime());
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
	 * Returns a number of records in given timeframe, seperated by interval. It is fetched in read
	 * only transaction.
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
	public List<ChartData> getNumberOfRecords(Date start, Date end, Integer interval) throws IllegalArgumentException,
	        APIException {
		
		if (interval < 1 || interval > 24) {
			throw new IllegalArgumentException();
		}
		
		// create calendar objects to make time based decisions
		Calendar startTime = Calendar.getInstance();
		Calendar stopTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		Calendar tempCal = Calendar.getInstance();
		
		// set startTime
		startTime.setTime(start);
		
		// set stop time to be one interval after start
		stopTime.setTime(start);
		stopTime.add(Calendar.HOUR_OF_DAY, interval);
		
		// set end time to be one day after provided date
		endTime.setTime(end);
		endTime.add(Calendar.DATE, 1);
		
		List<AccessMonitor> data = Context.getService(AccessMonitorService.class).getAccessMonitors(null,
		    startTime.getTime(), endTime.getTime());
		
		System.out.println(data.size());
		
		// create the return list
		List<ChartData> list = new ArrayList();
		List<AccessMonitor> temp = new ArrayList();
		
		for (int i = 0; i < data.size(); ++i) {
			tempCal.setTime(data.get(i).getTimestamp());
			
			// if the current entry is outside the current timeframe, cycle forward
			while (tempCal.before(startTime) || !tempCal.before(stopTime)) {
				
				// check if there is data to add to the return value, and do so if necessary
				if (!temp.isEmpty()) {
					list.add(new ChartData(startTime.getTime(), stopTime.getTime(), temp.size()));
					temp = new ArrayList();
				}
				
				// cycle timeframe forward one interval
				startTime.add(Calendar.HOUR_OF_DAY, interval);
				stopTime.add(Calendar.HOUR_OF_DAY, interval);
				
				// this is the only section of code that could loop infinitely
				// this should stop it if it does occur, and alert
				if (startTime.after(endTime)) {
					log.error("Error in getSummedRecordList: Infinite loop");
					return new ArrayList();
				}
			}
			temp.add(data.get(i));
		}
		if (!temp.isEmpty()) {
			// add last of the data to the return list
			list.add(new ChartData(startTime.getTime(), stopTime.getTime(), temp.size()));
			System.out.println(list.size());
		}
		return list;
	}
	
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
	@Override
	public List<ChartData> getFilteredNumberOfRecords(Date start, Date end, Integer interval)
	        throws IllegalArgumentException, APIException {
		
		if (interval < 1 || interval > 24) {
			throw new IllegalArgumentException();
		}
		
		// create calendar objects to make time based decisions
		Calendar startTime = Calendar.getInstance();
		Calendar stopTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		Calendar tempCal = Calendar.getInstance();
		
		// set startTime
		startTime.setTime(start);
		
		// set stop time to be one interval after start
		stopTime.setTime(start);
		stopTime.add(Calendar.HOUR_OF_DAY, interval);
		
		// set end time to be one day after provided date
		endTime.setTime(end);
		endTime.add(Calendar.DATE, 1);
		
		List<AccessMonitor> data = Context.getService(AccessMonitorService.class).getAccessMonitors(null,
		    startTime.getTime(), endTime.getTime());
		
		System.out.println(data.size());
		
		// create the return list
		List<ChartData> list = new ArrayList();
		List<AccessMonitor> temp = new ArrayList();
		Integer lastUserId = data.get(0).getAccessingUserId();
		String lastUserGiven = data.get(0).getUserGiven();
		String lastUserFamily = data.get(0).getUserFamily();
		
		for (int i = 0; i < data.size(); ++i) {
			tempCal.setTime(data.get(i).getTimestamp());
			
			// if the current entry is outside the current timeframe, cycle forward
			while (tempCal.before(startTime) || !tempCal.before(stopTime)) {
				
				// check if there is data to add to the return value, and do so
				if (!temp.isEmpty()) {
					System.out.println("Add 312");
					list.add(new ChartData(lastUserId, lastUserGiven, lastUserFamily, startTime.getTime(), stopTime
					        .getTime(), temp.size()));
					
					// list was added, so reset these values
					lastUserId = data.get(i).getAccessingUserId();
					lastUserGiven = data.get(i).getUserGiven();
					lastUserFamily = data.get(i).getUserFamily();
					
					// and clear the temp list
					temp = new ArrayList();
				}
				
				// cycle timeframe forward one interval
				startTime.add(Calendar.HOUR_OF_DAY, interval);
				stopTime.add(Calendar.HOUR_OF_DAY, interval);
				
				// this is the only section of code that could loop infinitely
				// this should stop it if it does occur, and alert
				if (startTime.after(endTime)) {
					log.error("Error in getFilteredSummedRecordList: Infinite loop");
					return new ArrayList();
				}
			}
			
			// check if this user is the same as the last user
			// add current list to output and clear if user is different
			if (!lastUserId.equals(data.get(i).getAccessingUserId())) {
				System.out.println("Add 340");
				list.add(new ChartData(lastUserId, lastUserGiven, lastUserFamily, startTime.getTime(), stopTime.getTime(),
				        temp.size()));
				lastUserId = data.get(i).getAccessingUserId();
				lastUserGiven = data.get(i).getUserGiven();
				lastUserFamily = data.get(i).getUserFamily();
				temp = new ArrayList();
			}
			
			// add entry to temp list either way
			temp.add(data.get(i));
			
		}
		if (!temp.isEmpty()) {
			// add last of the data to the return list
			list.add(new ChartData(lastUserId, lastUserGiven, lastUserFamily, startTime.getTime(), stopTime.getTime(), temp
			        .size()));
		}
		System.out.println(list.size());
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
