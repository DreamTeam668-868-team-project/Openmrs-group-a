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
package org.openmrs.module.accessmonitor;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.BaseOpenmrsObject;

/**
 * @author Travis
 */
public class AccessRecord extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private Integer accessingUserId;
	
	private Date accessedOn;
	
	private Integer recordId;
	
	private String recordType;
	
	private String actionType;
	
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
	
	public Integer getAccessingUserId() {
		return this.accessingUserId;
	}
	
	public void setAccessingUserId(java.lang.Integer userId) {
		this.accessingUserId = userId;
	}
	
	public String getActionType() {
		return this.actionType;
	}
	
	public void setActionType(java.lang.String actionType) {
		this.actionType = actionType;
	}
	
	public void setAccessedOn(Date date) {
		this.accessedOn = date;
	}
	
	public Date getAccessedOn() {
		return this.accessedOn;
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
