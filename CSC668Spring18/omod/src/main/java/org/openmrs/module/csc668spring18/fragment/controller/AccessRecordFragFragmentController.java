/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.csc668spring18.fragment.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.csc668spring18.AccessRecord;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.module.csc668spring18.api.AccessRecordService;

/**
 * @author levine
 */
public class AccessRecordFragFragmentController {
	
	public void controller(FragmentModel model, HttpSession session, @SpringBean("userService") UserService service) {
		List<AccessRecord> tables = Context.getService(AccessRecordService.class).getAllAccessRecords();
		
		model.addAttribute("tableitems", tables);
		model.addAttribute("user", Context.getAuthenticatedUser());
		
	}
	
	public List<SimpleObject> getUsers(@SpringBean("userService") UserService service, UiUtils ui) {
		List<User> allUsers = service.getAllUsers();
		
		System.out.println("AccessRecordFragFragmentController, getUsers: ");
		String[] properties;
		properties = new String[2];
		properties[0] = "givenName";
		properties[1] = "familyName";
		
		return SimpleObject.fromCollection(allUsers, ui, properties);
	}
}
