/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import org.openmrs.Person;
import org.openmrs.api.context.Context;
import org.springframework.aop.AfterReturningAdvice;

/**
 * Advice code for the Person Class in the OpenMRS system provides reporting on when methods are
 * invoked from the PersonService interface
 * 
 * @author Travis
 */
public class PersonAdvice implements AfterReturningAdvice {
	
	/**
	 * @param returnObject the object returned from the invoking method
	 * @param method the invoking method
	 * @param args the list of arguments provided to the invoking method when it was called
	 * @param target
	 */
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
//            if((method.getDeclaringClass()).equals(Context.getPersonService().getClass())){
//                if(returnObject.getClass().equals(Person.class)){
//                    
//                }
//            }
	}
	
	public void list() {
		Context.getPersonService().getPeople(null, Boolean.TRUE);
		Context.getPersonService().getPeople(null, Boolean.TRUE, Boolean.TRUE);
		Context.getPersonService().getPerson(Integer.BYTES);
		Context.getPersonService().getPersonByUuid(null);
		Context.getPersonService().getSimilarPeople(null, Integer.BYTES, null);
		Context.getPersonService().savePerson(null);
		Context.getPersonService().voidPerson(null, null);
		Context.getPersonService().unvoidPerson(null);
		Context.getPersonService().purgePerson(null);
	}
	
}
