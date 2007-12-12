package fr.umlv.hmm2000.warriors;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.warriors.elements.Element;
import fr.umlv.hmm2000.warriors.exceptions.MaxNumberOfTroopsReachedException;

public class Heroe extends Warrior implements Container {

	private final ArrayList<Warrior> troop = new ArrayList<Warrior>(MAX_TROOP_SIZE);

	public static final int MAX_TROOP_SIZE = 12;

	private final String name;

	Heroe(Player player, double health, int speed,
			ArrayList<Element> listOfAttacks, Container container, Sprite sprite,
			double defenseValue, double attackValue, String name, ArrayList<Warrior> troop) {

		super(player, health, speed, listOfAttacks, container, sprite,
				defenseValue, attackValue);
		
		try {
			for (Warrior warrior : troop) {
				this.addWarrior(warrior);
			}
		}
		catch (MaxNumberOfTroopsReachedException e) {
			//Nothing to do
		}
		this.name = name;
	}

	@Override
	public ArrayList<Warrior> getTroop() {

		return this.troop;
	}

	@Override
	public void addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() >= MAX_TROOP_SIZE) {
			throw new MaxNumberOfTroopsReachedException(
					"The max number of troops, a heroe can contain, is reached");
		}

		int stepCount = w.getSpeed();
		if (stepCount < super.getSpeed()) {
			super.setSpeed(stepCount);
		}
		this.troop.add(w);
	};
	
	public void addWarriors(ArrayList<Warrior> w) {

		
	}

	@Override
	public void removeWarrior(Warrior w) {

		int speed = w.getSpeed();
		if (speed >= super.getSpeed()) {
			super.setSpeed(getMinSpeed());
		}
		this.troop.remove(w);
	};

	public int getMinSpeed() {

		int stepCount = this.troop.get(0).getSpeed();
		for (Warrior wit : this.troop) {
			int sc;
			if ((sc = (wit.getSpeed())) < stepCount) {
				stepCount = sc;
			}
		}
		return stepCount;
	}

	public String getName() {

		return this.name;
	}

}
