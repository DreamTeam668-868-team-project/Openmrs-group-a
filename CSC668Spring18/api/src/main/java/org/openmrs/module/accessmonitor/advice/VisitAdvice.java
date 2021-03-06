/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.advice;

import java.lang.reflect.Method;
import java.util.Date;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.module.accessmonitor.UpdateRecords;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the VisitService Class in the OpenMRS system provides reporting on when methods
 * are invoked from the PersonService interface Focuses on methods which retrieve, create, and
 * delete Person records
 * 
 * @author Travis
 */
public class VisitAdvice implements AfterReturningAdvice {
	
	/**
	 * @param returnObject the object returned from the invoking method
	 * @param method the invoking method
	 * @param args the list of arguments provided to the invoking method when it was called
	 * @param target
	 */
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		String recordType = "VISIT";
		String actionType = "";
		
		// getters
		// returns type List<Visit>
		//		if (method.getName().startsWith("getVisits") || method.getName().startsWith("getActiveVisits")
		//		        || method.getName().equals("getAllVisits")) {
		//			actionType = "RETRIEVAL";
		//			Date date = new Date();
		//			List<Visit> returnList = (List<Visit>) returnObject;
		//			
		//			for (Iterator<Visit> i = returnList.iterator(); i.hasNext();) {
		//				AccessMonitor record = new AccessMonitor();
		//				record.setTimestamp(date);
		//				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
		//				record.setRecordId(i.next().getId());
		//				record.setRecordType(recordType);
		//				record.setActionType(actionType);
		//				Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
		//			}
		//			return;
		//		}
		
		// getters
		// returns type Visit
		if (method.getName().startsWith("getVisit") && returnObject.getClass().equals(Visit.class)) {
			actionType = "RETRIEVAL";
			
			Visit visit = (Visit) returnObject;
			UpdateRecords.add(Context.getAuthenticatedUser(), visit.getId(), recordType, actionType, new Date());
			return;
		}
		
		// deletion methods
		if (method.getName().equals("voidVisit") || method.getName().equals("purgeVisit")) {
			actionType = "DELETE";
			Visit visit = (Visit) args[0];
			UpdateRecords.add(Context.getAuthenticatedUser(), visit.getId(), recordType, actionType, new Date());
			return;
		}
		
		if (method.getName().equals("unvoidVisit")) {
			actionType = "UNVOID";
			
			Visit visit = (Visit) args[0];
			UpdateRecords.add(Context.getAuthenticatedUser(), visit.getId(), recordType, actionType, new Date());
			return;
		}
		
		if (method.getName().equals("saveVisit")) {
			actionType = "CREATE";
			
			Visit visit = (Visit) args[0];
			UpdateRecords.add(Context.getAuthenticatedUser(), visit.getId(), recordType, actionType, new Date());
		}
	}
}
