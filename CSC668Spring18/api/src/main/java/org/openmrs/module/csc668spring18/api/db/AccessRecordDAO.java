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

import java.util.Date;
import java.util.List;
import org.openmrs.module.csc668spring18.AccessRecord;

/**
 * Database methods for {@link NewTableService}.
 */
public interface AccessRecordDAO {
	
	public AccessRecord getRecordByUuid(String uuid);
	
	public AccessRecord getRecord(Integer id);
	
	public List<AccessRecord> getAllRecords();
	
	public List<AccessRecord> getRecordsByDate(Date date);
	
	public List<AccessRecord> getRecordsByUser(Integer userId);
	
	public List<AccessRecord> getRecordsByTimeframe(Date start, Date end);
	
	public List<AccessRecord> getRecordsByUserandDate(Integer userId, Date date);
	
	public List<AccessRecord> getRecordsByUserandTimeframe(Integer userId, Date start, Date end);
	
	public AccessRecord saveRecord(AccessRecord record);
}
