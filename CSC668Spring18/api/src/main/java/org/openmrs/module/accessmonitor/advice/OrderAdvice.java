/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.openmrs.Order;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
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
			
			actionType = "RETRIEVAL";
			Date date = new Date();
			List<Order> returnList = (List<Order>) returnObject;
			
			for (Iterator<Order> i = returnList.iterator(); i.hasNext();) {
				AccessMonitor record = new AccessMonitor();
				record.setTimestamp(date);
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(i.next().getId());
				record.setRecordType(recordType);
				record.setActionType(actionType);
				Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
				
			}
			
			return;
		}
		
		// getters
		// returns type Order
		if (method.getName().startsWith("getOrder") && returnObject.getClass().equals(Order.class)) {
			
			actionType = "RETRIEVAL";
			
			Order order = (Order) returnObject;
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(order.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
			return;
		}
		
		// deletion methods
		if (method.getName().equals("voidOrder") || method.getName().equals("purgeOrder")) {
			
			actionType = "DELETE";
			Order order = (Order) args[0];
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(order.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
			return;
		}
		
		if (method.getName().equals("unvoidOrder")) {
			actionType = "UNVOID";
			
			Order order = (Order) args[0];
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(order.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
			return;
		}
		
		if (method.getName().equals("saveOrder")) {
			actionType = "CREATE";
			
			Order order = (Order) args[0];
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(order.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
		}
	}
}
