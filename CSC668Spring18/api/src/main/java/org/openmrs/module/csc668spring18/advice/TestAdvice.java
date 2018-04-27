/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Location;
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.api.AccessRecordService;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author Travis
 */
public class TestAdvice implements AfterReturningAdvice {
	
	protected Log log = LogFactory.getLog(getClass());
	
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		
		if (method.equals("getNewTables")) {
			log.info("Test Advice for CSC 668 Spring 2018 Module");
			log.info(target.toString());
			AccessRecord record = new AccessRecord();
			
			// id is set by the database, don't need to set it here
			record.setAccessedOn(new Date());
			record.setAccessingUser(Context.getAuthenticatedUser());
			record.setAccessLocation(new Location()); // can't think of how to get this info without an httpsession object atm
			
			record.setRecordId(0);
			record.setRecordType("TEST");
			Context.getService(AccessRecordService.class).saveAccessRecord(record);
		}
	}
}
