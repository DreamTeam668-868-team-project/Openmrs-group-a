/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.advice;

import java.lang.reflect.Method;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author Travis
 */
public class UserAdvice implements AfterReturningAdvice {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		if (method.getName().equals("getAllUsers")) {
			
			System.out.println("Start of Test Advice for CSC 668 Spring 2018 Module\n");
			
			System.out.println(returnObject.toString());
			System.out.println(method.getName().toString());
			for (Object obj : args) {
				System.out.println(obj.toString());
			}
			System.out.println(target.toString());
			
			System.out.println("\n\n");
			// Context.setUserContext(ctx);
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(0);
			record.setRecordType("TEST");
			record.setActionType("TEST");
			
			Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
		}
		log.debug("End of Test Advice for CSC 668 Spring 2018 Module\n");
		System.out.println("End of Test Advice for CSC 668 Spring 2018 Module");
	}
}
