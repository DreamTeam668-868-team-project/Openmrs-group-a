/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.advice;

import java.lang.reflect.Method;
import java.util.Date;
import org.openmrs.Patient;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author 
 */
public class VisitAdvice implements AfterReturningAdvice {
	
	@Override
	public void afterReturning(Object returnObject, Method method, Object[] args, Object target) {
		Context.getVisitService().getAllVisits();
		Context.getVisitService().getVisits(null, null, null, null, new Date(), new Date(), new Date(), new Date(), null,
		    true, true);
		// args are Collection<VisitType>, Collection<Patient>, Collection<Location>, Collection<Concept>
		// Map<VisitAttributeType, Object>
		Context.getVisitService().getVisit(new Integer(0));
		Context.getVisitService().getVisitByUuid(new String());
		Context.getVisitService().getVisitsByPatient(new Patient());
		Context.getVisitService().getVisitsByPatient(new Patient(), true, true);
		Context.getVisitService().purgeVisit(new Visit());
		Context.getVisitService().voidVisit(new Visit(), new String());
		Context.getVisitService().unvoidVisit(new Visit());
		Context.getVisitService().stopVisits(new Date());
		Context.getVisitService().saveVisit(new Visit());
	}
	
}
