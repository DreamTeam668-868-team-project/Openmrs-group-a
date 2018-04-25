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
package org.openmrs.module.csc668spring18.api.db;

import java.sql.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.csc668spring18.NewTable;
import org.openmrs.module.csc668spring18.api.NewTableService;

/**
 * Database methods for {@link NewTableService}.
 */
public interface NewTableDAO {
	
    public NewTable getRecordByUuid(String uuid);
    
    public NewTable getRecord(Integer id);

    public List<NewTable> getAllRecords();

    public List<NewTable> getRecordsByDate(Date date);

    public List<NewTable> getRecordsByUser(Integer userId);
    
    public List<NewTable> getRecordsByTimeframe(Date start, Date end);

    public List<NewTable> getRecordsByUserandDate(Integer userId, Date date);

    public List<NewTable> getRecordsByUserandTimeframe(Integer userId, Date start, Date end);

    public NewTable saveRecord(NewTable record);
}
