package fr.umlv.hmm2000.building;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.event.EncounterEvent;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.map.element.MapForegroundElement;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;

public class Castle implements FightableContainer {

	private final Player player;

	private final ArrayList<Fightable> troop;

	private final ArrayList<Hero> heroes;

	private final BattlePositionMap battlePosition;

	public Castle(Player player) {

		this.player = player;
		this.troop = new ArrayList<Fightable>(FightableContainer.MAX_TROOP_SIZE);
		this.heroes = new ArrayList<Hero>();
		this.battlePosition = new BattlePositionMap(
				FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
	}

	@Override
	public boolean addFightable(Fightable f)
			throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() < FightableContainer.MAX_TROOP_SIZE) {
			f.setFightableContainer(this);
			this.troop.add(f);
			return true;
		}
		else {
			throw new MaxNumberOfTroopsReachedException(
					"Too much warriors in the castle");
		}
	}

	public boolean addHero(Hero hero) {

		this.heroes.add(hero);
		return true;
	}

	@Override
	public BattlePositionMap getBattlePositionManager() {

		return this.battlePosition;
	}

	@Override
	public Player getPlayer() {

		return this.player;
	}

	@Override
	public List<Fightable> getTroop() {

		return this.troop;
	}

	public List<Hero> getHeroes() {

		return this.heroes;
	}

	@Override
	public void removeFightable(Fightable f) {

		this.troop.remove(f);

	}

	@Override
	public void setPlayer(Player player) {

		// do nothing

	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		// TODO Auto-generated method stub

	}

	@Override
	public boolean encounter(EncounterEvent event) {

		MapForegroundElement sender = event.getSender();
		MapForegroundElement recipient = event.getRecipient();

		if (sender instanceof FightableContainer
				&& recipient instanceof FightableContainer) {
			FightableContainer s = (FightableContainer) sender;
			FightableContainer r = (FightableContainer) recipient;

			if (!s.getPlayer().equals(r.getPlayer())) {
				CoreEngine.startBattle(s, r);
				return true;
			}
			else {
				//TODO start recrutement
				return true;
			}
		}
		return false;
	}

	@Override
	public void nextDay(int day) {

		// do nothing

	}

	@Override
	public Sprite getSprite() {

		return Sprite.CASTLE;
	}

}
