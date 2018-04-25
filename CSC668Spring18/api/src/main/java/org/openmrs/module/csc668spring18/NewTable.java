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
package org.openmrs.module.csc668spring18;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.BaseOpenmrsObject;

public class NewTable extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String info;
	
	private Date entryDate;
	
	private int userId;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
	public Date getEntryDate() {
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
