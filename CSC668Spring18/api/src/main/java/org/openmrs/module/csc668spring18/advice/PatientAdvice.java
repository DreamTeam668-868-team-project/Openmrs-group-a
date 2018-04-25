/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import java.util.Date;
import org.openmrs.Concept;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifierType;
import org.openmrs.activelist.Allergy;
import org.openmrs.activelist.Problem;
import org.openmrs.api.context.Context;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author Travis
 */
public class PatientAdvice implements AfterReturningAdvice {
	
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		
		// Patient retrievals
		// returns type List<Patient>
		Context.getPatientService().getAllPatients();
		Context.getPatientService().getDuplicatePatientsByAttributes(null);
		Context.getPatientService().getPatients("");
		
		// returns type Patient
		Context.getPatientService().getPatient(new Integer(0));
		Context.getPatientService().getPatientByExample(new Patient());
		Context.getPatientService().getPatientByUuid(new String());
		Context.getPatientService().getPatientOrPromotePerson(new Integer(0));
		
		// alter, create, remove patient records
		Context.getPatientService().voidPatient(new Patient(), new String()); // also returns patient
		Context.getPatientService().unvoidPatient(new Patient());
		Context.getPatientService().purgePatient(new Patient());
		Context.getPatientService().processDeath(new Patient(), new Date(), new Concept(), new String());
		Context.getPatientService().savePatient(new Patient()); // returns patient
		
		Context.getPatientService().removeAllergy(new Allergy(), new String());
		Context.getPatientService().removeProblem(new Problem(), new String());
		Context.getPatientService().saveAllergy(new Allergy());
		Context.getPatientService().saveProblem(new Problem());
		Context.getPatientService().saveCauseOfDeathObs(new Patient(), new Date(), new Concept(), new String());
		
		// deprecated methods --- maybe intercept these as well
		Context.getPatientService().createPatient(new Patient());
		Context.getPatientService().deletePatient(new Patient());
		Context.getPatientService().findPatients(new String(), true);
		Context.getPatientService().findDuplicatePatients(null /* Type is Set<String> */);
		Context.getPatientService().getPatientsByIdentifier(new String(), true);
		Context.getPatientService().getPatientsByName(new String());
		Context.getPatientService().identifierInUse(new String(), new PatientIdentifierType(), new Patient());
		Context.getPatientService().updatePatient(new Patient());
		
	}
	
}
