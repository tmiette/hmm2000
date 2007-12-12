package fr.umlv.hmm2000.engine.event;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MapForegroundElement;
import fr.umlv.hmm2000.map.MovableElement;

public class EncounterEvent {

	private final MovableElement sender;

	private final MapForegroundElement recipient;

	private final Location senderLocation;

	private final Location recipientLocation;

	public EncounterEvent(MovableElement sender,
			MapForegroundElement recipient, Location senderLocation,
			Location recipientLocation) {
		this.sender = sender;
		this.recipient = recipient;
		this.senderLocation = senderLocation;
		this.recipientLocation = recipientLocation;
	}

	public MapForegroundElement getRecipient() {
		return this.recipient;
	}

	public MovableElement getSender() {
		return this.sender;
	}
	
	public Location getSenderLocation() {
		return this.senderLocation;
	}

	public Location getRecipientLocation() {
		return this.recipientLocation;
	}

}
