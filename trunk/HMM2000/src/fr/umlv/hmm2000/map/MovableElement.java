package fr.umlv.hmm2000.map;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.unit.FightableContainer;

/**
 * All objets which can move in the game should extend this class. This class
 * defines the behaviours of an element which can move.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public abstract class MovableElement implements FightableContainer, Movable {

	// Player which owns element
	private Player player;

	public MovableElement(Player player) {

		this.player = player;
	}

	/**
	 * Gets player owner.
	 * @return player owner
	 */
	public Player getPlayer() {

		return this.player;
	}

	/**
	 * Sets the player owner.
	 */
	public void setPlayer(Player player) {

		this.player = player;
	}

	/**
	 * Gets element name.
	 * @return element name
	 */
	public abstract String getName();

}
