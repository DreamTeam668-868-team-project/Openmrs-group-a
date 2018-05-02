/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the PersonService Class in the OpenMRS system provides
 * reporting on when methods are invoked from the PersonService interface
 * Focuses on methods which retrieve, create, and delete Person records
 *
 * @author Travis
 */
public class PersonAdvice implements AfterReturningAdvice {

    /**
     * @param returnObject the object returned from the invoking method
     * @param method the invoking method
     * @param args the list of arguments provided to the invoking method when it
     * was called
     * @param target
     */
    @Override
    public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
        //            if((method.getDeclaringClass()).equals(Context.getPersonService().getClass())){
        //                if(returnObject.getClass().equals(Person.class)){
        //                    
        //                }
        //            }
        String recordType = "PERSON";
        String actionType = "";

        // getters
        // returns type List<Person>
        if (method.getName().equals("getPeople")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "RETRIEVAL";
                Date date = new Date();
                List<Person> returnList = (List<Person>) returnObject;

                for (Person person : returnList) {
                    AccessRecord record = new AccessRecord();
                    record.setAccessedOn(date);
                    record.setAccessingUserId(Context.getAuthenticatedUser().getUserId());
                    record.setRecordId(person.getId());
                    record.setRecordType(recordType);
                    record.setActionType(actionType);
                }
            } finally {
                Context.closeSessionWithCurrentUser();
            }
            return;
        }

        // getters
        // returns type Person
        if (method.getName().startsWith("getPerson")) {
            try {
                Context.openSessionWithCurrentUser();

                actionType = "RETRIEVAL";

                Person patient = (Person) returnObject;

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

        // deletion methods
        if (method.getName().equals("voidPerson") || method.getName().equals("purgePerson")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "DELETE";
                Person patient = (Person) args[0];

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

        if (method.getName().equals("unvoidPerson")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "UNVOID";

                Person patient = (Person) args[0];

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

        if (method.getName().equals("savePerson")) {
            try {
                Context.openSessionWithCurrentUser();
                actionType = "CREATE";

                Person patient = (Person) args[0];

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
