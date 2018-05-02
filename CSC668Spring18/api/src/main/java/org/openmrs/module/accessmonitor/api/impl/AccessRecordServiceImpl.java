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

import java.util.Date;
import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.module.accessmonitor.AccessRecord;
import org.openmrs.module.accessmonitor.AccessMonitorConfig;
import org.openmrs.module.accessmonitor.api.AccessRecordService;
import org.openmrs.module.accessmonitor.api.db.AccessRecordDAO;
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

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public AccessRecord getAccessRecordByUuid(String uuid) throws APIException {
        return dao.getRecordByUuid(uuid);
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public AccessRecord getAccessRecord(Integer id) throws APIException {
        return dao.getRecord(id);
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAllAccessRecords() throws APIException {
        return dao.getAllRecords();
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAccessRecordsByDate(Date date) throws APIException {
        return dao.getRecordsByDate(date);
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAccessRecordsByUser(Integer userId) throws APIException {
        return dao.getRecordsByUser(userId);
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAccessRecordsByTimeframe(Date start, Date end) throws APIException {
        return dao.getRecordsByTimeframe(start, end);
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAccessRecordsByUserandDate(Integer userId, Date date) throws APIException {
        return dao.getRecordsByUserandDate(userId, date);
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAccessRecordsByUserandTimeframe(Integer userId, Date start, Date end) throws APIException {
        return dao.getRecordsByUserandTimeframe(userId, start, end);
    }

    /**
     * Returns a List of records for the given user and timeframe. It can be
     * called by any authenticated user. It is fetched in read only transaction.
     *
     * @param userId Integer userId or null
     * @param start start date for time frame, single date, or null
     * @param end end date for time frame, single date, or null
     * @return List of AccessRecords (might be list of 1)
     * @throws APIException
     */
    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional(readOnly = true)
    @Override
    public List<AccessRecord> getAccessRecords(Integer userId, Date start, Date end) {
        
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
        List<AccessRecord> list = null;
        
        // call the appropriate method, according to the provided params
        switch (method) {
            case 1:
                date = end;
            case 2: // getAccessRecordsByDate()
                list = dao.getRecordsByDate(date);
                break;
            case 3: // getAccessRecordsByTimeframe();
                list = dao.getRecordsByTimeframe(start, end);
                break;
            case 4: // getAccessRecordsByUser()
                list = dao.getRecordsByUser(userId);
                break;
            case 5: // getAccessRecordsByUserAndDate()
                date = end;
            case 6:
                list = dao.getRecordsByUserandDate(userId, date);
                break;
            case 7: // getAccessRecrodsByUserAndTimeframe()
                list = dao.getRecordsByUserandTimeframe(userId, start, end);
                break;
            default: // getAllAccessRecords()
                list = dao.getAllRecords();
        }

        return list;
    }

    @Authorized(AccessMonitorConfig.MODULE_PRIVILEGE)
    @Transactional
    @Override
    public AccessRecord saveAccessRecord(AccessRecord record) throws APIException {
        //		if (record.getOwner() == null) {
        //			record.setOwner(userService.getUser(1));
        //		}

        return dao.saveAccessRecord(record);
    }
}
