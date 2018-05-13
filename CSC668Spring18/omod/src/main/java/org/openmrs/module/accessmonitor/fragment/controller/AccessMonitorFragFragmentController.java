/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor.fragment.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.AccessMonitor;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;
import java.util.Date;
import org.openmrs.module.accessmonitor.ChartData;
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
	        @RequestParam(value = "end", required = false) Date endDate, UiUtils ui) {//@SpringBean("userService") UserService service,
		AccessMonitorService service = Context.getService(AccessMonitorService.class);
		System.out.println("Chart!!!");
		List<ChartData> chartData = service.getNumberOfRecords(startDate, endDate, 24);
//                List<DetailData> chartData = service.getFilteredNumberOfRecords(startDate, endDate, 24);
		System.out.println("Chart!!! Start Date: " + startDate + ", End Date: " + endDate + ", Count: " + chartData.size());
		
		String[] properties = new String[] { "start", "number" };
		
		return SimpleObject.fromCollection(chartData, ui, properties);
	}
	
	public List<SimpleObject> getDetailData(@RequestParam(value = "start", required = false) Date startDate,
	        @RequestParam(value = "end", required = false) Date endDate, UiUtils ui) {
		AccessMonitorService service = Context.getService(AccessMonitorService.class);
		System.out.println("Detail!!! Start Date: " + startDate + ", End Date: " + endDate);
		//		System.out.println(startDate.getClass());
		//		System.out.println(endDate.getClass());
		//		AccessMonitorService service = Context.getService(AccessMonitorService.class);
		List<AccessMonitor> chartData = service.getAccessMonitors(null, startDate, endDate);
		//		System.out.println(chartData.size());
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
		
		return SimpleObject.fromCollection(chartData, ui, properties);
		//				return new ArrayList<SimpleObject>();
	}
}
