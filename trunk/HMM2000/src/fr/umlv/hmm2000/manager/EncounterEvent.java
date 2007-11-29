package fr.umlv.hmm2000.manager;

import fr.umlv.hmm2000.MovableElement;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MapForegroundElement;

public class EncounterEvent {

	private final MovableElement sender;

	private final MapForegroundElement recipient;

	private final Location location;
	
	public EncounterEvent(MovableElement sender, MapForegroundElement recipient, Location location) {
		this.sender = sender;
		this.recipient = recipient;
		this.location = location;
	}

	public MapForegroundElement getRecipient() {
		return this.recipient;
	}

	public MovableElement getSender() {
		return this.sender;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
}
