package org.rubiconred.pagerduty;

import java.io.IOException;

public class App 
{
	public static void main(String[] args) throws Exception {
		String integrationKey = null;
		if(args!=null && args.length > 0 ) {
			for(int argsIndex=0;argsIndex<args.length;argsIndex++) {
				if(args[argsIndex].equalsIgnoreCase("-integrationKey")){
					integrationKey = args[argsIndex+1];
				}
				
			}
		}
		if(integrationKey == null) {
			throw new Exception("Integration Key is not supplied. Please provide in the format -integrationKey <integrationKeyValue>");
		}
		PagerDutyIncident pgiObj = new PagerDutyIncident(integrationKey);
		pgiObj.notifier();
	}
}
