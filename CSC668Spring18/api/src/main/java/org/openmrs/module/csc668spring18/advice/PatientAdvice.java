/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the PatientService Class in the OpenMRS system provides
 * reporting on when methods are invoked from the PatientService interface
 * Focuses on methods which retrieve, create, or delete patient record(s)
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
            try {
                Context.openSessionWithCurrentUser();

                actionType = "RETRIEVAL";

                List<Patient> returnList = (List<Patient>) returnObject;
                for (Patient patient : returnList) {
                    AccessRecord record = new AccessRecord();
                    record.setAccessedOn(new Date());
                    record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                    record.setRecordId(patient.getId());
                    record.setRecordType(recordType);
                    record.setActionType(actionType);
                }
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        // getters
        // returns type Patient
        if (method.getName().startsWith("getPatient")) {
            try {
                Context.openSessionWithCurrentUser();

                actionType = "RETRIEVAL";

                Patient patient = (Patient) returnObject;

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(patient.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        // todo: potentially use before advice for these
        // deletion methods
        if (method.getName().equals("voidPatient") || method.getName().equals("purgePatient")) {
            try {
                Context.openSessionWithCurrentUser();

                actionType = "DELETE";
                Patient patient = (Patient) args[0];

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(patient.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        if (method.getName().equals("unvoidPatient")) {
            try {
                Context.openSessionWithCurrentUser();

                actionType = "UNVOID";

                Patient patient = (Patient) args[0];

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(patient.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        // create or update
        if (method.getName().equals("savePatient")) {
            try {
                Context.openSessionWithCurrentUser();

                actionType = "CREATE or UPDATE"; // no way to tell whether this is a creation or not, at this point

                Patient patient = (Patient) args[0];

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(patient.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
        }
    }
}
