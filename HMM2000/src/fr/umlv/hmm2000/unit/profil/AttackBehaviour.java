package fr.umlv.hmm2000.unit.profil;

import fr.umlv.hmm2000.unit.Fightable;

/**
 * This interface defines attack behaviour for fightable units
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public interface AttackBehaviour {

	/**
	 * Defines if a given attacker fightable can attack the given defender
	 * fightable
	 * 
	 * @param attacker
	 * @param defender
	 * @return if attacker can attack defender
	 */
	public boolean isAttackable(Fightable attacker, Fightable defender);

}
