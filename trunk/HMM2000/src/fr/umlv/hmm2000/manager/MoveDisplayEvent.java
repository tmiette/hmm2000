package fr.umlv.hmm2000.manager;

import java.util.List;

import fr.umlv.hmm2000.Event;
import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.map.Location;

public class MoveDisplayEvent extends Event {

	private final MovableElement source;
	
	private final List<Location> locations;

	public MoveDisplayEvent(MovableElement source, List<Location> locations) {
		super(source);
		this.source = source;
		this.locations = locations;
	}

	public List<Location> getLocations() {
		return locations;
	}
	
	@Override
	public MovableElement getSource() {
		return this.source;
	}
	
}
