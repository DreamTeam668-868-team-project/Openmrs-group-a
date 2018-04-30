/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.api.db.hibernate;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.RecordList;

/**
 *
 * @author Travis
 */
public class HibernateRecordListDAO {

    protected final Log log = LogFactory.getLog(this.getClass());

    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public RecordList getRecordListById(Integer id) {
        return (RecordList) getSession().createCriteria(RecordList.class).add(Restrictions.eq("record_id", id))
		        .uniqueResult();        
    }

    public List<RecordList> getRecordListByAccessId(Integer accessId) {
        Criteria criteria = getSession().createCriteria(RecordList.class);
		criteria.add(Restrictions.eq("access_id", accessId));
        return (List<RecordList>) criteria.list();
    }
    
    public List<RecordList> getAllRecordLists(){
        Criteria criteria = getSession().createCriteria(RecordList.class);
        return (List<RecordList>) criteria.list();
    }

    public List<RecordList> saveRecordList(List<RecordList> recordList) {
        for (RecordList record : recordList)
            getSession().saveOrUpdate(record);
        
        return recordList;
    }
}
