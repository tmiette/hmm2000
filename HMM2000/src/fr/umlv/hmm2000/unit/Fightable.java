package fr.umlv.hmm2000.unit;

import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.salesentity.Sellable;
import fr.umlv.hmm2000.unit.exception.WarriorDeadException;
import fr.umlv.hmm2000.unit.exception.WarriorNotReachableException;
import fr.umlv.hmm2000.unit.profile.AttackBehaviour;
import fr.umlv.hmm2000.unit.profile.ElementAbility;

/**
 * This interface defines behaviours of an unit which can attack and die during
 * a war. It contains methods to hurt and be hurted during battle. A fightable
 * must me contained by a fightable container
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface Fightable extends Sellable, MapForegroundElement {

	/**
	 * Gets specific physical attack value this fightable can do
	 * 
	 * @return physical attack value
	 */
	public double getPhysicalAttackValue();

	/**
	 * Sets specific physical attack value
	 * 
	 * @param attackValue
	 */
	public void setPhysicalAttackValue(double attackValue);

	/**
	 * Sets specific physical defense value
	 * 
	 * @param defenseValue
	 */
	public void setPhysicalDefenseValue(double defenseValue);

	/**
	 * Gets container which own this fightable
	 * 
	 * @return container
	 */
	public FightableContainer getFightableContainer();

	/**
	 * Gets specific physical defense value this fightable can protect him
	 * 
	 * @return physical defense value
	 */
	public double getPhysicalDefenseValue();

	/**
	 * Gets starting maximum health
	 * 
	 * @return health value
	 */
	public double getHealth();

	/**
	 * Gets current health
	 * 
	 * @return health value
	 */
	public double getCurrentHealth();

	/**
	 * Specify behaviour when this fightable attacks an other one
	 * 
	 * @param defender
	 *          fightable to attack
	 * @throws WarriorDeadException
	 *           behaviour when defender is dead
	 * @throws WarriorNotReachableException
	 *           behaviour if defender is not attackable
	 */
	public void performAttack(Fightable defender) throws WarriorDeadException,
			WarriorNotReachableException;

	/**
	 * Defines container of this fightable
	 * 
	 * @param container
	 */
	public void setFightableContainer(FightableContainer container);

	/**
	 * Takes damage from damage value
	 * 
	 * @param damage
	 * @throws WarriorDeadException
	 *           behaviour when this fightable is dead
	 */
	public void hurt(double damage) throws WarriorDeadException;

	/**
	 * Gets specific fightable id
	 * 
	 * @return id value
	 */
	public double getId();

	/**
	 * Gets fightable speed on world map
	 * 
	 * @return speed value
	 */
	public int getSpeed();

	/**
	 * Gets specific attack behaviour.
	 * 
	 * @return attack behaviour
	 */
	public AttackBehaviour getAttackBehaviour();

	/**
	 * Gets elementary abilities
	 * 
	 * @return elementary abilities
	 */
	public ElementAbility getAbilities();

	/**
	 * Returns if this fightable can attack the defender
	 * 
	 * @param defender
	 *          to attack
	 * @return if this fightable can attack
	 */
	public boolean isAttackable(Fightable defender);

}