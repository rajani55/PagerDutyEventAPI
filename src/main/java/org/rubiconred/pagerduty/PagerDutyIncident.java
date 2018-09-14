package org.rubiconred.pagerduty;



import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;


import com.github.dikhan.pagerduty.client.events.PagerDutyEventsClient;
import com.github.dikhan.pagerduty.client.events.domain.Payload;
import com.github.dikhan.pagerduty.client.events.domain.Severity;
import com.github.dikhan.pagerduty.client.events.domain.TriggerIncident;
import com.github.dikhan.pagerduty.client.events.exceptions.NotifyEventException;

public class PagerDutyIncident  {
		private String integrationKey;
		
		public PagerDutyIncident(String integrationKey){
			this.integrationKey = integrationKey ;
		}
		
		public void notifier() throws IOException {

		PagerDutyEventsClient pagerDutyEventsClient = PagerDutyEventsClient.create();
		String uuid = UUID.randomUUID().toString();
		Payload payload = Payload.Builder.newBuilder()
				.setSummary("test summary " + uuid)
				.setSource("testing host")
				.setSeverity(Severity.INFO)
				.setTimestamp(OffsetDateTime.now())
				.build();

		TriggerIncident incident = TriggerIncident.TriggerIncidentBuilder
				.newBuilder(this.integrationKey, payload)
				.setDedupKey(uuid)
				.build();
		try {
			Object obj = pagerDutyEventsClient.trigger(incident);
		} catch (NotifyEventException e) {
			throw new RuntimeException(e);
		}

	}

}
