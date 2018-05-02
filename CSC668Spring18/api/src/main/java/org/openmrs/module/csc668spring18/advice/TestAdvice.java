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
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.api.AccessRecordService;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author Travis
 */
public class TestAdvice implements AfterReturningAdvice {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		if (method.getName().startsWith("get")) {
			
			System.out.println("Start of Test Advice for CSC 668 Spring 2018 Module\n");
			try {
				Context.openSessionWithCurrentUser();
				System.out.println(returnObject.toString());
				System.out.println(method.getName().toString());
				for (Object obj : args) {
					System.out.println(obj.toString());
				}
				System.out.println(target.toString());
				
				System.out.println("\n\n");
				// Context.setUserContext(ctx);
				AccessRecord record = new AccessRecord();
				record.setAccessedOn(new Date());
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(0);
				record.setRecordType("TEST");
				record.setActionType("TEST");
				
				Context.getService(AccessRecordService.class).saveAccessRecord(record);
			}
			finally {
				Context.closeSessionWithCurrentUser();
			}
			log.debug("End of Test Advice for CSC 668 Spring 2018 Module\n");
			System.out.println("End of Test Advice for CSC 668 Spring 2018 Module");
		}
	}
}
