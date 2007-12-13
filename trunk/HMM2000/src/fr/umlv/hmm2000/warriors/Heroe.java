package fr.umlv.hmm2000.warriors;

import java.util.ArrayList;
import java.util.HashMap;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.war.BattlePositionManager;
import fr.umlv.hmm2000.warriors.elements.Element;
import fr.umlv.hmm2000.warriors.elements.ElementEnum;
import fr.umlv.hmm2000.warriors.exception.MaxNumberOfTroopsReachedException;

public class Heroe extends Warrior implements Container {

	private final ArrayList<Warrior> troop = new ArrayList<Warrior>(MAX_TROOP_SIZE);

	public static final int MAX_TROOP_SIZE = 12;

	private final String name;

	private final BattlePositionManager bpm = new BattlePositionManager(MAX_TROOP_SIZE
			/ BattlePositionManager.LINE_NUMBER);

	Heroe(Player player,
				double health,
				int speed,
				Sprite sprite,
				double defenseValue,
				double attackValue,
				HashMap<ElementEnum, Element> elements,
				String name) {

		super(player,
					health,
					speed,
					sprite,
					defenseValue,
					attackValue,
					elements);

		this.name = name;
	}

	@Override
	public ArrayList<Warrior> getTroop() {

		return this.troop;
	}

	@Override
	public void addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() >= MAX_TROOP_SIZE) {
			throw new MaxNumberOfTroopsReachedException("The max number of troops, a heroe can contain, is reached");
		}

		int stepCount = w.getSpeed();
		if (stepCount < super.getSpeed()) {
			super.setSpeed(stepCount);
		}
		this.troop.add(w);
	};

	@Override
	public void removeWarrior(Warrior w) {

		int speed = w.getSpeed();
		if (speed >= super.getSpeed()) {
			super.setSpeed(getMinSpeed());
		}
		this.troop.remove(w);
	};

	public int getMinSpeed() {

		int stepCount = this.troop.get(0)
															.getSpeed();
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

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);
	}

	@Override
	public BattlePositionManager getBattlePositionManager() {
	
		return this.bpm;
	}

}
