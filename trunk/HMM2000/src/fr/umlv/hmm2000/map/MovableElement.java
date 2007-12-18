package fr.umlv.hmm2000.map;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warrior.FightableContainer;

public abstract class MovableElement implements FightableContainer, Movable {

	private Player player;
	
	public MovableElement(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void setPlayer(Player player){
	  this.player = player;
	}
	
	public abstract double getStepCount();
	
	public abstract void setStepCount(double stepCount);
}
