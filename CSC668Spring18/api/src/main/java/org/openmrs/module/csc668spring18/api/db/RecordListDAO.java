/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.api.db;

import java.util.List;
import org.openmrs.module.csc668spring18.RecordList;

/**
 *
 * @author Travis
 */
public interface RecordListDAO {

    public RecordList getRecordListById(Integer id);
        
    public List<RecordList> getRecordListByAccessId(Integer accessRecordId);
    
    public List<RecordList> getAllRecordLists();
    
    public List<RecordList> saveRecordList(List<RecordList> recordList);
    
}
