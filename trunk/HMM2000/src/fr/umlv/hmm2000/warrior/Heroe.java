package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Heroe extends Warrior implements Container {

	private final ArrayList<Warrior> troop = new ArrayList<Warrior>(MAX_TROOP_SIZE);

	public static final int MAX_TROOP_SIZE = 12;

	private final String name;

	private final BattlePositionMap bpm = new BattlePositionMap(MAX_TROOP_SIZE
			/ BattlePositionMap.LINE_NUMBER);

	Heroe(Player player,
				ProfilWarrior profil,
				String name) {

		super(player,
					profil);

		this.name = name;
	}

	@Override
	public ArrayList<Warrior> getTroop() {

		return this.troop;
	}

	@Override
	public boolean addWarrior(Warrior w) throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() >= MAX_TROOP_SIZE) {
			throw new MaxNumberOfTroopsReachedException("The max number of troops, a heroe can contain, is reached");
		}

		int speed = w.getSpeed();
		if (speed < super.getSpeed()) {
			super.setSpeed(speed);
		}

		try {
			this.bpm.placeWarrior(w,
														this.bpm.getFirstFreeLocation());
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
		catch (LocationAlreadyOccupedException e) {
			return false;
		}
		catch (NoPlaceAvailableException e) {
			return false;
		}
		this.troop.add(w);
		w.setContainer(this);
		return true;
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

		if (this.troop.size() == 0) {
			return 0;
		}
		
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

	public String getProfilName() {

		return this.name;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);
	}

	@Override
	public BattlePositionMap getBattlePositionManager() {

		return this.bpm;
	}

	public String getName() {

		return this.name;
	}
	
	@Override
	public boolean encounter(EncounterEvent event) {
	
		CoreEngine.startBattle((Container)event.getSender(), (Container)event.getRecipient());
		return false;
	}

}
