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
package org.openmrs.module.accessmonitor.page.controller;

import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.UserService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.stereotype.Controller;

/**
 * The main controller.
 */
@Controller
public class AccessMonitorPageController {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	public void controller(PageModel model, HttpSession session, @SpringBean("userService") UserService service) {
		return;
		
	}
	
	//	public String post(HttpSession session, HttpServletRequest request,
	//	        @RequestParam(value = "info", required = false) String info) {
	//		
	//		String url = (request.getRequestURL().toString()).trim();
	//		System.out.println("AccessMonitorPageController - POST: BEFORE FIX\n" + url + "\n");
	//		url = url.substring(url.indexOf("//") + 2);
	//		url = url.substring(url.indexOf("/") + 1);
	//		
	//		System.out.println("AccessMonitorPageController - POST: \n" + url + "\n");
	//		AccessMonitor n = new AccessMonitor();
	//		Context.getService(AccessMonitorService.class).saveAccessMonitor(n);
	//		return "redirect:" + url;
	//	}
}
