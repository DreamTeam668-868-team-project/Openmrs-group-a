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

import java.util.List;
import org.openmrs.Patient;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.csc668spring18.NewTable;
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
	
	public NewTable saveNewTable(NewTable newTable);
	
	/**
	 * Get a {@link NewTable} object by primary key id.
	 * 
	 * @param id the primary key integer id to look up on
	 * @return the found NewTable object which matches the row with the given id. If no row with the
	 *         given id exists, null is returned.
	 */
	public NewTable getNewTable(Integer id);
	
	public List<NewTable> getNewTables();
}
