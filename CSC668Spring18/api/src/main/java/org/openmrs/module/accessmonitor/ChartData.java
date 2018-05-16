/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor;

import java.io.Serializable;
import java.util.Date;
import org.openmrs.BaseOpenmrsObject;

/**
 * @author guanming
 */
public class ChartData extends BaseOpenmrsObject implements Serializable {
	
	private Integer id;
	
	private Date start;
	
	private Integer number;
	
	public ChartData(Date s, Date e, Integer n) {
		start = s;
		number = n;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getStart() {
		return start;
	}
	
	public void setStart(Date time) {
		this.start = time;
	}
	
	public Integer getNumber() {
		return number;
	}
	
	public void setNumber(Integer number) {
		this.number = number;
	}
}
