/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the VisitService Class in the OpenMRS system provides
 * reporting on when methods are invoked from the PersonService interface
 * Focuses on methods which retrieve, create, and delete Person records
 *
 * @author Travis
 */
public class VisitAdvice implements AfterReturningAdvice {

    /**
     * @param returnObject the object returned from the invoking method
     * @param method the invoking method
     * @param args the list of arguments provided to the invoking method when it
     * was called
     * @param target
     */
    @Override
    public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
        String recordType = "VISIT";
        String actionType = "";

        // getters
        // returns type List<Visit>
        if (method.getName().startsWith("getVisits")
                || method.getName().startsWith("getActiveVisits")
                || method.getName().equals("getAllVisits")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "RETRIEVAL";
                Date date = new Date();
                List<Visit> returnList = (List<Visit>) returnObject;

                for (Visit visit : returnList) {
                    AccessRecord record = new AccessRecord();
                    record.setAccessedOn(date);
                    record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                    record.setRecordId(visit.getId());
                    record.setRecordType(recordType);
                    record.setActionType(actionType);
                }
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        // getters
        // returns type Visit
        if (method.getName().startsWith("getVisit")
                && returnObject.getClass().equals(Visit.class)) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "RETRIEVAL";

                Visit visit = (Visit) returnObject;

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(visit.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        // deletion methods
        if (method.getName().equals("voidVisit") || method.getName().equals("purgeVisit")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "DELETE";
                Visit visit = (Visit) args[0];

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(visit.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        if (method.getName().equals("unvoidVisit")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "UNVOID";

                Visit visit = (Visit) args[0];

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(visit.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        if (method.getName().equals("saveVisit")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "CREATE";

                Visit visit = (Visit) args[0];

                AccessRecord record = new AccessRecord();
                record.setAccessedOn(new Date());
                record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                record.setRecordId(visit.getId());
                record.setRecordType(recordType);
                record.setActionType(actionType);
            } finally {
                Context.closeSessionWithCurrentUser();
            }
        }
    }
}
