package fr.umlv.hmm2000.unit;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.battle.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.map.battle.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.map.builder.MapForegroundElementSaver;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;

/**
 * This class represents a monster troop who can own warrior
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 */
public class Monster implements FightableContainer {

  // icon to display on map
  private final Sprite sprite;

  // his army
  private final ArrayList<Fightable> troop;

  // troops layout on battle map
  private final BattlePositionMap battlePosition;

  // player owner unit
  private Player player;

  // specify who start to attack
  private final int attackPriority;

  // name
  private final String name;

  public Monster(Player player, Sprite sprite, String name, int attackPriority) {

    this.player = player;
    this.sprite = sprite;
    this.name = name;
    this.battlePosition = new BattlePositionMap(
        FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
    this.troop = new ArrayList<Fightable>();
    this.attackPriority = attackPriority;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean addFightable(Fightable f)
      throws MaxNumberOfTroopsReachedException {

    // troop capacity
    if (this.troop.size() == FightableContainer.MAX_TROOP_SIZE) {
      throw new MaxNumberOfTroopsReachedException(
          "The max number of troops, a heroe can contain, is reached");
    }

    try {
      // placing a new fightable to the battle position
      this.battlePosition.placeFightable(f, this.battlePosition
          .getFirstFreeLocation());
    } catch (ArrayIndexOutOfBoundsException e) {
      return false;
    } catch (LocationAlreadyOccupedException e) {
      return false;
    } catch (NoPlaceAvailableException e) {
      return false;
    }
    // adding a new fightable
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

    visitor.visit(this);
  }

  @Override
  public boolean encounter(Encounter encounter) {

    CoreEngine.startBattle(encounter.getSender(), this);
    return false;
  }

  @Override
  public void nextDay(int day) {

    // do nothing
  }

  @Override
  public Sprite getSprite() {

    return this.sprite;
  }

  @Override
  public Player getPlayer() {

    return this.player;
  }

  @Override
  public void setPlayer(Player player) {

    this.player = player;

  }

  @Override
  public int getAttackPriority() {

    return this.attackPriority;
  }

  @Override
  public String toString() {
    return this.name;
  }
  
  @Override
  public String getSaveString() {
    return MapForegroundElementSaver.save(this);
  }

}
