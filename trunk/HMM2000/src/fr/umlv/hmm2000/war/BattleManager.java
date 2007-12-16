package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.warrior.Container;

public class BattleManager {

	private final BattleRoundManager roundManager;

	private final BattleMap map;

	public BattleManager(	Container c1,
												Container c2) {

		this.map = null; //TODO creer map
		this.roundManager = new BattleRoundManager(	c1,
																								c2);
	}

	public void perform(Location l) {
		
	}
}
