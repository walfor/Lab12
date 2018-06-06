package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.HashMap;

public class FlowIdMap extends HashMap<LocalDate, Flow> {

	public Flow get(Flow flow) {

		Flow old = super.get(flow.getDay());

		if (old == null) {
			super.put(flow.getDay(), flow);
			return flow;
		}

		return old;

	}
}
