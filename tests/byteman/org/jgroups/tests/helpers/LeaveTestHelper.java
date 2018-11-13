package org.jgroups.tests.helpers;

import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;
import org.jgroups.protocols.pbcast.GmsImpl;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Bela Ban
 */
public class LeaveTestHelper extends Helper {
    protected Collection<GmsImpl.Request> requests=new ArrayList<>(5);

    protected LeaveTestHelper(Rule rule) {
        super(rule);
        if(linked("requests") == null)
            link("requests", requests);
        else
            requests=(Collection<GmsImpl.Request>)linked("requests");
    }

    public void processRequests(Collection<GmsImpl.Request> reqs) {
        for(GmsImpl.Request r: reqs) {
            if(r.getType() == GmsImpl.Request.LEAVE) {
                requests.add(r);
                System.out.printf("r: %s, list: %s\n", r, requests);
            }
        }
    }

}
