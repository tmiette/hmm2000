package fr.umlv.hmm2000.unit;

import java.util.ArrayList;
import java.util.List;

import fr.umlv.hmm2000.engine.CoreEngine;
import fr.umlv.hmm2000.engine.Player;
import fr.umlv.hmm2000.engine.guiinterface.HMMUserInterface;
import fr.umlv.hmm2000.engine.guiinterface.Sprite;
import fr.umlv.hmm2000.engine.guiinterface.UIDisplayingVisitor;
import fr.umlv.hmm2000.engine.manager.MoveCoreManager.Encounter;
import fr.umlv.hmm2000.map.Location;
import fr.umlv.hmm2000.map.MovableElement;
import fr.umlv.hmm2000.map.battle.BattlePositionMap;
import fr.umlv.hmm2000.map.battle.exception.LocationAlreadyOccupedException;
import fr.umlv.hmm2000.map.battle.exception.NoPlaceAvailableException;
import fr.umlv.hmm2000.map.builder.MapForegroundElementSaver;
import fr.umlv.hmm2000.resource.Resource.Kind;
import fr.umlv.hmm2000.salesentity.Price;
import fr.umlv.hmm2000.unit.exception.MaxNumberOfTroopsReachedException;
import fr.umlv.hmm2000.unit.skill.Skill;

/**
 * Represents hero who owns fightable to fight on battle map.
 * 
 * @author MIETTE Tom
 * @author MOURET Sebastien
 * 
 */
public class Hero extends MovableElement {

  // Hero counter
  private static int HEROES_COUNT = 0;

  // Unique id
  private final int id;

  // His army
  private final ArrayList<Fightable> troop;

  // Skills he can use during battle
  private final ArrayList<Skill> skills;

  private final String name;

  // Troops layout on battle map
  private final BattlePositionMap battlePosition;

  // Steps left to do on world map
  private double stepCount;

  // Default steps
  private int speed;

  // Icon to display on map
  private Sprite sprite;

  // Specify who start to attack
  private final int attackPriority;

  Hero(Player player, Sprite sprite, String name, Skill[] skills,
      int attackPriority) {
    super(player);
    this.id = HEROES_COUNT++;
    this.name = name;
    this.battlePosition = new BattlePositionMap(
        FightableContainer.MAX_TROOP_SIZE / BattlePositionMap.LINE_NUMBER);
    this.sprite = sprite;
    this.troop = new ArrayList<Fightable>();
    this.skills = new ArrayList<Skill>();
    // adding skills
    for (Skill skill : skills) {
      this.skills.add(skill);
    }
    this.attackPriority = attackPriority;
  }

  @Override
  public boolean addFightable(Fightable f)
      throws MaxNumberOfTroopsReachedException {

    // troop capacity
    if (this.troop.size() == FightableContainer.MAX_TROOP_SIZE) {
      throw new MaxNumberOfTroopsReachedException(
          "The max number of troops, a heroe can contain, is reached");
    }

    // Hero takes speed value of his slower troop unit
    final int speed = f.getSpeed();
    if (speed < this.speed || this.speed == 0) {
      this.speed = speed;
      this.stepCount = speed;
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

  public int getSpeed() {

    return this.speed;
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
      // removing unit from troop
      this.troop.remove(index);
      this.battlePosition.removeMapForegroundElement(this.battlePosition
          .getLocationForMapForegroundElement(f));
      // updating speed
      if (this.speed == speed) {
        this.speed = 0;
        for (Fightable fightable : this.troop) {
          if (fightable.getSpeed() < this.speed || this.speed == 0) {
            this.speed = fightable.getSpeed();
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
  public boolean encounter(Encounter encounter) {

    if (!encounter.getSender().getPlayer().equals(this.getPlayer())) {
      // starting new battle against element encountered
      CoreEngine.startBattle(encounter.getSender(), this);
    } else {
      // starting swap unit with element encountered
      CoreEngine.startSwap(this, encounter.getSender());
    }
    return false;
  }

  @Override
  public void nextDay(int day) {
    if (this.getPlayer().spend(new Price().addResource(Kind.GOLD, 1))) {
      this.stepCount = this.speed;
    } else {
      CoreEngine.fireMessage(this.getPlayer() + " : you cannot pay " + this
          + " anymore. This hero leave.", HMMUserInterface.WARNING_MESSAGE);
      Location l = CoreEngine.map().getLocationForMapForegroundElement(this);
      CoreEngine.map().removeMapForegroundElement(l);
      CoreEngine.fireSpriteRemoved(l, this.sprite);
    }
  }

  @Override
  public Sprite getSprite() {

    return this.sprite;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public int getAttackPriority() {

    return this.attackPriority;
  }

  /**
   * Gets list of hero skills.
   * 
   * @return hero skills
   */
  public List<Skill> getSkills() {

    return this.skills;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public String getSaveString() {
    return MapForegroundElementSaver.save(this);
  }

  /**
   * Returns unique id of the hero.
   * 
   * @return unique id of the hero.
   */
  public int getId() {
    return this.id;
  }

}
