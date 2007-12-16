package fr.umlv.hmm2000.war;

import fr.umlv.hmm2000.warrior.Container;

public class BattleManager {
	
	private final BattleRoundManager roundManager;

	public BattleManager(Container c1, Container c2) {

		this.roundManager = new BattleRoundManager(c1, c2);
	}

}
