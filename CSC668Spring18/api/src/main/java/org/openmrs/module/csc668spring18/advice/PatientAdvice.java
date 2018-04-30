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
 * Advice code for the Patient Class in the OpenMRS system provides reporting on
 * when methods are invoked from the PatientService interface
 *
 * @author Travis
 */
public class PatientAdvice implements AfterReturningAdvice {
/**
 * 
 * @param returnObject
 * @param method
 * @param args
 * @param target 
 */
    @Override
    public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
        if (method.getName().equals("getAllPatients") ||
            method.getName().equals("getPatients")){

            
            
        }
        
        /* returns type List<Patient>
        Context.getPatientService().getAllPatients();
        Context.getPatientService().getDuplicatePatientsByAttributes(null);
        Context.getPatientService().getPatients("");
        */
        
        
        if (method.getName().startsWith("getPatient")) {
            
        }
        /* returns type Patient
        Context.getPatientService().getPatient(new Integer(0));
        Context.getPatientService().getPatientByExample(new Patient());
        Context.getPatientService().getPatientByUuid(new String());
        Context.getPatientService().getPatientOrPromotePerson(new Integer(0));
        */
        
        
        if (method.getName().equals("voidPatient") ||
            method.getName().equals("purgePatient") ||
            method.getName().equals("unvoidPatient") ||
            method.getName().equals("savePatient")){
            
        }
        /* alter, create, remove patient records
        Context.getPatientService().voidPatient(new Patient(), new String()); // also returns patient
        Context.getPatientService().unvoidPatient(new Patient());
        Context.getPatientService().purgePatient(new Patient());
        Context.getPatientService().savePatient(new Patient()); // returns patient
        */
    }

}
