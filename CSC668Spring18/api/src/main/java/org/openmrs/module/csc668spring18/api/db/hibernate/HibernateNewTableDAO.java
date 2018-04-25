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
package org.openmrs.module.csc668spring18.api.db.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.module.csc668spring18.NewTable;
import org.openmrs.module.csc668spring18.api.db.NewTableDAO;

/**
 * It is a default implementation of {@link NewTableDAO}.
 */
public class HibernateNewTableDAO implements NewTableDAO {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public NewTable getNewTable(Integer id) {
		return (NewTable) sessionFactory.getCurrentSession().get(NewTable.class, id);
	}
	
	/*
	public NewTable getNewTableByUuid(String uuid) {
	    Criteria crit = sessionFactory.getCurrentSession().createCriteria(
	            NewTable.class);
	    crit.add(Restrictions.eq("uuid", uuid));
	    return (NewTable) crit.uniqueResult();
	}

	 */
	public NewTable saveNewTable(NewTable newTable) {
		sessionFactory.getCurrentSession().saveOrUpdate(newTable);
		return newTable;
	}
	
	public List<NewTable> getNewTables() {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(NewTable.class);
		return crit.list();
	}
	
}
