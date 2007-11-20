package fr.umlv.hmm2000;

import fr.umlv.hmm2000.map.MapForegroundElement;

public class Event {

	private final MapForegroundElement source;

	public Event(MapForegroundElement source) {
		this.source = source;
	}

	public MapForegroundElement getSource() {
		return this.source;
	}

}
