package fr.umlv.hmm2000.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.building.item.CastleItem;
import fr.umlv.hmm2000.building.item.CreateNewFactoryItem;
import fr.umlv.hmm2000.building.item.ExitHeroItem;
import fr.umlv.hmm2000.building.item.HeroRecruitmentItem;
import fr.umlv.hmm2000.building.item.UpgradeCastleItem;
import fr.umlv.hmm2000.building.item.UpgradeFactoryItem;
import fr.umlv.hmm2000.building.item.WarriorRecruitmentItem;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.battle.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.map.battle.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.map.builder.MapForegroundElementSaver;
import fr.umlv.hmm2000.unit.Fightable;
import fr.umlv.hmm2000.unit.FightableContainer;
import fr.umlv.hmm2000.unit.Hero;
import fr.umlv.hmm2000.unit.UnitFactory;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.profile.Level;
import fr.umlv.hmm2000.unit.profile.WarriorProfile;

/**
 * This class represents a castle and its factories. It can produce figthable
 * thank to its factories, it can be attacked by fightable container and produce
 * heroes. Factory can create a fightable thanks to it type.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Castle implements FightableContainer {

  // Owner player
  private Player player;

  // Troop created by castle and factories
  private final ArrayList<Fightable> troop;

  // Heroe created by castle
  private final ArrayList<Hero> heroes;

  // Troops layout on battle map
  private final BattlePositionMap battlePosition;

  // Factories built in the castle
  private final HashMap<WarriorProfile, Level> factory;

  // Actions permitted by castle
  private ArrayList<CastleItem> items;

  // Default castle level
  public final static Level defaultLevel = Level.LEVEL_1;

  // Default warrior a castle can create
  private final WarriorProfile defaultFactory;

  // Days corresponding to the factory buiding
  private final HashMap<WarriorProfile, Integer> timeToBuildFactory;

  // Default days number to build factory
  public static final Integer defaultTimeToBuildFactory = 3;

  public Castle(Player player, WarriorProfile profile) {

    this.player = player;
    this.defaultFactory = profile;
    this.troop = new ArrayList<Fightable>(FightableContainer.MAX_TROOP_SIZE);
    this.heroes = new ArrayList<Hero>();
    this.battlePosition = new BattlePositionMap(
        FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
    this.factory = new HashMap<WarriorProfile, Level>();
    this.factory.put(this.defaultFactory, defaultLevel);
    try {
      // Adding default unit
      this.addFightable(UnitFactory.createWarrior(this.defaultFactory,
          defaultLevel));
    } catch (MaxNumberOfTroopsReachedException e) {
      new AssertionError(e);
    }
    this.timeToBuildFactory = new HashMap<WarriorProfile, Integer>(
        WarriorProfile.values().length);
  }

  /**
   * Gets a hero out of castle. This method returns null if hero isn't
   * contained.
   * 
   * @param hero
   *            hero to take.
   * @return the hero.
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
   * @param profile
   *            warrior type to produce
   * @return possibity to produce
   */
  public boolean canProduceWarrior(WarriorProfile profile) {

    return this.factory.containsKey(profile);
  }

  /**
   * Adds a new factory in castle
   * 
   * @param profile
   *            factory type
   */
  public void buildFactory(WarriorProfile profile) {

    if (!this.factory.containsKey(profile)) {
      this.factory.put(profile, defaultLevel);
    }
  }

  /**
   * Adds a new factory in castle after few days. Days value is qualified by
   * defaultTimeToBuildFactory constant.
   * 
   * @param profile
   *            factory to buy
   */
  public void buyFactory(WarriorProfile profile) {

    if (!this.timeToBuildFactory.containsKey(profile)) {
      this.timeToBuildFactory.put(profile, defaultTimeToBuildFactory);
    }
  }

  /**
   * Gets all factories contained in castle
   * 
   * @return set of warrior profile
   */
  public Set<WarriorProfile> getFactoryBuilt() {

    return this.factory.keySet();
  }

  /**
   * Gets next possible level for a given factory
   * 
   * @param profile
   *            factory
   * @return next level
   */
  public Level getNextFactoryLevel(WarriorProfile profile) {

    Level level = this.factory.get(profile);
    if (level != null) {
      level = level.getNextLevel();
    }
    return level;
  }

  /**
   * Upgrade level of an existing factory
   * 
   * @param profile
   *            warrior profile
   */
  public boolean upgradeFactory(WarriorProfile profile) {

    if (this.factory.containsKey(profile)) {
      // Old level
      Level level = this.factory.get(profile);
      this.factory.remove(profile);
      // New level
      Level nextLevel = level.getNextLevel();
      this.factory.put(profile, nextLevel == null ? level : nextLevel);

      return true;
    }
    return false;
  }

  /**
   * Produce a warrior in castle if it contains the specific factory
   * 
   * @param profile
   *            warrior type to produce
   * @return creation possibility
   * @throws MaxNumberOfTroopsReachedException
   */
  public boolean createWarrior(WarriorProfile profile)
      throws MaxNumberOfTroopsReachedException {

    if (this.canProduceWarrior(profile)) {
      Level level = this.factory.get(profile);
      this.addFightable(UnitFactory.createWarrior(profile, level));
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
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } catch (LocationAlreadyOccupedException e) {
      return false;
    } catch (NoPlaceAvailableException e) {
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
   *            to add
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
   *            factory type
   * @return specific level factory
   */
  public Level getFactoryLevel(WarriorProfile pw) {

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
      if (this.getNextFactoryLevel(this.getDefaultFactory()) != null) {
      	items.add(new UpgradeCastleItem(this));
			}
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

    this.player = player;

  }

  @Override
  public void accept(UIDisplayingVisitor visitor) {

    visitor.visit(this);
  }

  @Override
  public boolean encounter(Encounter encounter) {

    if (!encounter.getSender().getPlayer().equals(this.getPlayer())) {
      if (this.heroes.size() > 0) {
        Hero hero = this.chooseHero(this.heroes);
        this.unitTransfert(this, hero);
        CoreEngine.startBattle(encounter.getSender(), hero);
      } else {
        CoreEngine.startBattle(encounter.getSender(), this);
      }

    } else {
      CoreEngine.startSwap(this, encounter.getSender());
    }
    return false;
  }

  /**
   * Chooses hero to protect castle during battle.
   * 
   * @param heroes
   *            castle heroes
   * @return best hero defender
   */
  private Hero chooseHero(ArrayList<Hero> heroes) {

    return heroes.size() > 0 ? heroes.get(0) : null;
  }

  /**
   * Transfer units from fightable container from to fightable container to.
   * Leaves one unit in the source container.
   * 
   * @param from
   *            source container
   * @param to
   *            destination container
   */
  private void unitTransfert(FightableContainer from, FightableContainer to) {

    List<Fightable> list = new ArrayList<Fightable>();
    list.addAll(from.getTroop());
    for (int i = 0; i < list.size() - 1; i++) {
      try {
        Fightable f = list.get(i);
        to.addFightable(f);
        this.removeFightable(f);
      } catch (MaxNumberOfTroopsReachedException e) {
        return;
      }
    }
  }

  @Override
  public void nextDay(int day) {

    // Looking for all factories building pending
    for (Entry<WarriorProfile, Integer> entries : this.timeToBuildFactory
        .entrySet()) {
      Integer days = entries.getValue();
      WarriorProfile profile = entries.getKey();
      days -= day;
      if (days <= 0) {
        // Starting factory building
        this.buildFactory(profile);
        // Removing pending factory building
        this.timeToBuildFactory.remove(profile);
        CoreEngine.fireMessage(profile
            + " factory is ready to build new warrior.",
            HMMUserInterface.INFO_MESSAGE);
      } else {
        // Refreshing building deadline
        this.timeToBuildFactory.put(profile, days);
      }
    }

  }

  @Override
  public Sprite getSprite() {

    return Sprite.CASTLE;
  }

  /**
   * Gets level and factory corresponding.
   * 
   * @return the level and factory corresponding.
   */
  public HashMap<WarriorProfile, Level> getFactory() {

    return this.factory;
  }

  @Override
  public int getAttackPriority() {

    return FightableContainer.PRIORITY_HIGH;
  }

  @Override
  public String toString() {

    return "CASTLE";
  }

  /**
   * Gets default factory containing in castle.
   * 
   * @return default factory.
   */
  public WarriorProfile getDefaultFactory() {

    return this.defaultFactory;
  }

  @Override
  public String getName() {

    return "CASTLE";
  }

  @Override
  public String getSaveString() {

    return MapForegroundElementSaver.save(this);
  }
}
