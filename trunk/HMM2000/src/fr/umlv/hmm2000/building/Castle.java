package fr.umlv.hmm2000.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.battle.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.map.battle.NoPlaceAvailableException;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profil.Level;
import fr.umlv.hmm2000.unit.profil.ProfilWarrior;

/**
 * This class represents a castle and its factories. It can produce figthable
 * thank to its factories, it can be attacked by fightable container and produce
 * heroes.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Castle implements FightableContainer {

	// Owner player
	private final Player player;

	// Troop created by castle and factories
	private final ArrayList<Fightable> troop;

	// Heroe created by castle
	private final ArrayList<Hero> heroes;

	// Troops layout on battle map
	private final BattlePositionMap battlePosition;

	// Factories built in the castle
	private final HashMap<ProfilWarrior, Level> factory;

	// Actions permitted by castle
	private ArrayList<CastleItem> items;

	// Default castle level
	public final static Level defaultLevel = Level.LEVEL_1;

	// Default warrior a castle can create
	public final static ProfilWarrior defaultWarrior = ProfilWarrior.GRUNT;

	public Castle(Player player) {

		this.player = player;
		this.troop = new ArrayList<Fightable>(FightableContainer.MAX_TROOP_SIZE);
		this.heroes = new ArrayList<Hero>();
		this.battlePosition = new BattlePositionMap(
				FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
		this.factory = new HashMap<ProfilWarrior, Level>();
		this.factory.put(defaultWarrior, defaultLevel);
		try {
			// Adding default unit
			this
					.addFightable(UnitFactory.createWarrior(defaultWarrior, defaultLevel));
		}
		catch (MaxNumberOfTroopsReachedException e) {
			new AssertionError(e);
		}
	}

	/**
	 * Gets a hero out of castle. This method returns null if hero isnt contained
	 * 
	 * @param hero
	 *          to take
	 * @return
	 */
	public Hero removeHero(Hero hero) {

		int index = this.heroes.indexOf(hero);
		if (index != -1) {
			Hero h = this.heroes.get(index);
			this.heroes.remove(index);
			return h;
		}
		return null;
	}

	/**
	 * Returns if castle can produce warrior thanks to its factories
	 * 
	 * @param profil
	 *          warrior type to produce
	 * @return possibity to produce
	 */
	public boolean canProduceWarrior(ProfilWarrior profil) {

		return this.factory.containsKey(profil);
	}

	/**
	 * Adds a new factory in castle
	 * 
	 * @param profil
	 *          factory type
	 */
	public void buildFactory(ProfilWarrior profil) {

		if (!this.factory.containsKey(profil)) {
			this.factory.put(profil, defaultLevel);
		}
	}

	/**
	 * Gets all factories contained in castle
	 * 
	 * @return set of warrior profil
	 */
	public Set<ProfilWarrior> getFactoryBuilt() {

		return this.factory.keySet();
	}
	
	/**
	 * Gets next possible level for a given factory
	 * @param profil factory
	 * @return next level
	 */
	public Level getNextFactoryLevel(ProfilWarrior profil) {

		return this.factory.get(profil).getNextLevel();
	}
	
	/**
	 * Upgrade level of an existing factory
	 * 
	 * @param profil
	 *          warrior profil
	 */
	public boolean upgradeFactory(ProfilWarrior profil) {

		if (this.factory.containsKey(profil)) {
			// Old level
			Level level = this.factory.get(profil);
			this.factory.remove(profil);
			// New level
			Level nextLevel = level.getNextLevel();
			this.factory.put(profil, nextLevel == null ? level : nextLevel);
			
			return true;
		}
		return false;
	}

	/**
	 * Produce a warrior in castle if it contains the specific factory
	 * 
	 * @param profil
	 *          warrior type to produce
	 * @return creation possibility
	 * @throws MaxNumberOfTroopsReachedException
	 */
	public boolean createWarrior(ProfilWarrior profil)
			throws MaxNumberOfTroopsReachedException {

		if (this.canProduceWarrior(profil)) {
			Level level = this.factory.get(profil);
			this.addFightable(UnitFactory.createWarrior(profil, level));
			return true;
		}
		return false;
	}

	@Override
	public boolean addFightable(Fightable f)
			throws MaxNumberOfTroopsReachedException {

		if (this.troop.size() == FightableContainer.MAX_TROOP_SIZE) {
			throw new MaxNumberOfTroopsReachedException(
					"The max number of troops, a heroe can contain, is reached");
		}

		try {
			// Placing unit on map
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
		// Adding unit to castle troop
		this.troop.add(f);
		f.setFightableContainer(this);
		return true;
	}

	/**
	 * Adds a hero in castle
	 * 
	 * @param hero
	 *          to add
	 * @return adding state
	 */
	public boolean addHero(Hero hero) {

		this.heroes.add(hero);
		return true;
	}

	/**
	 * Returns the level of given factory
	 * 
	 * @param pw
	 *          factory type
	 * @return specific level factory
	 */
	public Level getFactoryLevel(ProfilWarrior pw) {

		return this.factory.get(pw);
	}

	/**
	 * Gets castle actions available
	 * 
	 * @return actions list
	 */
	public List<CastleItem> getItems() {

		if (this.items == null) {
			this.items = new ArrayList<CastleItem>();
			items.add(CastleItem.defaultItem);
			items.add(new HeroRecruitmentItem(this));
			items.add(new WarriorRecruitmentItem(this));
			items.add(new UpgradeCastleItem(this));
			items.add(new UpgradeFactoryItem(this));
			items.add(new ExitHeroItem(this));
			items.add(new CreateNewFactoryItem(this));
		}
		return this.items;
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

	/**
	 * Gets heroes list contained in castle
	 * 
	 * @return heroes list
	 */
	public List<Hero> getHeroes() {

		return this.heroes;
	}

	@Override
	public void removeFightable(Fightable f) {

		int index;
		if ((index = this.troop.indexOf(f)) != -1) {
			this.troop.remove(index);
			this.battlePosition.removeMapForegroundElement(this.battlePosition
					.getLocationForMapForegroundElement(f));
		}
	}

	@Override
	public void setPlayer(Player player) {

		// do nothing

	}

	@Override
	public void accept(UIDisplayingVisitor visitor) {

		visitor.visit(this);
	}

	@Override
	public boolean encounter(Encounter encounter) {

		if (!encounter.getSender().getPlayer().equals(this.getPlayer())) {
			CoreEngine.startBattle(encounter.getSender(), this);
		}
		else {
			CoreEngine.startSwap(this, encounter.getSender());
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

	/**
	 * Gets level and factory corresponding
	 * 
	 * @return
	 */
	public HashMap<ProfilWarrior, Level> getFactory() {

		return this.factory;
	}

	@Override
	public int getAttackPriority() {

		return FightableContainer.PRIORITY_HIGH;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Castle ");
		sb.append("(player, ");
		sb.append(this.player);
		sb.append(")");
		sb.append("(troop, ");
		for (Fightable warrior : this.troop) {
			sb.append(warrior);
			sb.append(" - ");
		}
		sb.append(")");
		sb.append("(Factories, ");
		for (Entry<ProfilWarrior, Level> entries : this.factory.entrySet()) {
			sb.append("[").append(entries.getKey()).append(",").append(
					entries.getValue()).append("]");
		}
		sb.append(")");

		return sb.toString();
	}
}
