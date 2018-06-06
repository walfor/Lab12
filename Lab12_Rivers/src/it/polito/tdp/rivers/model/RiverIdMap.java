package it.polito.tdp.rivers.model;

import java.util.HashMap;

public class RiverIdMap extends HashMap<Integer, River> {

	public River get(River river) {

		River old = super.get(river.getId());

		if (old == null) {
			super.put(river.getId(), river);
			return river;
		}

		return old;

	}
}
