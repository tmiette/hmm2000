package fr.umlv.hmm2000;

import fr.umlv.hmm2000.map.MapForegroundElement;

public abstract class MovableElement implements MapForegroundElement, Movable {

	private final Player player;
	
	public MovableElement(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public abstract int getStepCount();
	
}
