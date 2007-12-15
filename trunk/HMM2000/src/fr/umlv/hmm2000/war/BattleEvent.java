package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.warrior.Warrior;


public class BattleEvent {

	private final Warrior attacker;
	
	private final Warrior defenser;
	
	public BattleEvent(Warrior attacker, Warrior defenser) {

		this.attacker = attacker;
		this.defenser = defenser;
	}
	
	
	
}
