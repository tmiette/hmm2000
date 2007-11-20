package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.Event;
import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.map.Location;

public class MoveEvent extends Event {

	private final MovableElement source;
	
	private final Location start;

	private final Location end;

	public MoveEvent(MovableElement source, Location start, Location end) {
		super(source);
		this.source = source;
		this.start = start;
		this.end = end;
	}
	
	public Location getStart() {
		return this.start;
	}
	
	public Location getEnd() {
		return this.end;
	}
	
	@Override
	public MovableElement getSource() {
		return this.source;
	}

}
