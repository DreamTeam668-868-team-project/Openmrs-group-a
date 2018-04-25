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

import java.sql.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.csc668spring18.NewTable;
import org.openmrs.module.csc668spring18.api.db.NewTableDAO;

/**
 * It is a default implementation of {@link NewTableDAO}.
 */
public class HibernateNewTableDAO implements NewTableDAO {

    protected final Log log = LogFactory.getLog(this.getClass());

    private DbSessionFactory sessionFactory;

    private DbSession getSession() {
        return sessionFactory.getCurrentSession();
    }

    public NewTable getRecordByUuid(String uuid) {
        return (NewTable) getSession().createCriteria(NewTable.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
    }

    public NewTable getRecord(Integer id) {
        return (NewTable) getSession().createCriteria(NewTable.class).add(Restrictions.eq("record_id", id)).uniqueResult();
    }

    public List<NewTable> getAllRecords() {
        return (List<NewTable>) getSession().createCriteria(NewTable.class).list();
    }

    public List<NewTable> getRecordsByDate(Date date) {
        Criteria criteria = getSession().createCriteria(NewTable.class);
        criteria.add(Restrictions.eq("access_date", date));
        return (List<NewTable>) criteria.list();
    }

    public List<NewTable> getRecordsByUser(Integer userId) {
        Criteria criteria = getSession().createCriteria(NewTable.class);
        criteria.add(Restrictions.eq("accessing_user", userId));
        return (List<NewTable>) criteria.list();
    }

    public List<NewTable> getRecordsByTimeframe(Date start, Date end) {
        Criteria criteria = getSession().createCriteria(NewTable.class);
        criteria.add(Restrictions.ge("access_date", start));
        criteria.add(Restrictions.le("access_date", end));
        return (List<NewTable>) criteria.list();
    }

    public List<NewTable> getRecordsByUserandDate(Integer userId, Date date) {
        Criteria criteria = getSession().createCriteria(NewTable.class);
        criteria.add(Restrictions.eq("accessing_user", userId));
        criteria.add(Restrictions.eq("access_date", date));
        return (List<NewTable>) criteria.list();
    }

    public List<NewTable> getRecordsByUserandTimeframe(Integer userId, Date start, Date end) {
        Criteria criteria = getSession().createCriteria(NewTable.class);
        criteria.add(Restrictions.eq("accessing_user", userId));
        criteria.add(Restrictions.ge("access_date", start));
        criteria.add(Restrictions.le("access_date", end));
        return (List<NewTable>) criteria.list();
    }

    public NewTable saveRecord(NewTable record) {
        getSession().saveOrUpdate(record);
        return record;
    }

}
