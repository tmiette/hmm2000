package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;


public class Monster extends MovableElement {
	
	private final Sprite sprite;
	
	private final ArrayList<Fightable> troop;

	private final BattlePositionMap battlePosition;
	
	public Monster(Sprite sprite) {

		super(null);
		this.sprite = sprite;
		this.battlePosition = new BattlePositionMap(
				FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
		this.troop = new ArrayList<Fightable>();
	}

	@Override
	public String getName() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addFightable(Fightable f)
			throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() == FightableContainer.MAX_TROOP_SIZE) {
			throw new MaxNumberOfTroopsReachedException(
					"The max number of troops, a heroe can contain, is reached");
		}

		try {
			this.battlePosition.placeFightable(f, this.battlePosition
					.getFirstFreeLocation());
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
		this.troop.add(f);
		f.setFightableContainer(this);
		return true;
	}


	@Override
	public BattlePositionMap getBattlePositionManager() {

		return this.battlePosition;
	}

	@Override
	public List<Fightable> getTroop() {

		return this.troop;
	}

	@Override
	public void removeFightable(Fightable f) {

		int index;
		if ((index = this.troop.indexOf(f)) != -1) {
			this.troop.remove(index);
		}
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		//TODO visitor.visit(this);
		
	}

	@Override
	public boolean encounter(EncounterEvent event) {

		CoreEngine.startBattle((FightableContainer) event.getSender(), (FightableContainer) event
				.getRecipient());
		return false;
	}

	@Override
	public void nextDay(int day) {

		//do nothing
		
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	@Override
	public double getStepCount() {

		return 0;
	}

	@Override
	public void setStepCount(double stepCount) {

		//do nothing
		
	}

}
