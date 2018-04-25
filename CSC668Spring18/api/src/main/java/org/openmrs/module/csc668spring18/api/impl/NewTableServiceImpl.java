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

import java.sql.Date;
import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.module.csc668spring18.NewTable;
import org.openmrs.module.csc668spring18.api.NewTableService;
import org.openmrs.module.csc668spring18.api.db.NewTableDAO;

/**
 * It is a default implementation of {@link NewTableService}.
 */
public class NewTableServiceImpl extends BaseOpenmrsService implements NewTableService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private NewTableDAO dao;
	
	

    UserService userService;

    /**
     * Injected in moduleApplicationContext.xml
     */
    public void setDao(NewTableDAO dao) {
        this.dao = dao;
    }

    /**
     * Injected in moduleApplicationContext.xml
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public NewTable getRecordByUuid(String uuid) throws APIException {
        return dao.getRecordByUuid(uuid);
    }

    @Override
    public NewTable getRecord(Integer id) throws APIException {
        return dao.getRecord(id);
    }

    @Override
    public List<NewTable> getAllRecords() throws APIException {
        return dao.getAllRecords();
    }

    @Override
    public List<NewTable> getRecordsByDate(Date date) throws APIException {
        return dao.getRecordsByDate(date);
    }

    @Override
    public List<NewTable> getRecordsByUser(Integer userId) throws APIException {
        return dao.getRecordsByUser(userId);
    }

    @Override
    public List<NewTable> getRecordsByTimeframe(Date start, Date end) throws APIException {
        return dao.getRecordsByTimeframe(start, end);
    }

    @Override
    public List<NewTable> getRecordsByUserandDate(Integer userId, Date date) throws APIException {
        return dao.getRecordsByUserandDate(userId, date);
    }

    @Override
    public List<NewTable> getRecordsByUserandTimeframe(Integer userId, Date start, Date end) throws APIException {
        return dao.getRecordsByUserandTimeframe(userId, start, end);
    }

    @Override
    public NewTable saveRecord(NewTable record) throws APIException {
//		if (record.getOwner() == null) {
//			record.setOwner(userService.getUser(1));
//		}

        return dao.saveRecord(record);
    }
}
