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
package org.openmrs.module.csc668spring18.api.impl;

import java.util.Date;
import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.api.AccessRecordService;
import org.openmrs.module.csc668spring18.api.db.AccessRecordDAO;

/**
 * It is a default implementation of {@link AccessRecordService}.
 */
public class AccessRecordServiceImpl extends BaseOpenmrsService implements AccessRecordService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private AccessRecordDAO dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(AccessRecordDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public AccessRecord getRecordByUuid(String uuid) throws APIException {
		return dao.getRecordByUuid(uuid);
	}
	
	@Override
	public AccessRecord getRecord(Integer id) throws APIException {
		return dao.getRecord(id);
	}
	
	@Override
	public List<AccessRecord> getAllRecords() throws APIException {
		return dao.getAllRecords();
	}
	
	@Override
	public List<AccessRecord> getRecordsByDate(Date date) throws APIException {
		return dao.getRecordsByDate(date);
	}
	
	@Override
	public List<AccessRecord> getRecordsByUser(Integer userId) throws APIException {
		return dao.getRecordsByUser(userId);
	}
	
	@Override
	public List<AccessRecord> getRecordsByTimeframe(Date start, Date end) throws APIException {
		return dao.getRecordsByTimeframe(start, end);
	}
	
	@Override
	public List<AccessRecord> getRecordsByUserandDate(Integer userId, Date date) throws APIException {
		return dao.getRecordsByUserandDate(userId, date);
	}
	
	@Override
	public List<AccessRecord> getRecordsByUserandTimeframe(Integer userId, Date start, Date end) throws APIException {
		return dao.getRecordsByUserandTimeframe(userId, start, end);
	}
	
	@Override
	public AccessRecord saveRecord(AccessRecord record) throws APIException {
		//		if (record.getOwner() == null) {
		//			record.setOwner(userService.getUser(1));
		//		}
		
		return dao.saveRecord(record);
	}
}
