package fr.umlv.hmm2000.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.war.BattlePositionMap;
import fr.umlv.hmm2000.war.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.war.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.warrior.Fightable;
import fr.umlv.hmm2000.warrior.FightableContainer;
import fr.umlv.hmm2000.warrior.Hero;
import fr.umlv.hmm2000.warrior.UnitFactory;
import fr.umlv.hmm2000.warrior.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.warrior.profil.Level;
import fr.umlv.hmm2000.warrior.profil.ProfilWarrior;

public class Castle implements FightableContainer {

  private final Player player;

  private final ArrayList<Fightable> troop;

  private final ArrayList<Hero> heroes;

  private final BattlePositionMap battlePosition;

  private final HashMap<ProfilWarrior, Level> factory;

  private ArrayList<CastleItem> items;

  public final static Level defaultLevel = Level.LEVEL_1;
  public final static ProfilWarrior defaultWarrior = ProfilWarrior.FLIGHT;

  public Castle(Player player) {
    this.player = player;
    this.troop = new ArrayList<Fightable>(FightableContainer.MAX_TROOP_SIZE);
    this.heroes = new ArrayList<Hero>();
    this.battlePosition = new BattlePositionMap(
        FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
    this.factory = new HashMap<ProfilWarrior, Level>();
    this.factory.put(defaultWarrior, defaultLevel);
  }
  
  public Hero getHero(Hero hero) {

  	int index = this.heroes.indexOf(hero);
  	if (index != -1) {
			Hero h = this.heroes.get(index);
			this.heroes.remove(index);
			return h;
		}
  	return null;
	}

  public boolean canBuyWarrior(ProfilWarrior profil) {

    return this.factory.containsKey(profil);
  }

  public void buildFactory(ProfilWarrior profil) {

    if (!this.factory.containsKey(profil)) {
      this.factory.put(profil, defaultLevel);
    }
  }

  public Set<ProfilWarrior> getBuildableFactories() {

  	return this.factory.keySet();
  }
  
  public void upgradeFactory(ProfilWarrior profil) {

    if (this.factory.containsKey(profil)) {
      Level level = this.factory.get(profil);
      System.out.println(profil);
      System.out.println(level);
      this.factory.remove(profil);
      Level nextLevel = level.getNextLevel();
      System.out.println(nextLevel);
      this.factory.put(profil, nextLevel == null ? level : nextLevel);
    }
  }

  public boolean createWarrior(ProfilWarrior profil)
      throws MaxNumberOfTroopsReachedException {

    if (this.canBuyWarrior(profil)) {
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
      this.battlePosition.placeFightable(f, this.battlePosition
          .getFirstFreeLocation());
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } catch (LocationAlreadyOccupedException e) {
      return false;
    } catch (NoPlaceAvailableException e) {
      return false;
    }
    this.troop.add(f);
    f.setFightableContainer(this);
    return true;
  }

  public boolean addHero(Hero hero) {

    this.heroes.add(hero);
    return true;
  }
  
  public Level getFactoryLevel(ProfilWarrior pw) {

		return this.factory.get(pw);
	}

  public List<CastleItem> getItems() {
    if (this.items == null) {
      this.items = new ArrayList<CastleItem>();
      items.add(CastleItem.defaultItem);
      items.add(new HeroRecruitmentItem(this));
      items.add(new WarriorRecruitmentItem(this));
      items.add(new UpgradeCastleItem(this));
      items.add(new UpgradeFactoryItem(this));
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

    // TODO Auto-generated method stub

  }

  @Override
  public boolean encounter(Encounter encounter) {

  	if (!encounter.getSender().getPlayer().equals(this.getPlayer())) {
      CoreEngine.startBattle(encounter.getSender(), this);
    } else {
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
			sb.append("[").append(entries.getKey()).append(",").append(entries.getValue()).append("]");
		}
  	sb.append(")");
  	
  	return sb.toString();
  }
}
