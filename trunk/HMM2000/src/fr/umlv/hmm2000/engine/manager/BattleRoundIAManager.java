package fr.umlv.hmm2000.engine.manager;

import java.util.ArrayList;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.exception.WarriorDeadException;
import fr.umlv.hmm2000.warrior.exception.WarriorNotReachableException;

public class BattleRoundIAManager {

	private final ArrayList<Fightable> iaTroop;

	private final ArrayList<Fightable> troop;

	public BattleRoundIAManager(ArrayList<Fightable> iaTroop,
															ArrayList<Fightable> troop) {

		this.iaTroop = iaTroop;
		this.troop = troop;
	}

	public void performRound() {

		final Fightable bestAttacker = this.getBestWarrior(this.iaTroop);
		final Fightable defender = this.getAttackableWarrior(bestAttacker, this.troop);
		if (bestAttacker != null && defender != null) {
			try {
				System.out.println("IA attaque");
				CoreEngine.battleManager().roundManager()
						.tagAsAlreadyPlayed(bestAttacker);
				bestAttacker.performAttack(defender);
//				CoreEngine.battleManager().roundManager().nextDay();
			}
			catch (WarriorDeadException e) {
				CoreEngine.battleManager().kill(
						CoreEngine.map().getLocationForMapForegroundElement(defender),
						defender);
			}
			catch (WarriorNotReachableException e) {
				new AssertionError(e);
			}
		}
		
	}

	private Fightable getBestWarrior(ArrayList<Fightable> warriors) {

		if (warriors.size() != 0) {
		// warrior with maximum physical attack value
			Fightable bestWarrior = warriors.get(0);
			for (Fightable warrior : warriors) {
				if (warrior.getPhysicalAttackValue() > bestWarrior
						.getPhysicalAttackValue()) {
					bestWarrior = warrior;
				}
			}
			return bestWarrior;
		}
		return null;
	}

	private Fightable getAttackableWarrior(Fightable warrior,
			ArrayList<Fightable> warriors) {

		if (warriors.size() != 0) {
			// list of attackable warriors
			ArrayList<Fightable> attackables = new ArrayList<Fightable>();
			for (Fightable w : warriors) {
				if (warrior.isAttackable(w)) {
					attackables.add(w);
				}
			}
			// warrior with lower health
			Fightable lowWarrior = attackables.get(0);
			for (Fightable w : attackables) {
				if (w.getCurrentHealth() < lowWarrior.getCurrentHealth()) {
					lowWarrior = w;
				}
			}
			return lowWarrior;
		}
		return null;
	}

}
