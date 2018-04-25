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

import java.util.List;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.csc668spring18.NewTable;
import org.openmrs.module.csc668spring18.api.NewTableService;
import org.openmrs.module.csc668spring18.api.db.NewTableDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * It is a default implementation of {@link NewTableService}.
 */
public class NewTableServiceImpl extends BaseOpenmrsService implements NewTableService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private NewTableDAO dao;
	
	/**
	 * @param dao the dao to set
	 */
	public void setDao(NewTableDAO dao) {
		this.dao = dao;
	}
	
	/**
	 * @return the dao
	 */
	public NewTableDAO getDao() {
		return dao;
	}
	
	public Log getLog() {
		return log;
	}
	
	@Transactional(readOnly = true)
	public NewTable getNewTable(Integer id) {
		return dao.getNewTable(id);
	}
	
	@Transactional
	public NewTable saveNewTable(NewTable doe) {
		return dao.saveNewTable(doe);
	}
	
	@Transactional(readOnly = true)
	public List<NewTable> getNewTables() {
		return dao.getNewTables();
	}
}
