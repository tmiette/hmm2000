package fr.umlv.hmm2000.building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.umlv.hmm2000.Player;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.gui.Sprite;
import fr.umlv.hmm2000.war.BattlePositionMap;
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

  public boolean canBuyWarrior(ProfilWarrior profil) {

    return this.factory.containsKey(profil);
  }

  public void buildFactory(ProfilWarrior profil) {

    if (!this.factory.containsKey(profil)) {
      this.factory.put(profil, defaultLevel);
    }
  }

  public List<ProfilWarrior> getBuildableFactories() {

    ArrayList<ProfilWarrior> list = new ArrayList<ProfilWarrior>();
    for (ProfilWarrior profil : ProfilWarrior.values()) {
      if (!this.factory.containsKey(profil)) {
        list.add(profil);
      }
    }
    return list;
  }

  public void upgradeFactory(ProfilWarrior profil) {

    if (this.factory.containsKey(profil)) {
      Level level = this.factory.get(profil);
      this.factory.remove(profil);
      this.factory.put(profil, level.getNextLevel());
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

    if (this.troop.size() < FightableContainer.MAX_TROOP_SIZE) {
      f.setFightableContainer(this);
      this.troop.add(f);
      return true;
    } else {
      throw new MaxNumberOfTroopsReachedException(
          "Too much warriors in the castle");
    }
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
  public boolean encounter(Encounter Encounter) {

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

}
