package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.warrior.Warrior2;


public class BattleEvent {

	private final Warrior2 attacker;
	
	private final Warrior2 defenser;
	
	public BattleEvent(Warrior2 attacker, Warrior2 defenser) {

		this.attacker = attacker;
		this.defenser = defenser;
	}
	
	
	
}
