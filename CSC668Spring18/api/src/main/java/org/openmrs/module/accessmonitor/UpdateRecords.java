/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.accessmonitor;

import java.util.Date;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.accessmonitor.api.AccessMonitorService;

/**
 *
 * @author Travis
 */
public class UpdateRecords {
    public static void add(User user, Integer id, String recordType, String actionType, Date date) {
        AccessMonitor record = new AccessMonitor();
        record.setTimestamp(date);
        record.setAccessingUserId(user.getUserId());
        record.setUserGiven(user.getGivenName());
        record.setUserFamily(user.getFamilyName());
        record.setRecordId(id);
        record.setRecordType(recordType);
        record.setActionType(actionType);
        Context.getService(AccessMonitorService.class).saveAccessMonitor(record);
    }
}
