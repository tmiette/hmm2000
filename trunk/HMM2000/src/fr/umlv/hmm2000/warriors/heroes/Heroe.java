package fr.umlv.hmm2000.warriors.heroes;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.warriors.Warrior;
import fr.umlv.hmm2000.warriors.heroes.exceptions.maxNumberOfTroopsReached;

public abstract class Heroe extends Warrior {

	private final ArrayList<Warrior> troop;
	
	public static final int MAX_TROOP_SIZE = 12;

	public Heroe(Player player) {

		super(player);
		this.troop = new ArrayList<Warrior>(MAX_TROOP_SIZE);
	}

	public void addWarrior(Warrior w) throws maxNumberOfTroopsReached {

		if (this.troop.size() >= MAX_TROOP_SIZE) {
			throw new maxNumberOfTroopsReached("The max number of troops, a heroe can contain, is reached");
		}
		
		int stepCount = w.getStepCount();
		if (stepCount < super.getStepCount()) {
			super.setStepCount(stepCount);
		}
		this.troop.add(w);
	};

	public void removeWarrior(Warrior w) {

		int stepCount = w.getStepCount();
		if (stepCount >= super.getStepCount()) {
			super.setStepCount(getMinStepCount());
		}
		this.troop.remove(w);
	};
	
	public int getMinStepCount() {

		int stepCount = this.troop.get(0).getStepCount();
		for (Warrior wit : this.troop) {
			int sc;
			if ((sc = (wit.getStepCount())) < stepCount) {
				stepCount = sc;
			}
		}
		return stepCount;
	}

}
