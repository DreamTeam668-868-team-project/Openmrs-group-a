/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.BaseOpenmrsObject;

/**
 *
 * @author Travis
 */
public class RecordList extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
        
        private Integer accessId;
	
	private Integer recordId;
	
	private String recordType;
        
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		
		this.id = id;
	}
	
	@Override
	public String getUuid() {
		return super.getUuid();
	}
	
	@Override
	public void setUuid(String uuid) {
		super.setUuid(uuid);
	}
	
        public void setAccessId(Integer accessId) {
		this.accessId = accessId;
	}
	
	public Integer getAccessId() {
		return this.accessId;
	}
        
        public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	
	public Integer getRecordId() {
		return this.recordId;
	}
	
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
	public String getRecordType() {
		return this.recordType;
	}    
}
