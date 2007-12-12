package fr.umlv.hmm2000.engine.event;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MapForegroundElement;

public class SelectionEvent {
	
	private final MapForegroundElement source;

	private final Location location;

	public SelectionEvent(MapForegroundElement source, Location location) {
		this.source = source;
		this.location = location;
	}
	
	public MapForegroundElement getSource() {
		return this.source;
	}

	public Location getLocation() {
		return this.location;
	}

}
