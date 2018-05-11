/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.fragment.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
import java.util.Date;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author levine
 */
public class AccessMonitorFragFragmentController {
	
	public void controller(FragmentModel model, HttpSession session, @SpringBean("userService") UserService service) {
		List<AccessMonitor> tables = Context.getService(AccessMonitorService.class).getAllAccessMonitors();
		
		model.addAttribute("tableitems", tables);
		model.addAttribute("user", Context.getAuthenticatedUser());
	}
	
	public List<SimpleObject> getChartData(@RequestParam(value = "start", required = false) Date startDate,
	        @RequestParam(value = "end", required = false) Date endDate, @SpringBean("userService") UserService service,
	        UiUtils ui) {
		System.out.println("Start Date: " + startDate + ", End Date: " + endDate);
		
		List<User> allUsers = service.getAllUsers();
		
		String[] properties;
		properties = new String[2];
		properties[0] = "givenName";
		properties[1] = "familyName";
		
		return SimpleObject.fromCollection(allUsers, ui, properties);
	}
	
	public List<SimpleObject> getDetailData(@RequestParam(value = "start", required = false) Date startDate,
	        @RequestParam(value = "end", required = false) Date endDate, UiUtils ui) {
		AccessMonitorService service = Context.getService(AccessMonitorService.class);
		System.out.println("Start Date: " + startDate + ", End Date: " + endDate);
		System.out.println(startDate.getClass());
		System.out.println(endDate.getClass());
		//		AccessMonitorService service = Context.getService(AccessMonitorService.class);
		List<AccessMonitor> chartData = service.getAccessMonitors(null, startDate, endDate);
		System.out.println(chartData.size());
		String[] properties;
		properties = new String[8];
		properties[0] = "id";
		properties[1] = "accessingUserId";
		properties[2] = "timestamp";
		properties[3] = "recordId";
		properties[4] = "recordType";
		properties[5] = "actionType";
		properties[6] = "userGiven";
		properties[7] = "userFamily";
		
		System.out.println("!!!!");
		return SimpleObject.fromCollection(chartData, ui, properties);
		//				return new ArrayList<SimpleObject>();
	}
}
