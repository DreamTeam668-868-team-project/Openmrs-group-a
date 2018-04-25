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
import java.sql.Time;
import java.util.Date;
import org.openmrs.BaseOpenmrsObject;
import org.openmrs.Location;
import org.openmrs.User;

public class NewTable extends BaseOpenmrsObject implements Serializable {
	
	private static final long serialVersionUID = 1L;


    private Integer id;

    private User accessingUser;

    private Location accessLocation;

    private java.sql.Date accessDate;

    private Time accessTime;

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

    public User getUser() {
        return this.accessingUser;
    }

    public void setUser(User user) {
        this.accessingUser = user;
    }

    public Location getAccessLocation() {
        return this.accessLocation;
    }

    public void setAccessLocation(Location location) {
        this.accessLocation = location;
    }

    public void setAccessDate(java.sql.Date date) {
        this.accessDate = date;
    }

    public java.sql.Date getAccessDate() {
        return this.accessDate;
    }

    public void setAccessTime(Time time) {
        this.accessTime = time;
    }

    public Time getAccessTime() {
        return this.accessTime;
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
