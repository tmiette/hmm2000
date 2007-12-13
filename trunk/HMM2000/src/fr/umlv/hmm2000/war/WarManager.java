package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.warriors.Container;


public class WarManager {

	private WarMapManager warMap;
	
	private Container attacker;
	
	private Container defenser;
	
	public WarManager(Container attacker, Container defenser) {

		this.warMap = new WarMapManager(attacker.getTroop(), defenser.getTroop());
		this.attacker = attacker;
		this.defenser = defenser;
	}
	
	public void perform() {

		
	}
}
