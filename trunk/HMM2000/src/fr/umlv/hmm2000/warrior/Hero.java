package fr.umlv.hmm2000.warrior;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public class Hero extends MovableElement {

	private final ArrayList<Fightable> troop;

	private final String name;

	private final BattlePositionMap battlePosition;

	private double stepCount;

	private int speed;

	private Sprite sprite;

	Hero(	Player player,
				Sprite sprite,
				String name) {

		super(player);
		this.name = name;
		this.battlePosition = new BattlePositionMap(
				FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
		this.sprite = sprite;
		this.troop = new ArrayList<Fightable>();
	}

	@Override
	public boolean addFightable(Fightable f)
			throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() == FightableContainer.MAX_TROOP_SIZE) {
			throw new MaxNumberOfTroopsReachedException(
					"The max number of troops, a heroe can contain, is reached");
		}

		final int speed = f.getSpeed();
		if (speed < this.speed || this.speed == 0) {
			this.speed = speed;
			this.stepCount = speed;
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
			int speed = this.troop.get(index).getSpeed();
			this.troop.remove(index);
			if (this.speed == speed) {
				this.speed = 0;
				for (Fightable fightable : this.troop) {
					if (fightable.getSpeed() < this.speed || this.speed == 0) {
						this.speed = fightable.getSpeed();
						this.stepCount = fightable.getSpeed();
					}
				}
			}
		}
	}

	@Override
	public double getStepCount() {

		return this.stepCount;
	}

	@Override
	public void setStepCount(double stepCount) {

		this.stepCount = stepCount >= 0 ? stepCount : 0;
	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);

	}

	@Override
	public boolean encounter(EncounterEvent event) {

		MapForegroundElement sender = event.getSender();
		MapForegroundElement recipient = event.getRecipient();
		if (sender instanceof FightableContainer && recipient instanceof FightableContainer) {
			FightableContainer s = (FightableContainer)sender;
			FightableContainer r = (FightableContainer)recipient;
			
			if (!s.getPlayer().equals(r.getPlayer())) {
				CoreEngine.startBattle(s, r);
			}
			return false;
		}
		
		return false;
	}

	@Override
	public void nextDay(int day) {

		this.stepCount = this.speed;
	}

	@Override
	public Sprite getSprite() {

		return this.sprite;
	}

	public String getName() {

		return this.name;
	}

}
