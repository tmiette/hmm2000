package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.Event;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MapForegroundElement;

public class SelectionEvent extends Event {

	private final Location location;
	
	public SelectionEvent(MapForegroundElement source, Location location) {
		super(source);
		this.location = location;
	}

	public Location getLocation() {
		return this.location;
	}

}
