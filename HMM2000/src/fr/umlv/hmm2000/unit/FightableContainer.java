package fr.umlv.hmm2000.unit;

import java.util.List;

import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;

/**
 * This class defines a fightable container. It can be attacked by an other
 * fightable container and it can attack thanks to its troop (fightable).
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface FightableContainer extends MapForegroundElement {

	// attack priorities constants
	public static final int PRIORITY_HIGH = 8;

	public static final int PRIORITY_MEDIUM = 4;

	public static final int PRIORITY_LOW = 2;

	public static final int PRIORITY_VERY_LOW = 1;

	public static final int MAX_TROOP_SIZE = 12;

	/**
	 * Gets troops contained by the fightable container.
	 * 
	 * @return troop
	 */
	public List<Fightable> getTroop();

	/**
	 * Adds an unit (fightable) to its troop
	 * 
	 * @param f
	 *          unit to add
	 * @return if unit can be added
	 * @throws MaxNumberOfTroopsReachedException
	 *           if troop size reached the max troop size value
	 */
	public boolean addFightable(Fightable f)
			throws MaxNumberOfTroopsReachedException;

	/**
	 * Removes an unit (fightable) from its troop
	 * 
	 * @param f
	 *          unit to remove
	 */
	public void removeFightable(Fightable f);

	/**
	 * Gets the specific battle position map
	 * 
	 * @return
	 */
	public BattlePositionMap getBattlePositionManager();

	/**
	 * Gets player owns the fightable container
	 * 
	 * @return
	 */
	public Player getPlayer();

	/**
	 * Sets the castle owner
	 * 
	 * @param player
	 *          owner
	 */
	public void setPlayer(Player player);

	/**
	 * Gets specific attack priority to choose who will start battle first
	 * 
	 * @return attack priority value
	 */
	public int getAttackPriority();

}
