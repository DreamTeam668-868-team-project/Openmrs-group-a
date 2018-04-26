/**
 * The contents of this file are subject to the OpenMRS Public License Version
 * 1.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Copyright (C) OpenMRS, LLC. All Rights Reserved.
 */
package org.openmrs.module.csc668spring18.page.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.module.csc668spring18.api.AccessRecordService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main controller.
 */
@Controller
public class AccessRecordPageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public void controller(PageModel model, HttpSession session, @SpringBean("userService") UserService service) {
		return;
		
	}
	
	public String post(HttpSession session, HttpServletRequest request,
	        @RequestParam(value = "info", required = false) String info) {
		
		String url = (request.getRequestURL().toString()).trim();
		System.out.println("AccessRecordPageController - POST: BEFORE FIX\n" + url + "\n");
		url = url.substring(url.indexOf("//") + 2);
		url = url.substring(url.indexOf("/") + 1);
		
		System.out.println("AccessRecordPageController - POST: \n" + url + "\n");
		AccessRecord n = new AccessRecord();
		Context.getService(AccessRecordService.class).saveRecord(n);
		return "redirect:" + url;
	}
}
