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
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.AccessRecordConfig;
import org.openmrs.module.csc668spring18.api.AccessRecordService;
import org.openmrs.module.csc668spring18.api.db.AccessRecordDAO;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public AccessRecord getAccessRecordByUuid(String uuid) throws APIException {
		return dao.getRecordByUuid(uuid);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public AccessRecord getAccessRecord(Integer id) throws APIException {
		return dao.getRecord(id);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessRecord> getAllAccessRecords() throws APIException {
		return dao.getAllRecords();
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessRecord> getAccessRecordsByDate(Date date) throws APIException {
		return dao.getRecordsByDate(date);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessRecord> getAccessRecordsByUser(Integer userId) throws APIException {
		return dao.getRecordsByUser(userId);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessRecord> getAccessRecordsByTimeframe(Date start, Date end) throws APIException {
		return dao.getRecordsByTimeframe(start, end);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessRecord> getAccessRecordsByUserandDate(Integer userId, Date date) throws APIException {
		return dao.getRecordsByUserandDate(userId, date);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional(readOnly = true)
	@Override
	public List<AccessRecord> getAccessRecordsByUserandTimeframe(Integer userId, Date start, Date end) throws APIException {
		return dao.getRecordsByUserandTimeframe(userId, start, end);
	}
	
	@Authorized(AccessRecordConfig.MODULE_PRIVILEGE)
	@Transactional
	@Override
	public AccessRecord saveAccessRecord(AccessRecord record) throws APIException {
		//		if (record.getOwner() == null) {
		//			record.setOwner(userService.getUser(1));
		//		}
		
		return dao.saveAccessRecord(record);
	}
}
