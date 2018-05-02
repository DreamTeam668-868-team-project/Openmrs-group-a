/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.openmrs.Order;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessRecord;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the OrderService Class in the OpenMRS system provides reporting on when methods
 * are invoked from the PersonService interface Focuses on methods which retrieve, create, and
 * delete Person records
 * 
 * @author Travis
 */
public class OrderAdvice implements AfterReturningAdvice {
	
	/**
	 * @param returnObject the object returned from the invoking method
	 * @param method the invoking method
	 * @param args the list of arguments provided to the invoking method when it was called
	 * @param target
	 */
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		String recordType = "ORDER";
		String actionType = "";
		
		// getters
		// returns type List<Order>
		if (method.getName().equals("getOrders") || method.getName().equals("getActiveOrders")
		        || method.getName().equals("getAllOrdersByPatient")) {
			
			try {
				Context.openSessionWithCurrentUser();
				
				actionType = "RETRIEVAL";
				Date date = new Date();
				List<Order> returnList = (List<Order>) returnObject;
				
				for (Order order : returnList) {
					AccessRecord record = new AccessRecord();
					record.setAccessedOn(date);
					record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
					record.setRecordId(order.getId());
					record.setRecordType(recordType);
					record.setActionType(actionType);
				}
			}
			finally {
				Context.closeSessionWithCurrentUser();
			}
			return;
		}
		
		// getters
		// returns type Order
		if (method.getName().startsWith("getOrder") && returnObject.getClass().equals(Order.class)) {
			
			try {
				Context.openSessionWithCurrentUser();
				
				actionType = "RETRIEVAL";
				
				Order order = (Order) returnObject;
				
				AccessRecord record = new AccessRecord();
				record.setAccessedOn(new Date());
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(order.getId());
				record.setRecordType(recordType);
				record.setActionType(actionType);
				
			}
			finally {
				Context.closeSessionWithCurrentUser();
			}
			return;
		}
		
		// deletion methods
		if (method.getName().equals("voidOrder") || method.getName().equals("purgeOrder")) {
			try {
				Context.openSessionWithCurrentUser();
				
				actionType = "DELETE";
				Order order = (Order) args[0];
				
				AccessRecord record = new AccessRecord();
				record.setAccessedOn(new Date());
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(order.getId());
				record.setRecordType(recordType);
				record.setActionType(actionType);
			}
			finally {
				Context.closeSessionWithCurrentUser();
			}
			return;
		}
		
		if (method.getName().equals("unvoidOrder")) {
			try {
				Context.openSessionWithCurrentUser();
				
				actionType = "UNVOID";
				
				Order order = (Order) args[0];
				
				AccessRecord record = new AccessRecord();
				record.setAccessedOn(new Date());
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(order.getId());
				record.setRecordType(recordType);
				record.setActionType(actionType);
			}
			finally {
				Context.closeSessionWithCurrentUser();
			}
			return;
		}
		
		if (method.getName().equals("saveOrder")) {
			try {
				Context.openSessionWithCurrentUser();
				actionType = "CREATE";
				
				Order order = (Order) args[0];
				
				AccessRecord record = new AccessRecord();
				record.setAccessedOn(new Date());
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(order.getId());
				record.setRecordType(recordType);
				record.setActionType(actionType);
			}
			finally {
				Context.closeSessionWithCurrentUser();
			}
		}
	}
}
