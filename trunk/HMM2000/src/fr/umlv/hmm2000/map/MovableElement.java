package fr.umlv.hmm2000.map;

import fr.umlv.hmm2000.Player;

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
