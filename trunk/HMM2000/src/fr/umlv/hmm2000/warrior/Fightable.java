package fr.umlv.hmm2000.warrior;

import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;

public interface Fightable extends MapForegroundElement {

	public abstract void setAttackValue(double attackValue);

	public abstract void setDefenseValue(double defenseValue);

	public abstract double getAttackValue();

	public abstract FightableContainer getFightableContainer();

	public abstract double getDefenseValue();

	public abstract double getHealth();

	public abstract void performAttack(Warrior defender)
			throws WarriorDeadException, WarriorNotReachableException;

	public abstract void setFightableContainer(FightableContainer container);

	public abstract void setHealth(double health) throws WarriorDeadException;

	public abstract double getId();
	
	public abstract int getSpeed();

}