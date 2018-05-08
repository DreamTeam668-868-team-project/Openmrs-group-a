/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the PatientService Class in the OpenMRS system provides reporting on when methods
 * are invoked from the PatientService interface Focuses on methods which retrieve, create, or
 * delete patient record(s)
 * 
 * @author Travis
 */
public class PatientAdvice implements AfterReturningAdvice {
	
	/**
	 * @param returnObject
	 * @param method
	 * @param args
	 * @param target
	 */
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		
		String recordType = "PATIENT";
		String actionType = "";
		// getters
		// returns type List<Patient>
		if (method.getName().equals("getAllPatients") || method.getName().equals("getPatients")) {
			actionType = "RETRIEVAL";
			
			List<Patient> returnList = (List<Patient>) returnObject;
			for (Patient patient : returnList) {
				AccessMonitor record = new AccessMonitor();
				record.setTimestamp(new Date());
				record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
				record.setRecordId(patient.getId());
				record.setRecordType(recordType);
				record.setActionType(actionType);
			}
			return;
		}
		
		// getters
		// returns type Patient
		if (method.getName().startsWith("getPatient")) {
			
			actionType = "RETRIEVAL";
			
			Patient patient = (Patient) returnObject;
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(patient.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			return;
		}
		
		// todo: potentially use before advice for these
		// deletion methods
		if (method.getName().equals("voidPatient") || method.getName().equals("purgePatient")) {
			
			actionType = "DELETE";
			Patient patient = (Patient) args[0];
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(patient.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			
			return;
		}
		
		if (method.getName().equals("unvoidPatient")) {
			
			actionType = "UNVOID";
			
			Patient patient = (Patient) args[0];
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(patient.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
			return;
		}
		
		// create or update
		if (method.getName().equals("savePatient")) {
			
			actionType = "CREATE or UPDATE"; // no way to tell whether this is a creation or not, at this point
			
			Patient patient = (Patient) args[0];
			
			AccessMonitor record = new AccessMonitor();
			record.setTimestamp(new Date());
			record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
			record.setRecordId(patient.getId());
			record.setRecordType(recordType);
			record.setActionType(actionType);
		}
	}
}
